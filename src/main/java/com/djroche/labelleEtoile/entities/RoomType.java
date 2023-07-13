package com.djroche.labelleEtoile.entities;

public enum RoomType {
    SINGLE(1, 100),
    DOUBLE(2, 150),
    TRIPLE(3, 200),
    QUADRUPLE(4, 250);

    private final int capacity;
    private final int price;

    RoomType(int capacity, int price) {
        this.capacity = capacity;
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() {
        return price;
    }
}
