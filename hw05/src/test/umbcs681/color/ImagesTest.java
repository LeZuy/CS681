package umbcs681.color;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.*;
import java.util.stream.IntStream;

import static Arrays.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class ImagesTest {
    private Image testImage;

    @BeforeEach
    void setImageTestFixture() {
        var matrix = new ArrayList<ArrayList<Color>>();
        matrix.add(asList(new Color(1, 2, 3), new Color(1, 2, 3), new Color(1, 2, 3)));
        matrix.add(asList(new Color(10, 20, 30), new Color(10, 20, 30), new Color(10, 20, 30)));
        matrix.add(asList(new Color(50, 60, 70), new Color(50, 60, 70), new Color(50, 60, 70))); 
        testImage = Image.fromPixels(matrix);
    }

    void testGrayScaler(){
        Image grayScaled = 
            Images.transform(testImage, (c) ->  {           
                int avg = (c.getRedScale() + c.getGreenScale() + c.getBlueScale()) / 3;
                return new Color(avg, avg, avg);
            }
        );

        var targetGrayScaled = new ArrayList<ArrayList<Integer>>();
        targetGrayScaled.add(asList(2, 2, 2));
        targetGrayScaled.add(asList(20, 20, 20));
        targetGrayScaled.add(asList(60, 60, 60)); 

        IntStream.range(0, grayScaled.getHeight()).forEach(y ->
            IntStream.range(0, grayScaled.getWidth()).forEach(x -> {
                Color c = grayScaled.getColor(x, y);
                int g = targetGrayScaled.get(y).get(x);;
                assertEquals(g, c.getRedScale());
                assertEquals(g, c.getGreenScale());
                assertEquals(g, c.getBlueScale());
            }));
    }
}
