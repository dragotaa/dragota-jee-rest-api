package com.dragota.jee;

import com.dragota.jee.model.BankClient;
import com.dragota.jee.service.BankClientService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.stream.Stream;

@Singleton
@Startup
public class StartupBean {
    private final BankClientService bankClientService;

    @Inject
    public StartupBean(BankClientService bankClientService) {
        this.bankClientService = bankClientService;
    }

    @PostConstruct
    private void startup() {
        Stream
                .of("Jack London", "Tom Cruise", "Traian Vuia", "Eva Mendez", "Angelina Jolie", "Brad Pitt")
                .forEach(name ->
                bankClientService.addBankClient(new BankClient(name, BigDecimal.ZERO, false))
        );
        bankClientService.getAllBankClients().forEach(System.out::println);
    }

    @PreDestroy
    private void shutdown() {
        bankClientService.clear();
    }
}