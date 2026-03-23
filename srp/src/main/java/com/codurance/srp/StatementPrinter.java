package com.codurance.srp;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toCollection;

public class StatementPrinter {

    private final Console console;
    private final StatementFormatter formatter;

    public StatementPrinter(Console console, StatementFormatter formatter) {
        this.console = console;
        this.formatter = formatter;
    }

    public void print(List<Transaction> transactions) {
        console.printLine(formatter.header());
        final AtomicInteger balance = new AtomicInteger(0);
        transactions.stream()
                .map(transaction -> formatter.formatLine(transaction, balance.addAndGet(transaction.amount())))
                .collect(toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(console::printLine);
    }
}
