import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        List<Car> aListOfCars = new ArrayList<>();
        aListOfCars.add(new Car("BMV", 1000));
        aListOfCars.add(new Car("Volvo", 2000));
        aListOfCars.add(new Car("Vinfast", 1500));
        Collections.sort(aListOfCars, new CarPriceComparator()); // take a Comparator class

        for(Car car : aListOfCars)
            car.printName();
        Collections.sort(aListOfCars, (Car o1, Car o2) -> {return (int)(o1.getPrice() - o2.getPrice());});
        long count = aListOfCars.stream().filter( (Car car)-> car.getPrice()<2000 ).count();
        System.out.print(count);

        Car[] cars= {new Car("BMV", 1000), new Car("Volvo", 2000), new Car("Vinfast", 1500)};
        List<Car> collected = Stream.of(cars).map((Car car) -> car.increasePrice()).collect(Collectors.toList());
        for(Car car : collected) System.out.print(car.getPrice()); 
        Stream<Car> aStreamOfCars = Stream.concat(Stream.of(cars), aListOfCars.stream());
        List<Car> anotherListOfCars = aStreamOfCars.collect(Collectors.toList());
        Stream<Car> anotherStreamOfCars = anotherListOfCars.stream();
        Stream<Car> anotheranotherStreamOfCars = anotherListOfCars.stream();
        anotherListOfCars.forEach(Car::printName);
        for(Car car : anotherStreamOfCars.collect(Collectors.toList()))
            car.printName();
        Stream.generate(() -> 10).limit(5).forEach(System.out::println);
        anotheranotherStreamOfCars.map((Car car) -> car.getPrice()).forEach(System.out::println);
        Stream<Integer> priceOfCarsByYear = Stream.iterate(1000, (i) -> i+10).limit(5);
        priceOfCarsByYear.forEach(System.out::println);
        Stream<Car> anotheranotheranotherStreamOfCars = anotherListOfCars.stream().sorted((Car o1, Car o2) -> {return (int)(o1.getPrice() - o2.getPrice());});
        anotheranotheranotherStreamOfCars.collect(Collectors.toCollection(ArrayList::new)).forEach(Car::printName);

        HashMap<String, String> transcript = new HashMap<>();
        transcript.put("CS680", "A");
        transcript.put("CS681", "B");
        transcript.put("CS682", "A");
        // double gpa = transcript.values().stream().mapToDouble((String grade) -> {if (grade.equals("A")) return 3.6;})
        // Map<Boolean, List<Car>> carsByPrice = aListOfCars.stream().collect(partitioningBy((Car car) -> car.getPrice() > 5000));
    }   
}