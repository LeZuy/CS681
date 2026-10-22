package umbcs681.color;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class BinarizationAdjusterTest {

    private static final int THRESHOLD = 125;

    @Test
    public void testBinarizationAdjusterLess125() {
        Image img = new Image(1, 1);
        img.setColor(0, 0, new Color(100, 100, 100)); // < 125

        Image adjusted = Images.transform(img, color -> {
            int avg = (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return avg <= THRESHOLD
                ? new Color(0, 0, 0)
                : new Color(255, 255, 255);
            }
        );

        assertArrayEquals(new int[]{0, 0, 0}, adjusted.getColor(0, 0).toArray());
    }

    @Test
    public void testBinarizationAdjusterEqual125() {
        Image img = new Image(1, 1);
        img.setColor(0, 0, new Color(125, 125, 125)); // = 125

        Image adjusted = Images.transform(img, color -> {
            int avg = (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return avg <= THRESHOLD
                ? new Color(0, 0, 0)
                : new Color(255, 255, 255);
            }
        );

        assertArrayEquals(new int[]{0, 0, 0}, adjusted.getColor(0, 0).toArray());
    }

    @Test
    public void testBinarizationAdjusterGreater125() {
        Image img = new Image(1, 1);
        img.setColor(0, 0, new Color(200, 200, 200)); // > 125

        Image adjusted = Images.transform(img, color -> {
            int avg = (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return avg <= THRESHOLD
                ? new Color(0, 0, 0)
                : new Color(255, 255, 255);
            }
        );

        assertArrayEquals(new int[]{255, 255, 255}, adjusted.getColor(0, 0).toArray());
    }

    @Test
    public void testBinarizationAdjusterDidNotChangeOriginalImage() {
        Image img = new Image(1, 1);
        Color originalColor = new Color(100, 150, 200);
        img.setColor(0, 0, originalColor);

        Image adjusted = Images.transform(img, color -> {
            int avg = (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return avg <= THRESHOLD
                ? new Color(0, 0, 0)
                : new Color(255, 255, 255);
            }
        );


        assertArrayEquals(new int[]{100, 150, 200}, img.getColor(0, 0).toArray());
    }


    @Test
    public void testBinarizationEdgeCases0() {
        Image img = new Image(1, 1);
        img.setColor(0, 0, new Color(0, 0, 0));

        Image adjusted = Images.transform(img, color -> {
            int avg = (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return avg <= THRESHOLD
                ? new Color(0, 0, 0)
                : new Color(255, 255, 255);
            }
        );


        assertArrayEquals(new int[]{0, 0, 0}, adjusted.getColor(0, 0).toArray());
    }

    @Test
    public void testBinarizationEdgeCases255() {
        Image img = new Image(1, 1);
        img.setColor(0, 0, new Color(255, 255, 255));

        Image adjusted = Images.transform(img, color -> {
            int avg = (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return avg <= THRESHOLD
                ? new Color(0, 0, 0)
                : new Color(255, 255, 255);
            }
        );


        assertArrayEquals(new int[]{255, 255, 255}, adjusted.getColor(0, 0).toArray());
    }
}
