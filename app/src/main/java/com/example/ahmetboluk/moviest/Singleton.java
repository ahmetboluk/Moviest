package com.example.ahmetboluk.moviest;

public class Singleton
{
    private static Singleton instance = null;
    private int value;

    private Singleton()
    {

    }

    public synchronized static Singleton getInstance()
    {
        if( instance == null )
            instance = new Singleton();
        return instance;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException("Klonlama yapılamaz!!!");
    }

    public int getDeger() {
        return value;
    }

    public void setDeger(int deger) {
        this.value = deger;
    }

}