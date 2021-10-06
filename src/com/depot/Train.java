package com.depot;

import java.util.ArrayList;

public class Train {

    private Integer seats_total = 0;
    private Integer total_lifting_capacity = 0;

    public ArrayList<Car> cars = new ArrayList<Car>();

    public void addCar(Car car) {
        cars.add(car);
        countSeatsAndLiftingCapacity(car);
    }

    private void countSeatsAndLiftingCapacity(Car car) {
        if (car instanceof PassengerCar) {
            seats_total += ((PassengerCar) car).seats_count;
        }
        else if (car instanceof  FreightCar) {
            total_lifting_capacity += ((FreightCar) car).lifting_capacity;
        }
    }

    public Integer getSeats_total() {
        return seats_total;
    }

    public Integer getTotal_lifting_capacity() {
        return total_lifting_capacity;
    }
}
