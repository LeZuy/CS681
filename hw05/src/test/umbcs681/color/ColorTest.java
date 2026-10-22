package umbcs681.color;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {
    @Test 
    void testColorGettersReturnConstructorValues(){
        Color c = new Color(127, 127, 127);
        assertEquals(127, c.getRedScale()); 
        assertEquals(127, c.getGreenScale()); 
        assertEquals(127, c.getBlueScale()); 
        assertArrayEquals(new int[]{127, 127, 127}, c.toArray());
    }

    @Test 
    void testColorGettersReturnConstructorValuesBlack(){
        Color c = new Color(0, 0, 0);
        assertEquals(0, c.getRedScale()); 
        assertEquals(0, c.getGreenScale()); 
        assertEquals(0, c.getBlueScale()); 
        assertArrayEquals(new int[]{0, 0, 0}, c.toArray());
    }

    @Test 
    void testColorGettersReturnConstructorValuesWhite(){
        Color c = new Color(255, 255, 255);
        assertEquals(255, c.getRedScale()); 
        assertEquals(255, c.getGreenScale()); 
        assertEquals(255, c.getBlueScale()); 
        assertArrayEquals(new int[]{255, 255, 255}, c.toArray());
    }
}
