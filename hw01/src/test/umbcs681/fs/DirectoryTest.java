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
        assertArrayEquals(root.toStringArray(),testDir);
    }

    @Test 
    public void testDirHw1EqualityCheck(){
        Directory hw1 = fs.getRootDirs().get(0).getSubDirectories().get(0);
        String[] testDir = {"hw01", "0", "umbcs681"};
        assertArrayEquals(hw1.toStringArray(),testDir);
    }

    @Test 
    public void testDirSrcEqualityCheck(){
        Directory src = fs.getRootDirs().get(0).getSubDirectories().get(0).getSubDirectories().get(0);
        String[] testDir = {"src", "0", "hw01"};
        assertArrayEquals(src.toStringArray(),testDir);
    }

    @Test
    public void testNewDirHw2ConstructorAppendedToParent(){
        Directory root = fs.getRootDirs().get(0);
        Directory hw2 = new Directory(root, "hw02", LocalDateTime.now());
        String[] hw2Info = {"hw02", "0", "umbcs681"};     
        assertArrayEquals(root.getChildren().get(2).toStringArray(),hw2Info); 
        root.getChildren().remove(hw2);  
    }

    @Test 
    public void testUmbcs681GetSubDirReturn1SubDir(){
        Directory root = fs.getRootDirs().get(0);
        assertEquals(root.getSubDirectories().size(), 1);
    }

    @Test 
    public void testUmbcs681GetFileReturn1File(){
        Directory root = fs.getRootDirs().get(0);
        assertEquals(root.getFiles().size(), 1);
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
        assertEquals(root.countChildren(), 2);
    }

    @Test
    public void testUmbcs681GetChildrenReturnHw1andReadme(){
        Directory root = fs.getRootDirs().get(0);
        String[] hw1Info = {"hw01", "0", "umbcs681"};
        String[] readmeInfo = {"readme.md", "10", "umbcs681"};
        assertArrayEquals(root.getChildren().get(0).toStringArray(),hw1Info);
        assertArrayEquals(root.getChildren().get(1).toStringArray(),readmeInfo);
    }
    
    @Test
    public void testUmbcs681GetTotalSizeReturn60(){
        Directory root = fs.getRootDirs().get(0);
        assertEquals(root.getTotalSize(), 60);
    }
}

