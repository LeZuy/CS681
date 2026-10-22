package umbcs681.car;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.DoubleSummaryStatistics;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingDouble;

public class CarTest {
    
    private static ArrayList<Car> usedCars;

    @BeforeAll
    public static void setup() {
        usedCars = TestFixtureInitializer.createUsedCars();
    }

    @Test
    void testCarGettersReturnConstructorValues() {
        Car c = new Car("Toyota", "A", 16100, 40000, 2018);   
        assertEquals("Toyota", c.getModel());
        assertEquals("A", c.getMake());
        assertEquals(16100f, c.getPrice());
        assertEquals(40000, c.getMileage());
        assertEquals(2018, c.getYear());
        assertEquals(0, c.getDominationCount());
    }

    @Test
    public void testPriceComparator() {
        String[] expectedCarsByPrice = {"D", "B", "A", "C", "E"};
        String[] sortedCarsByPrice = usedCars.stream()
            .sorted(Comparator.comparing(Car::getPrice))
            .map(Car::getMake)
            .toArray(String[]::new);
        assertArrayEquals(expectedCarsByPrice, sortedCarsByPrice);
    }

    @Test
    public void testYearComparator() {
        String[] expectedCarsByYear = {"E", "D", "C", "B", "A"};
        String[] sortedCarsByYear = usedCars.stream()
            .sorted((c1, c2) -> c2.getYear() - c1.getYear())
            .map(Car::getMake)
            .toArray(String[]::new);
        assertArrayEquals(expectedCarsByYear, sortedCarsByYear);
    }

    @Test
    public void testMileageComparator() {
        String[] expectedCarsByMileage = {"D", "B", "A", "C", "E"};
        String[] sortedCarsByMileage = usedCars.stream()
            .sorted(Comparator.comparing(Car::getMileage))
            .map(Car::getMake)
            .toArray(String[]::new);
        assertArrayEquals(expectedCarsByMileage, sortedCarsByMileage);
    }

    @Test
    void testDominateWithLowerPrice() {
        assertTrue(new Car("Toyota", "X", 90f, 100, 2020).dominates(new Car("Mazda", "B", 100f, 100, 2020)));
    }

    @Test
    void testDominateWithLowerMillage() {
        assertTrue(new Car("Toyota", "X",  100f, 90, 2020).dominates(new Car("Mazda", "B", 100f, 100, 2020)));
    }

    @Test
    void testDominateWithEarlierYear() {
        assertTrue(new Car("Toyota", "X",  100f, 90, 2021).dominates(new Car("Mazda", "B", 100f, 100, 2020)));
    }

    @Test
    void testNoCarsDominateWithMake() {
        assertFalse(new Car("Toyota", "B", 100f, 100, 2020).dominates(new Car("Mazda", "X", 100f, 100, 2020)));
        assertFalse(new Car("Mazda", "X", 100f, 100, 2020).dominates(new Car("Toyota", "B", 100f, 100, 2020)));
    }

    @Test
    void testDominationSetter() {
        Car c = new Car("Mazda", "X", 100, 100, 2020);
        c.setDominationCount(99);
        c.computeDominationCount(new ArrayList<>());
        assertEquals(0, c.getDominationCount());
    }

    @Test
    void testDominationCarsSameValue() {
        Car c1 = new Car("Mazda", "X", 100, 100, 2020);
        Car c2 = new Car("Mazda", "Y", 100, 100, 2020);
        ArrayList<Car> cars = new ArrayList<>(List.of(c1, c2));
        c1.computeDominationCount(cars);
        c2.computeDominationCount(cars);
        assertEquals(0, c1.getDominationCount());
        assertEquals(0, c2.getDominationCount());
    }

    @Test
    void testDominateCarOutOfList() {
        Car target = new Car("Vinfast", "V", 16000, 39000, 2019);
        target.computeDominationCount(usedCars);
        assertEquals(2, target.getDominationCount());
    }

    @Test
    void testDominationCountIsIdempotent() {
        Car a = usedCars.get(0);
        a.computeDominationCount(usedCars);
        a.computeDominationCount(usedCars);
        assertEquals(2, a.getDominationCount());
    }

    @Test
    void testCarDominateItself() {
        Car c = new Car("Toyota", "X",100, 100, 2020);
        c.computeDominationCount(new ArrayList<>(List.of(c)));
        assertEquals(0, c.getDominationCount());
    }

    // /*
    // Domination count:
    // A: 2
    // B: 1
    // C: 1
    // D: 0
    // E: 0
    // */

    @Test
    public void testParetoComparator() {
        usedCars.forEach(car -> car.computeDominationCount(usedCars));
        String[] expectedCarsByPareto = {"D", "E", "B", "C", "A"};
        String[] sortedCarsByPareto = usedCars.stream()
            .sorted(Comparator.comparing(Car::getDominationCount)
                            .thenComparing(Car::getMake))
            .map(Car::getMake)
            .toArray(String[]::new);
        assertArrayEquals(expectedCarsByPareto, sortedCarsByPareto);
    }

    @Test
    void testParetoZeroCounts() {
        ArrayList<Car> cars = new ArrayList<>(List.of(
            new Car("Mazda", "A",100, 200, 2020),  
            new Car("Toyota", "B",200, 100, 2020),  
            new Car("Honda", "C",150, 150, 2022))); 
        cars.forEach(c -> c.computeDominationCount(cars));
        cars.forEach(c -> assertEquals(0, c.getDominationCount(), c.getMake()));
    }

    @Test
    void testCarsSummarizePriceByYear(){
        ArrayList<Car> moreUsedCars = new ArrayList<Car>(usedCars);
        moreUsedCars.add(new Car("Toyota", "F", 10000, 20000, 2020));
        moreUsedCars.add(new Car("Volvo", "G", 12000, 20000, 2020));
        moreUsedCars.add(new Car("Vinfast", "H", 17000, 28000, 2022));
        moreUsedCars.add(new Car("Tesla", "I", 17000, 8000, 2024));

        Map<Integer, DoubleSummaryStatistics> summaryCarPriceByYearTarget =
            Map.of(
                2018, new DoubleSummaryStatistics(1, 16100, 16100, 16100),
                2019, new DoubleSummaryStatistics(1, 16000, 16000, 16000),
                2020, new DoubleSummaryStatistics(3, 10000, 16500, 38500),
                2022, new DoubleSummaryStatistics(2, 13000, 17000, 30000),
                2024, new DoubleSummaryStatistics(2, 17000, 17000, 34000)
            );

        Map<Integer, DoubleSummaryStatistics> summaryCarPriceByYear = 
            moreUsedCars.stream().collect(
                groupingBy(Car::getYear,
                            summarizingDouble(Car::getPrice)));

        summaryCarPriceByYear.forEach((key, stats) -> {
                DoubleSummaryStatistics e = summaryCarPriceByYearTarget.get(key);
                assertEquals(e.getCount(), stats.getCount());     
                assertEquals(e.getSum(), stats.getSum());
                assertEquals(e.getMin(), stats.getMin());
                assertEquals(e.getMax(), stats.getMax());
                assertEquals(e.getAverage(), stats.getAverage());
            });
    }

    @Test
    void  testCarsEmptyListSummary(){
        ArrayList<Car> none = new ArrayList<Car>();

        Map<Integer, DoubleSummaryStatistics> byYear = none.stream()
            .collect(groupingBy(Car::getYear, summarizingDouble(Car::getPrice)));

        assertTrue(byYear.isEmpty());

        DoubleSummaryStatistics stats = none.stream().collect(summarizingDouble(Car::getPrice));

        assertEquals(0, stats.getCount());
        assertEquals(0.0, stats.getSum());
        assertEquals(Double.POSITIVE_INFINITY, stats.getMin());
        assertEquals(Double.NEGATIVE_INFINITY, stats.getMax());
        assertEquals(0.0, stats.getAverage());
    }

    void testCarsSummaryExtreme(){
        ArrayList<Car> cars = new ArrayList<>(List.of(
            new Car("M", "A", 0f, 1000, 2020),
            new Car("M", "B", -500f, 1000, 2020),
            new Car("M", "C", Float.MAX_VALUE, 1000, 2020),
            new Car("M", "D", Float.MAX_VALUE, 1000, 2020)
        ));

        DoubleSummaryStatistics s = cars.stream()
            .collect(groupingBy(Car::getYear, summarizingDouble(Car::getPrice))).get(2020);

        assertEquals(4, s.getCount());
        assertEquals(-500.0, s.getMin());
        assertEquals((double) Float.MAX_VALUE, s.getMax());
        assertEquals(2.0 * Float.MAX_VALUE - 500, s.getSum(), 1e24);
    }
}
