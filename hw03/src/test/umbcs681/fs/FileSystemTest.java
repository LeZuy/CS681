package umbcs681.fs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class FileSystemTest {
    
    private static FileSystem fs;
    
    @BeforeAll
    public static void setupFS(){
        fs = TestFixtureInitializer.createFS();
    }

    @Test
    public void testGetRootDirs(){
        assertEquals(1, fs.getRootDirs().size());
    }

    @Test
    public void testSingletonReturnsSameInstance() {
        FileSystem testFS = FileSystem.getFileSystem();
        assertSame(fs, testFS);
    }

    @Test
    public void testGetRootDirsReturnsReturnUmbcs681(){
        Directory root = fs.getRootDirs().get(0);
        assertEquals("umbcs681", root.getName());
    }

    @Test
    public void testAppendRootDirsWithANewRoot(){
        Directory testRoot = new Directory(null, "umbcs681", null);
        fs.appendRootDirs(testRoot);
        assertEquals(2, fs.getRootDirs().size());
        fs.getRootDirs().remove(testRoot);
    }

}
