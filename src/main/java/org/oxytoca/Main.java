package org.oxytoca;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.oxytoca.ActionsWithBalance.*;

public class Main {
    public static void main(String[] args) {
        FrontalSystem frontalSystem = new FrontalSystem(2);
        Bank bank = new Bank();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Client client1 = new Client("Клиент №1", frontalSystem);
        Client client2 = new Client("Клиент №2", frontalSystem);
        Client client3 = new Client("Клиент №3", frontalSystem);
        Client client4 = new Client("Клиент №4", frontalSystem);
        Client client5 = new Client("Клиент №5", frontalSystem);

        RequestHandler requestHandler1 = new RequestHandler("Обработчик заявок №1",
                frontalSystem, bank);
        RequestHandler requestHandler2 = new RequestHandler("Обработчик заявок №2",
                frontalSystem, bank);

        List<TimeoutSystem> timeoutsList = new ArrayList<>();
        timeoutsList.add(new TimeoutSystem(bank, bank.getBalance().get()));
        timeoutsList.add(new TimeoutSystem(bank, bank.getBalance().get()));
        timeoutsList.add(new TimeoutSystem(bank, bank.getBalance().get()));

        client1.setRequest(new Request(client1.getClientTreadName(), REPAYMENT, 10000));
        client2.setRequest(new Request(client2.getClientTreadName(), REPAYMENT, 15000));
        client3.setRequest(new Request(client3.getClientTreadName(), REPAYMENT, 20000));
        client4.setRequest(new Request(client4.getClientTreadName(), CREDIT, 5000));
        client5.setRequest(new Request(client5.getClientTreadName(), CREDIT, 150000));

        try {
            executorService.invokeAll(timeoutsList);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.execute(client1);
        executorService.execute(client2);
        executorService.execute(client3);
        executorService.execute(client4);
        executorService.execute(client5);

        executorService.execute(requestHandler1);
        executorService.execute(requestHandler2);
    }

}
