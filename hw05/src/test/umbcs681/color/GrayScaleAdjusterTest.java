package umbcs681.color;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class GrayScaleAdjusterTest {

    @Test
    public void testGrayScaleAdjusterToAvg() {
        Image img = new Image(1, 1);
        img.setColor(0, 0, new Color(60, 120, 180)); //avg = 120

        Image adjusted = Images.transform(img, color -> {
            int avg = (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return new Color(avg, avg, avg);
        });

        assertArrayEquals(new int[]{120, 120, 120}, adjusted.getColor(0, 0).toArray());
    }

    @Test
    public void testGrayScaleAdjusterAlreadyAvg() {
        Image img = new Image(1, 1);
        img.setColor(0, 0, new Color(120, 120,120 )); //avg = 120

        Image adjusted = Images.transform(img, color -> {
            int avg = (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return new Color(avg, avg, avg);}
        );

        assertArrayEquals(new int[]{120, 120, 120}, adjusted.getColor(0, 0).toArray());
    }

    @Test
    public void testGrayScaleAdjuster2x2Image() {
        Image img = new Image(2, 2);
        img.setColor(0, 0, new Color(30, 60, 90)); // 60
        img.setColor(0, 1, new Color(90, 90, 90)); // 90
        img.setColor(1, 0, new Color(0, 0, 0)); // 0
        img.setColor(1, 1, new Color(255, 255, 255)); // 255

        Image adjusted = Images.transform(img, color -> {
            int avg = (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return new Color(avg, avg, avg);
            }
        );

        assertArrayEquals(new int[]{60, 60, 60}, adjusted.getColor(0, 0).toArray());
        assertArrayEquals(new int[]{90, 90, 90}, adjusted.getColor(0, 1).toArray());
        assertArrayEquals(new int[]{0, 0, 0}, adjusted.getColor(1, 0).toArray());
        assertArrayEquals(new int[]{255, 255, 255}, adjusted.getColor(1, 1).toArray());
    }

    @Test
    public void testGrayScaleAdjusterDidNotChangeOriginalImage() {
        Image img = new Image(1, 1);
        Color originalColor = new Color(100, 150, 200);
        img.setColor(0, 0, originalColor);

        Image adjusted = Images.transform(img, color -> {
            int avg = (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return new Color(avg, avg, avg);
            }
        );

        assertArrayEquals(new int[]{100, 150, 200}, img.getColor(0, 0).toArray());
    }
}
