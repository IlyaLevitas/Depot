package com.depot;

public class PassengerCar extends Car {

    static private Integer passenger_cars_count = 0;

    protected int seats_count;

    public PassengerCar(String _type, int _seats_count) {
        super(_type);
        seats_count = _seats_count;
        passenger_cars_count++;
    }

    public static Integer getPassenger_cars_count() {
        return passenger_cars_count;
    }
}


