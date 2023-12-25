package org.oxytoca;

public class RequestHandler implements Runnable {
    private final FrontalSystem frontalSystem;
    private final Bank bank;
    private final String handlerName;

    public RequestHandler(String handlerName, FrontalSystem frontalSystem, Bank bank) {
        this.handlerName = handlerName;
        this.frontalSystem = frontalSystem;
        this.bank = bank;
    }

    public String getHandlerName() {
        return handlerName;
    }

    @Override
    public void run() {
        while (true) {
            Request request = frontalSystem.getRequest();
            if (request != null) {
                bank.changeBalance(request.getRequestType(), handlerName,
                        request.getAmount(), request);
                System.out.println(handlerName +": " + "Получена заявка на обработку по клиенту "
                        + request.getClientTreadName());
            }
        }
    }


}
