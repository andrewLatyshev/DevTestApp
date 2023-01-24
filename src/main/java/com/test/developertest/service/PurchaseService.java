package com.test.developertest.service;

import com.test.developertest.models.Purchase;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface PurchaseService {

    List<Purchase> getAllPurchases() throws IOException;

    Purchase showPurchase(Long id);

    void deletePurchase(Long id);

    void addPurchase(Purchase purchase);

    void editPurchase(Purchase purchase);

    List<Purchase> bestSellsOfWeek();

    String bestProductOfMonth();

    String nameOfHalfAYear();

    String productFor18();
}
