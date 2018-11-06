package com.dragota.jee.beans;

import com.dragota.jee.model.BankClient;
import com.dragota.jee.service.BankClientService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class BankClientBean  implements Serializable {

    @Inject
    private BankClientService bankClientService;
    private List<BankClient> bankClientsAvailable;
    private List<BankClient> bankPremierClientsAvailable;
    private int sourceAccountId;
    private int destinationAccountId;
    private String name;
    private boolean premierCustomer;
    private BigDecimal initialBalance;
    private BigDecimal amountToTransfer;
    private BankClient sourceBankClient;
    private BankClient destinationBankClient;


    @PostConstruct
    public void init() {
        loadClients();
    }

    private void loadClients(){
        bankClientsAvailable = bankClientService.getAllBankClients();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BankClient> getBankClientsAvailable() {
        return bankClientsAvailable;
    }

    public void setBankClientsAvailable(List<BankClient> bankClientsAvailable) {
        this.bankClientsAvailable = bankClientsAvailable;
    }

    public String getPremierBankClients() {
        bankPremierClientsAvailable = bankClientService.getAllBankClients()
                .stream()
                .filter(this::isPremierCustomer)
                .collect(Collectors.toList());

        return "success";
    }

    private boolean isPremierCustomer(BankClient bankClient) {
        return bankClient.isPremierCursomer();
    }


    public String getExistingBankClients() {
        bankClientsAvailable = bankClientService.getAllBankClients();
        return "success";
    }

    public String add() {
        BankClient bankClient = new BankClient();
        bankClient.setName(name);
        bankClient.setPremierCursomer(isPremierCustomer());
        bankClientService.addBankClient(bankClient);
        if (isPremierCustomer()){
            return "addedPremierCustomer";
        }
        return "success";
    }

    public String trasferFundsInternally() {
        List<BankClient> bankClients = bankClientService.getAllBankClients();
        Optional<BankClient> sourceBankClient = bankClients.stream().filter(x -> x.getId() == sourceAccountId).findAny();
        Optional<BankClient> destinationBankClient = bankClients.stream().filter(x -> x.getId() == destinationAccountId).findAny();
        if (!sourceBankClient.isPresent()){
            return "sourceAccountNotFound";
        }
        if (!destinationBankClient.isPresent()){
            return "destinationAccountNotFound";
        }
        if (sourceBankClient.get().getBalance().add(amountToTransfer.multiply(new BigDecimal(-1))).doubleValue() > 0){
            sourceBankClient.get().setBalance(sourceBankClient.get().getBalance().add(amountToTransfer.multiply(new BigDecimal(-1))));
            destinationBankClient.get().setBalance(sourceBankClient.get().getBalance().add(amountToTransfer));
        } else {
            return "insuficientFunds";
        }
        return "fundTransferSuccess";
    }

    public boolean isPremierCustomer() {
        return premierCustomer;
    }

    public void setPremierCustomer(boolean premierCustomer) {
        this.premierCustomer = premierCustomer;
    }

    public List<BankClient> getBankPremierClientsAvailable() {
        return bankPremierClientsAvailable;
    }

    public void setBankPremierClientsAvailable(List<BankClient> bankPremierClientsAvailable) {
        this.bankPremierClientsAvailable = bankPremierClientsAvailable;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public int getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(int destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public int getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(int sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public BigDecimal getAmountToTransfer() {
        return amountToTransfer;
    }

    public void setAmountToTransfer(BigDecimal amountToTransfer) {
        this.amountToTransfer = amountToTransfer;
    }

    public BankClient getSourceBankClient() {
        return sourceBankClient;
    }

    public void setSourceBankClient(BankClient sourceBankClient) {
        this.sourceBankClient = sourceBankClient;
    }

    public BankClient getDestinationBankClient() {
        return destinationBankClient;
    }

    public void setDestinationBankClient(BankClient destinationBankClient) {
        this.destinationBankClient = destinationBankClient;
    }
}
