package org.oxytoca;

public class Request {
    private final String clientTreadName;
    private final ActionsWithBalance requestType;
    private final double amount;

    public Request(String clientTreadName, ActionsWithBalance requestType, double amount) {
        this.clientTreadName = clientTreadName;
        this.requestType = requestType;
        this.amount = amount;
    }

    public String getClientTreadName() {
        return clientTreadName;
    }

    public ActionsWithBalance getRequestType() {
        return requestType;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Request{" +
                "clientTreadName='" + clientTreadName + '\'' +
                ", requestType=" + requestType +
                ", amount=" + amount +
                '}';
    }
}
