package org.oxytoca;

import static org.oxytoca.ActionsWithBalance.*;

public class Main {
    public static void main(String[] args) {
        FrontalSystem frontalSystem = new FrontalSystem();
        Bank bank = new Bank(0);

        Client client1 = new Client("Клиент №1", frontalSystem);
        Client client2 = new Client("Клиент №2", frontalSystem);
        Client client3 = new Client("Клиент №3", frontalSystem);
        Client client4 = new Client("Клиент №4", frontalSystem);
        Client client5 = new Client("Клиент №5", frontalSystem);

        Thread threadOfClient1 = new Thread(client1);
        Thread threadOfClient2 = new Thread(client2);
        Thread threadOfClient3 = new Thread(client3);
        Thread threadOfClient4 = new Thread(client4);
        Thread threadOfClient5 = new Thread(client5);

        Thread handlerThread1 = new Thread(new RequestHandler("Обработчик заявок №1",
                frontalSystem, bank));
        Thread handlerThread2 = new Thread(new RequestHandler("Обработчик заявок №2",
                frontalSystem, bank));

        client1.setRequest(new Request(client1.getClientTreadName(), REPAYMENT, 10000));
        client2.setRequest(new Request(client2.getClientTreadName(), REPAYMENT, 15000));
        client3.setRequest(new Request(client3.getClientTreadName(), REPAYMENT, 20000));
        client4.setRequest(new Request(client4.getClientTreadName(), CREDIT, 5000));
        client5.setRequest(new Request(client5.getClientTreadName(), CREDIT, 150000));

        threadOfClient1.start();
        threadOfClient2.start();
        threadOfClient3.start();
        threadOfClient4.start();
        threadOfClient5.start();

        handlerThread1.start();
        handlerThread2.start();
    }

}
