package umbcs681.color;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.IntStream;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static java.util.stream.Collectors.toCollection;

public class ImageTest {

    private Image testImage;

    @BeforeEach
    void setImageTestFixture() {
        testImage = new Image(1280, 720); 
    }

    @Test 
    void testZeroSizedImage() {
        Image im = new Image(0, 0);
        assertEquals(0, im.getWidth());
        assertEquals(0, im.getHeight());
    }

    @Test 
    void testImageGettersReturnConstructorValues(){

        int height = testImage.getHeight();
        int width = testImage.getWidth();
        
        ArrayList<ArrayList<Color>> imgColorGrid = IntStream.range(0, height)
            .mapToObj(
                i -> Stream.generate(() -> new Color(0, 0, 0))
                .limit(width)
                .collect(toCollection(() -> new ArrayList<>(width))))
            .collect(toCollection(() -> new ArrayList<>(height)));
        
        IntStream.range(0, testImage.getHeight()).forEach(y ->
            IntStream.range(0, testImage.getWidth()).forEach(x -> {
                Color c = testImage.getColor(x, y);
                assertEquals(0, c.getRedScale());
                assertEquals(0, c.getGreenScale());
                assertEquals(0, c.getBlueScale());
            }));
    }

    @Test 
    void testSetColorAtXY(){
        Color targetColor = new Color(255, 0, 127);
        testImage.setColor(0, 1, new Color(255, 0, 127));
        Color pixelAt01 = testImage.getColor(0,1);

        IntStream.range(0, testImage.getHeight()).forEach(y ->
            IntStream.range(0, testImage.getWidth()).forEach(x -> {
                if(x == 0 && y == 1){
                    assertEquals(targetColor.getRedScale(), testImage.getColor(x, y).getRedScale());
                    assertEquals(targetColor.getRedScale(), testImage.getColor(x, y).getRedScale());
                    assertEquals(targetColor.getRedScale(), testImage.getColor(x, y).getRedScale());
                }
                else{
                    Color c = testImage.getColor(x, y);
                    assertEquals(0, c.getRedScale());
                    assertEquals(0, c.getGreenScale());
                    assertEquals(0, c.getBlueScale());
                }
            }));
    }

    @Test 
    void testSetColorAtXYTwice(){
        Color targetColor = new Color(255, 0, 127);
        testImage.setColor(0, 1, new Color(255, 255, 255));
        testImage.setColor(0, 1, new Color(255, 0, 127));
        Color pixelAt01 = testImage.getColor(0,1);
        assertEquals(targetColor.getRedScale(), pixelAt01.getRedScale());
        assertEquals(targetColor.getGreenScale(), pixelAt01.getGreenScale());
        assertEquals(targetColor.getBlueScale(), pixelAt01.getBlueScale());
    }
}
