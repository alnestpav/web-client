package ru.siblion.nesterov.client.managing;

import ru.siblion.nesterov.client.type.Action;
import ru.siblion.nesterov.client.type.Record;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.Date;

/**
 * Created by rnaway on 05.02.2017.
 */
@Stateless
public class RecordsManager {
    @PersistenceContext(unitName = "client")
    private EntityManager entityManager;

    public void addRecord(String username, Action action, String message, Date date) {
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