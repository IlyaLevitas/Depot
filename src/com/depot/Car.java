package com.depot;

public class Car {

    static private Integer cars_count = 0;

    private String type;

    public Car(String _type) {
        type = _type;
        cars_count++;
    }

    public static Integer getCars_count() {
        return cars_count;
    }

    public String getCarType() {
        return type;
    }
}
