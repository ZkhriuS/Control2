package model;

import java.util.List;

public class Counter implements AutoCloseable{
    private int counter;

    public void add(){
        counter++;
    }

    public int getCounter(){
        return counter;
    }

    @Override
    public void close() throws Exception {

    }
}
