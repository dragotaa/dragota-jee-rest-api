package com.dragota.jee.controller;

import com.dragota.jee.model.BankClient;
import com.dragota.jee.service.BankClientService;

import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ejb.LockType.READ;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Lock(READ)
@Singleton
@Path("/premierBankClients")
public class BankClientResource {
    private final BankClientService bankClientService;

    @Inject
    public BankClientResource(BankClientService bankClientService) {
        this.bankClientService = bankClientService;
    }

    @GET
    @Produces({APPLICATION_JSON})
    public List<BankClient> getPremierBankClients() {
        return bankClientService.getAllBankClients().stream()
                .filter(this::isPremierCustomer)
                .collect(Collectors.toList());
    }

    private boolean isPremierCustomer(BankClient bankClient) {
        return bankClient.isPremierCursomer();
    }
}

