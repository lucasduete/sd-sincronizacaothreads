package io.github.lucasduete.sd.sincronizacaothreads.buffer;

public class BufferSimple implements Buffer {

    private Integer buffer = -1;


    @Override
    public void set(int value) {
        System.out.println("Productor write " + value);
        this.buffer = value;
    }

    @Override
    public int get() {
        System.out.println("Consumer read " + buffer.toString());
        return buffer;
    }

}
