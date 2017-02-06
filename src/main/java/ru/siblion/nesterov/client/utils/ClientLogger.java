package ru.siblion.nesterov.client.utils;

import ru.siblion.nesterov.client.type.Action;
import ru.siblion.nesterov.client.type.ClientLogMessage;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by rnaway on 05.02.2017.
 */
@Stateless
public class ClientLogger {
    @PersistenceContext(unitName = "client")
    private EntityManager entityManager;

    @Asynchronous
    public void log(String username, Action action, String message) {
        ClientLogMessage clientLogMessage = new ClientLogMessage();
        clientLogMessage.setUsername(username);
        clientLogMessage.setAction(action);
        clientLogMessage.setMessage(message);
        clientLogMessage.setDate(new Date());
        entityManager.persist(clientLogMessage);
    }
}