package io.github.lucasduete.sd.sincronizacaothreads.buffer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BufferWithBlockingQueue implements Buffer {

    private final BlockingQueue<Integer> queue;

    public BufferWithBlockingQueue() {
        this.queue = new ArrayBlockingQueue<>(3);
    }

    @Override
    public void set(int value) {
        try {
            queue.put(value);
            System.out.println("Producer write " + value);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int get() {
        int readedValue = 0;

        try {
            readedValue = queue.take();
            System.out.println("Consumerer read " + readedValue);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return readedValue;
    }
}
