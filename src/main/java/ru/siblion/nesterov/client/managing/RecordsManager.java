package ru.siblion.nesterov.client.managing;

import ru.siblion.nesterov.client.type.Record;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Date;

/**
 * Created by rnaway on 05.02.2017.
 */
@Stateless
public class RecordsManager {
    @PersistenceUnit
    // Переменная для внедрения экземпляра EntityManagerFactory
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @PostConstruct
    private void init() {
    // Создает EntityManager
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void addRecord(String username, String action, String message, Date date) {
        init();
        entityManager.joinTransaction();
        Record record = new Record();
        record.setUsername(username);
        record.setAction(action);
        record.setMessage(message);
        record.setDate(date);
        entityManager.persist(record);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}