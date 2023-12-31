package org.oxytoca;

public class Client implements Runnable {
    private String clientTreadName;
    private Request request;
    private FrontalSystem frontalSystem;

    public Client(String clientTreadName, FrontalSystem frontalSystem) {
        this.clientTreadName = clientTreadName;
        this.frontalSystem = frontalSystem;
    }

    public String getClientTreadName() {
        return clientTreadName;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        frontalSystem.addRequest(request);
        System.out.println(request.getClientTreadName() + ": " + "заявка: " +
                request.toString() + " отправлена в банк.");
    }
}
