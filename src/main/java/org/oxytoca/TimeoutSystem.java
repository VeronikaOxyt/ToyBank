package org.oxytoca;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class TimeoutSystem implements Callable<Void> {
    private final Bank bank;
    private final long balance;

    public TimeoutSystem(Bank bank, long balance) {
        this.bank = bank;
        this.balance = balance;
    }

    @Override
    public Void call() throws InterruptedException {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long minTimeout = 5000;
        long maxTimeout = 10000;
        long timeout = random.nextLong(minTimeout, maxTimeout);
        Thread.sleep(timeout);
        bank.setBalance(balance);
        return null;
    }
}
