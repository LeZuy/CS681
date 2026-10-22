package umbcs681.color;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class Image {

    private int width;
    private int height;
    private ArrayList<ArrayList<Color>> pixels;

    public Image(int height, int width) {
        this.width = width;
        this.height = height;
        this.pixels = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            pixels.add(new ArrayList<>(width));
            for (int j = 0; j < width; j++) {
                pixels.get(i).add(new Color(0, 0, 0));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor(int x, int y) {
        return pixels.get(y).get(x);
    }
    
    public void setColor(int x, int y, Color color) {
        pixels.get(y).set(x, color);
    }

    public Stream<ArrayList<Color>> stream() {
        return pixels.stream();
    }

    public static Image fromPixels(List<List<Color>> grid) {
        int h = grid.size();
        int w = h == 0 ? 0 : grid.get(0).size();
        Image img = new Image(w, h);

        IntStream.range(0, h).forEach(y ->
            IntStream.range(0, w).forEach(x ->
                img.setColor(x, y, grid.get(y).get(x))));

        return img;
    }
}
