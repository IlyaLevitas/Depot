package com.depot;

public class FreightCar extends Car {

    static private Integer freight_cars_count = 0;

    protected int lifting_capacity;

    public FreightCar(String _type, int _lifting_capacity){
        super(_type);
        lifting_capacity = _lifting_capacity;
        freight_cars_count++;
    }

    public static Integer getFreight_cars_count() {
        return freight_cars_count;
    }
}
