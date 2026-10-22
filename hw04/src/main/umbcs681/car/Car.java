package umbcs681.car;

import java.util.ArrayList;

public class Car {
    private String model, make;
    private int mileage, year, dominationCount;
    private float price;

    public Car(String model, String make, float price, int mileage, int year) {
        this.model = model;
        this.make = make;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public float getPrice() {
        return price;
    }

    public void setDominationCount(int count) {
        this.dominationCount = count;
    }

    public int getDominationCount() {
        return dominationCount;
    }

    // Helper method for Pareto domination
    public boolean dominates(Car that) {
        boolean better = false;
        if (this.getPrice() > that.getPrice()) return false;
        if (this.getMileage() > that.getMileage()) return false;
        if (this.getYear() < that.getYear()) return false;

        if (this.getPrice() < that.getPrice()) better = true;
        if (this.getMileage() < that.getMileage()) better = true;
        if (this.getYear() > that.getYear()) better = true;

        return better;
    }  

    public void computeDominationCount(ArrayList<Car> cars) {
        int count = 0;
        for (Car other : cars) {
            if (other != this && other.dominates(this)) {
                count++;
            }
        }
        this.dominationCount = count;
    }
}
