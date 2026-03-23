package com.codurance.srp;

public class AccountService {

    private final TransactionRepository transactionRepository;
    private final Clock clock;
    private final StatementPrinter statementPrinter;

    public AccountService(TransactionRepository transactionRepository, Clock clock, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        transactionRepository.add(new Transaction(clock.today(), amount));
    }

    public void withdraw(int amount) {
        transactionRepository.add(new Transaction(clock.today(), -amount));
    }

    public void printStatement() {
        statementPrinter.print(transactionRepository.all());
    }
}