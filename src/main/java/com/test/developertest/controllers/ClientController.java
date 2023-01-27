package com.test.developertest.controllers;


import com.test.developertest.models.FilesStorage;
import com.test.developertest.models.Product;
import com.test.developertest.models.Purchase;
import com.test.developertest.service.FileStorageService;
import com.test.developertest.service.ProductService;
import com.test.developertest.service.PurchaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@RestController
@Validated
@RequestMapping(value = "/api")
public class ClientController {

    private final PurchaseService purchaseService;

    private final ProductService productService;

    private final FileStorageService storageService;

    static final Logger LOGGER = LogManager.getLogger();

    public ClientController(PurchaseService purchaseService, ProductService productService, FileStorageService filesStorage) {
        this.purchaseService = purchaseService;
        this.productService = productService;
        this.storageService = filesStorage;
    }
    @GetMapping(value = "/index", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Purchase>> showAllPurchases() {
        try {
            return new ResponseEntity<>(purchaseService.getAllPurchases(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "aboutThisPurchase/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Purchase> parsePurchaseToFile(@PathVariable Long id) {
        try {
        Purchase purchase = purchaseService.showPurchase(id);
        return new ResponseEntity<>(purchase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = storageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            LOGGER.info("Could not determine file type.");
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping(value = "/addPurchase", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Purchase> createPurchaseForm(@RequestBody Purchase purchase) {
        try {

            purchaseService.addPurchase(purchase);
            return new ResponseEntity<>(purchase, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Purchase> deletePurchase(@PathVariable Long id) {
        try {
            purchaseService.deletePurchase(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Purchase> updatePurchase(@RequestBody Purchase purchase) {
        try {
            purchaseService.editPurchase(purchase);
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Product>> showAllProducts() {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<>(productService.getProductList(), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
