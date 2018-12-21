package io.github.lucasduete.sd.sincronizacaothreads;

import io.github.lucasduete.sd.sincronizacaothreads.buffer.Buffer;
import io.github.lucasduete.sd.sincronizacaothreads.buffer.BufferWithCicleBuffer;
import io.github.lucasduete.sd.sincronizacaothreads.buffer.BufferWithLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Buffer buffer = new BufferWithCicleBuffer();

        executorService.execute(new Produtor(buffer));
        executorService.execute(new Consumidor(buffer));

        executorService.shutdown();
    }
}
