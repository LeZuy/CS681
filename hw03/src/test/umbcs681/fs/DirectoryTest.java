package umbcs681.fs;

import java.time.LocalDateTime;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DirectoryTest {
    
    private static FileSystem fs;
    
    @BeforeAll
    public static void setupFS(){
        fs = TestFixtureInitializer.createFS();
    }

    @Test 
    public void testDirUmbcs681EqualityCheck(){
        Directory root = fs.getRootDirs().get(0);
        String[] testDir = {"umbcs681", "0", "null"};
        assertArrayEquals(testDir, root.toStringArray());
    }

    @Test 
    public void testDirHw1EqualityCheck(){
        Directory hw1 = fs.getRootDirs().get(0).getSubDirectories().get(0);
        String[] testDir = {"hw01", "0", "umbcs681"};
        assertArrayEquals(testDir, hw1.toStringArray());
    }

    @Test 
    public void testDirSrcEqualityCheck(){
        Directory src = fs.getRootDirs().get(0).getSubDirectories().get(0).getSubDirectories().get(0);
        String[] testDir = {"src", "0", "hw01"};
        assertArrayEquals(testDir, src.toStringArray());
    }

    @Test
    public void testNewDirHw2ConstructorAppendedToParent(){
        Directory root = fs.getRootDirs().get(0);
        Directory hw2 = new Directory(root, "hw02", LocalDateTime.now());
        String[] hw2Info = {"hw02", "0", "umbcs681"};     
        assertArrayEquals(hw2Info, root.getChildren().get(2).toStringArray());
        root.getChildren().remove(hw2);  
    }

    @Test 
    public void testUmbcs681GetSubDirReturn1SubDir(){
        Directory root = fs.getRootDirs().get(0);
        assertEquals(1, root.getSubDirectories().size());
    }

    @Test 
    public void testUmbcs681GetFileReturn1File(){
        Directory root = fs.getRootDirs().get(0);
        assertEquals(1, root.getFiles().size());
    }

    @Test
    public void testUmbcs681IsDirectory(){
        Directory root = fs.getRootDirs().get(0);
        assertTrue(root.isDirectory());
    }

    @Test
    public void testReadmeIsDirectory(){
        File readme = fs.getRootDirs().get(0).getFiles().get(0);
        assertFalse(readme.isDirectory());
    }

    @Test 
    public void testUmbcs681CountChildren(){
        Directory root = fs.getRootDirs().get(0);
        assertEquals(2, root.countChildren());
    }

    @Test
    public void testUmbcs681GetChildrenReturnHw1andReadme(){
        Directory root = fs.getRootDirs().get(0);
        String[] hw1Info = {"hw01", "0", "umbcs681"};
        String[] readmeInfo = {"readme.md", "10", "umbcs681"};
        assertArrayEquals(hw1Info, root.getChildren().get(0).toStringArray());
        assertArrayEquals(readmeInfo, root.getChildren().get(1).toStringArray());
    }
    
    @Test
    public void testUmbcs681GetTotalSizeReturn60(){
        Directory root = fs.getRootDirs().get(0);
        assertEquals(55, root.getTotalSize());
    }
}
