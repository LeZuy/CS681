public class Car {
    private String name;
    private double price;
    public Car(String name, double price){
        this.name = name;
        this.price = price;
    }

    public void printName(){
        System.out.print(this.name);
    }

    public double getPrice(){
        return this.price;
    }

    public Car increasePrice(){
        // System.out.print(this.price + 100);
        return new Car(this.name, this.price + 100);
    }
}
