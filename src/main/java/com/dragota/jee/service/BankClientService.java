package com.dragota.jee.service;

import com.dragota.jee.model.BankClient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
public class BankClientService {

    @PersistenceContext(unitName = "bankClientPersistenceUnit")
    private EntityManager entityManager;

    public void addBankClient(BankClient bankClient) {
        entityManager.persist(bankClient);
    }

    public List<BankClient> getAllBankClients() {
        CriteriaQuery<BankClient> cq = entityManager.getCriteriaBuilder().createQuery(BankClient.class);
        cq.select(cq.from(BankClient.class));
        return entityManager.createQuery(cq).getResultList();
    }

    public void clear() {
        Query removeAll = entityManager.createQuery("delete from BankClient");
        removeAll.executeUpdate();
    }
}
