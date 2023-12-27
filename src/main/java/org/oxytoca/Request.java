package org.oxytoca;

public class Request {
    private final String clientTreadName;
    private final ActionsWithBalance requestType;
    private final long amount;

    public Request(String clientTreadName, ActionsWithBalance requestType, long amount) {
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

    public long getAmount() {
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
