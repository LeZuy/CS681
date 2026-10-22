package umbcs681.fs;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LinkTest {

    private static FileSystem fs;
    
    @BeforeAll
    public static void setupFS(){
        fs = TestFixtureInitializer.createFS();
    }

    @Test 
    public void testLinkRmEquality(){
        Link link = fs.getRootDirs().get(0).getSubDirectories().get(0).getLinks().get(0);
        String[] testLink = {"rm", "0", "hw01"};
        assertArrayEquals(link.toStringArray(),testLink);
    }

    @Test 
    public void testIsDirectoryFalse(){
        Link link = fs.getRootDirs().get(0).getSubDirectories().get(0).getLinks().get(0);
        assertFalse(link.isDirectory());
    }

    @Test
    public void testGetTarget(){
        Link link = fs.getRootDirs().get(0).getSubDirectories().get(0).getLinks().get(0);
        File target = (File) link.getTarget();
        String[] testTarget = {"readme.md", "10", "umbcs681"};
        assertArrayEquals(target.toStringArray(),testTarget);
    }
}
