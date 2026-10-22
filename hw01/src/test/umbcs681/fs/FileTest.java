package umbcs681.fs;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class FileTest {

    private static FileSystem fs;
    
    @BeforeAll
    public static void setupFS(){
        fs = TestFixtureInitializer.createFS();
    }

    @Test
    public void testFileAEqualityCheck(){
        File a = fs.getRootDirs().get(0).getSubDirectories().get(0).getSubDirectories().get(0).getFiles().get(0);
        String[] testFile = {"A.java", "15", "src"};
        assertArrayEquals(a.toStringArray(),testFile);
    }

    @Test
    public void testFileBEqualityCheck(){
        File b = fs.getRootDirs().get(0).getSubDirectories().get(0).getSubDirectories().get(0).getFiles().get(1);
        String[] testFile = {"B.java", "15", "src"};
        assertArrayEquals(b.toStringArray(),testFile);
    }

    @Test   
    public void testFileReadmeEqualityCheck(){
        File readme = fs.getRootDirs().get(0).getFiles().get(0);
        String[] testFile = {"readme.md", "10", "umbcs681"};
        assertArrayEquals(readme.toStringArray(),testFile);
    }

    @Test 
    public void testFileBuildEqualityCheck(){
        File build = fs.getRootDirs().get(0).getSubDirectories().get(0).getFiles().get(0);
        String[] testFile = {"build.xml", "20", "hw01"};
        assertArrayEquals(build.toStringArray(),testFile);
    }    

    @Test
    public void testFileAIsDirectory(){
        File a = fs.getRootDirs().get(0).getSubDirectories().get(0).getSubDirectories().get(0).getFiles().get(0);
        assertFalse(a.isDirectory());
    }
}
