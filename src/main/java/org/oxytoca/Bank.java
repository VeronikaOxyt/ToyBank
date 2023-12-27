package org.oxytoca;

import java.util.concurrent.atomic.AtomicLong;

import static java.lang.System.*;

public class Bank {
    private final AtomicLong balance = new AtomicLong(0);

    public void setBalance(long balanceToLong) {
        balance.updateAndGet((updatedBalance) -> balanceToLong);
    }

    public AtomicLong getBalance() {
        return balance;
    }

    public void changeBalance(ActionsWithBalance action, String handlerName,
                                           long amount, Request request) {
        switch (action) {
            case CREDIT:
                if (balance.get() >= amount) {
                    balance.updateAndGet((balanceToLong) -> balanceToLong - amount);
                    out.println("Заявка " + request.toString() +
                            " УСПЕШНО ВЫПОЛНЕНА. " + "Баланс банка: " + balance);
                } else out.println("Заявка " + request.toString() +
                        " НЕ ВЫПОЛНЕНА. Получена от " + handlerName + ". Сумма больше баланса банка"
                        + "Баланс банка: " + balance);
                break;
            case REPAYMENT:
                balance.updateAndGet((balanceToLong) -> balanceToLong + amount);
                out.println("Заявка " + request.toString() +
                        " УСПЕШНО ВЫПОЛНЕНА. Получена от " + handlerName
                        + ". Баланс банка: " + balance);
                break;
        }
    }
}
