package com.test.developertest.service;

import com.test.developertest.models.Purchase;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface PurchaseService {

    List<Purchase> getAllPurchases() throws IOException;

    Purchase showPurchase(Long id);

    void deletePurchase(Long id);

    void addPurchase(Purchase purchase);

    public Purchase parsePurchaseToFile(Long id);

    public void addPurchaseFromFile(Resource file) throws JAXBException, SAXException, IOException;

    void editPurchase(Purchase purchase);

    List<Purchase> bestSellsOfWeek();

    String bestProductOfMonth();

    String nameOfHalfAYear();

    String productFor18();
}
