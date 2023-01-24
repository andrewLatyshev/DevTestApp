package com.test.developertest.service;

import com.test.developertest.models.Purchase;
import com.test.developertest.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final EntityManager entityManager;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, EntityManager entityManager) {
        this.purchaseRepository = purchaseRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return entityManager.createNamedQuery("showAllPurchases", Purchase.class).getResultList();
    }

    @Override
    public Purchase showPurchase(Long id) {
        return purchaseRepository.getById(id);
    }

    @Transactional
    @Override
    public void deletePurchase(Long id) {
        entityManager.createNamedQuery("deletePurchase", Purchase.class);
    }

    @Transactional
    @Override
    public void addPurchase(Purchase purchase) {
        entityManager.createNamedQuery("createPurchase", Purchase.class);
    }

    @Transactional
    @Override
    public void editPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public List<Purchase> bestSellsOfWeek() {
        return entityManager.createNamedQuery("reportForWeek", Purchase.class).getResultList();
    }


    public String bestProductOfMonth() {
        return entityManager.createNamedQuery("goodOfMonth", Purchase.class).getSingleResult().toString();
    }

    public String nameOfHalfAYear() {
        return entityManager.createNamedQuery("topBuyer", Purchase.class).getSingleResult().toString();
    }

    public String productFor18() {
        return entityManager.createNamedQuery("bestFor18", Purchase.class).getSingleResult().toString();
    }
    }
