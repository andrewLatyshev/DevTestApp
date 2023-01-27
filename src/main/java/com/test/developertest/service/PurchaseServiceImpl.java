package com.test.developertest.service;

import com.test.developertest.models.Purchase;
import com.test.developertest.repository.PurchaseRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;


@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final EntityManager entityManager;
    private final PurchaseConverter purchaseConverter;

    private final FileStorageService fileStorageService;

    static final Logger LOGGER = LogManager.getLogger();


    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, EntityManager entityManager, PurchaseConverter purchaseConverter, FileStorageService fileStorageService) {
        this.purchaseRepository = purchaseRepository;
        this.entityManager = entityManager;
        this.purchaseConverter = purchaseConverter;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return entityManager.createNamedQuery("showAllPurchases", Purchase.class).getResultList();
    }

    @Override
    public Purchase showPurchase(Long id) {
        return purchaseRepository.getById(id);
    }

    @Override
    public Purchase parsePurchaseToFile(Long id) {
        try {
            purchaseConverter.writeInternal(purchaseRepository.getById(id));
            LOGGER.log(Level.INFO, "Файл успешно представлен пользователю");
            return purchaseRepository.getById(id);
        } catch (JAXBException e) {
            throw new RuntimeException(e + "Произошла ошибка при запросе покупки из БД");
        }
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
    public void addPurchaseFromFile(Resource file) throws IOException {

        try {
            purchaseRepository.save(purchaseConverter.readInternal(file.getFile()));
            LOGGER.log(Level.INFO, "Покупка успешно добавлена в БД");
        } catch (JAXBException e) {
            throw new RuntimeException(e + "Произошла ошибка при добавлении покупки в БД, неверный формат в файле");
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
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
