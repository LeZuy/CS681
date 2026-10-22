package umbcs681.fs;

import java.time.LocalDateTime;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class FileTest {

    private static FileSystem fs;
    
    @BeforeAll
    public static void setupFS(){
        fs = TestFixtureInitializer.createFS();
    }

    @Test
    void gettersReturnConstructorValues() {
        LocalDateTime createdTime = LocalDateTime.of(2026, 1, 2, 3, 4);
        Directory parent = new Directory(null, "parent", createdTime);
        File file = new File(parent, "notes.txt", 12, createdTime);

        assertEquals("notes.txt", file.getName());
        assertEquals(12, file.getSize());
        assertSame(parent, file.getParent());
        assertEquals(createdTime, file.getCreationTime());
    }

    @Test
    public void testFileAEqualityCheck(){
        File a = fs.getRootDirs().get(0).getSubDirectories().get(0).getSubDirectories().get(0).getFiles().get(0);
        String[] testFile = {"A.java", "15", "src"};
        assertArrayEquals(testFile, a.toStringArray());
    }

    @Test
    public void testFileBEqualityCheck(){
        File b = fs.getRootDirs().get(0).getSubDirectories().get(0).getSubDirectories().get(0).getFiles().get(1);
        String[] testFile = {"B.java", "10", "src"};
        assertArrayEquals(testFile, b.toStringArray());
    }

    @Test   
    public void testFileReadmeEqualityCheck(){
        File readme = fs.getRootDirs().get(0).getFiles().get(0);
        String[] testFile = {"readme.md", "10", "umbcs681"};
        assertArrayEquals(testFile, readme.toStringArray());
    }

    @Test 
    public void testFileBuildEqualityCheck(){
        File build = fs.getRootDirs().get(0).getSubDirectories().get(0).getFiles().get(0);
        String[] testFile = {"build.xml", "20", "hw01"};
        assertArrayEquals(testFile, build.toStringArray());
    }    

    @Test
    public void testFileAIsDirectory(){
        File a = fs.getRootDirs().get(0).getSubDirectories().get(0).getSubDirectories().get(0).getFiles().get(0);
        assertFalse(a.isDirectory());
    }

    @Test
    public void testFileAExtensionJava(){
        File b = fs.getRootDirs().get(0).getSubDirectories().get(0).getSubDirectories().get(0).getFiles().get(1);
        assertEquals("java", b.getExtension());
    }

    @Test
    public void testFileBuildExtensionJava(){
        File build = fs.getRootDirs().get(0).getSubDirectories().get(0).getFiles().get(0);
        assertEquals("xml", build.getExtension());
    }

    @Test
    public void testFileReadmeExtensionJava(){
        File readme = fs.getRootDirs().get(0).getFiles().get(0);
        assertEquals("md", readme.getExtension());
    }
}
