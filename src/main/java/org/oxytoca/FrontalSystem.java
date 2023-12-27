package org.oxytoca;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FrontalSystem {

    /**
     * Согласно заданию требуется подобрать коллекцию с
     * механизмом блокировки, доступа FIFO и возможностью
     * ограничить ее емкость (capacity). Подходит
     * BlockingQueue.
     */
    private final BlockingQueue<Request> requestsQueue;

    public FrontalSystem(int capacity) {
        requestsQueue = new ArrayBlockingQueue<>(capacity);
    }


    public void addRequest(Request request) throws InterruptedException {
        requestsQueue.put(request);
    }

    public Request getRequest() {
        try {
            return requestsQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
