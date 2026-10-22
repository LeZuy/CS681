package umbcs681.car;
import java.util.List;
import java.util.ArrayList;
public class TestFixtureInitializer {

    public static ArrayList<Car>  createUsedCars(){

        return new ArrayList<Car>(List.of(
            new Car("Toyota", "A", 16100, 40000, 2018),
            new Car("Honda",  "B", 16000, 38000, 2019),
            new Car("Ford",   "C", 16500, 41000, 2020),
            new Car("Mazda",  "D", 13000, 30000, 2022),
            new Car("Tesla",  "E", 17000, 45000, 2024)
        ));
    }
}
