package io.github.lucasduete.sd.sincronizacaothreads;

import io.github.lucasduete.sd.sincronizacaothreads.buffer.Buffer;

import java.util.Random;

public class Produtor implements Runnable {

    private final Buffer buffer;
    private final Random random = new Random();

    public Produtor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++ ) {
            try {
                Thread.sleep(random.nextInt(3000));
                buffer.set(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("\nFIM DO PRODUTOR\n");
    }
}
