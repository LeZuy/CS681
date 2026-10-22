package umbcs681.color;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Images {

    public static Image transform(Image image, Function<Color, Color> adjuster) {
        List<List<Color>> grid = IntStream.range(0, image.getHeight())
            .mapToObj(y -> IntStream.range(0, image.getWidth())
                .mapToObj(x -> image.getColor(x, y))
                .collect(toList()))
            .collect(toList());

        List<List<Color>> adjustedGrid = grid.stream()
            .map(row -> row.stream().map(adjuster).collect(toList()))
            .collect(toList());

        Image adjusted = new Image(image.getWidth(), image.getHeight());
        
        return adjusted;
    }

}
// The Images class provides a static method to transform an image using a given ColorAdjuster.