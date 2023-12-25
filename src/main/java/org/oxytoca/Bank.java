package org.oxytoca;

import static java.lang.System.*;

public class Bank {
    private double balance;

    public Bank(double balance) {
        this.balance = balance;
    }

    public synchronized void changeBalance(ActionsWithBalance action, String handlerName,
                                           double amount, Request request) {
        switch (action) {
            case CREDIT:
                if (balance >= amount) {
                    balance -= amount;
                    out.println("Заявка " + request.toString() +
                            " УСПЕШНО ВЫПОЛНЕНА. " + "Баланс банка: " + balance);
                } else out.println("Заявка " + request.toString() +
                        " НЕ ВЫПОЛНЕНА. Получена от " + handlerName + ". Сумма больше баланса банка"
                        + "Баланс банка: " + balance);
                break;
            case REPAYMENT:
                balance += amount;
                out.println("Заявка " + request.toString() +
                        " УСПЕШНО ВЫПОЛНЕНА. Получена от " + handlerName
                        + ". Баланс банка: " + balance);
                break;
        }
    }
}
