package io.github.lucasduete.sd.sincronizacaothreads.buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

    public class BufferWithLock implements Buffer {

    private Lock lock = new ReentrantLock(false);
    private Condition canRead = lock.newCondition();
    private Condition canWrite = lock.newCondition();

    private Integer buffer = -1;
    private Boolean empty = true;

    @Override
    public void set(int value) {
        lock.lock();

        try {
            while (!empty) {
                System.out.println("Productor try write but buffer is full");
                canWrite.await();
            }

            buffer = value;
            System.out.println("Productor write " + buffer);
            empty = false;
            canRead.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public int get() {
        lock.lock();

        try {

            while (empty) {
                System.out.println("Consumer try consume but buffer is empty");
                canRead.await();
            }

            empty = true;
            System.out.println("Consumer read : " + buffer);
            canWrite.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return buffer;
    }
}
