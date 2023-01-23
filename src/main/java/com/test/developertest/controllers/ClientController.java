package com.test.developertest.controllers;


import com.sun.xml.txw2.annotation.XmlAttribute;
import com.test.developertest.models.Product;
import com.test.developertest.models.Purchase;
import com.test.developertest.service.ProductService;
import com.test.developertest.service.PurchaseConverter;
import com.test.developertest.service.PurchaseService;
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

import java.util.List;


@RestController
@Validated
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_XML_VALUE})
public class ClientController {

    PurchaseService purchaseService;

    private final ProductService productService;

    private PurchaseConverter purchaseConverter;

    public ClientController(PurchaseService purchaseService, ProductService productService) {
        this.purchaseService = purchaseService;
        this.productService = productService;
    }
    @GetMapping(value = "/index")
    public ResponseEntity<List<Purchase>> showAllPurchases() {
        try {
            return new ResponseEntity<>(purchaseService.getAllPurchases(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "aboutThisPurchase/{id}")
    public ResponseEntity<Purchase> showThisPurchase(@PathVariable Long id) {
        try {
        Purchase purchase = purchaseService.showPurchase(id);
        return new ResponseEntity<>(purchase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "/addPurchase")
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

    @PutMapping(value = "update")
    public ResponseEntity<Purchase> updatePurchase(@RequestBody Purchase purchase) {
        try {
            purchaseService.editPurchase(purchase);
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> showAllProducts() {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<>(productService.getProductList(), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
