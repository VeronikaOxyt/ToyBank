package org.oxytoca;

import java.util.ArrayDeque;
import java.util.Queue;

public class FrontalSystem {
    private final Queue<Request> requests = new ArrayDeque<>();

    public synchronized void addRequest(Request request) {
        while (requests.size() > 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        requests.add(request);
        notifyAll();
    }

    public synchronized Request getRequest() {
        while (requests.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Request request = requests.poll();
        notifyAll();
        return request;
    }
}
