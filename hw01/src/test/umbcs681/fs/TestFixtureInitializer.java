package umbcs681.fs;

import java.time.LocalDateTime;

public class TestFixtureInitializer {

    public static FileSystem createFS(){

        FileSystem fs =  FileSystem.getFileSystem();     
        
        if(fs.getRootDirs().size() > 0){
            return fs;
        } else {
            Directory root = new Directory(null, "umbcs681", LocalDateTime.of(2026, 10, 10, 20, 0));
            Directory hw1 = new Directory(root, "hw01", LocalDateTime.of(2026, 10, 10, 20, 3));
            Directory src = new Directory(hw1, "src", LocalDateTime.of(2026, 10, 10, 20, 6));
        
            File readme = new File(root, "readme.md", 10, LocalDateTime.of(2026, 10, 11, 20, 0));
            File build = new File(hw1, "build.xml", 20, LocalDateTime.of(2026, 10, 11, 19, 0));
            File a = new File(src, "A.java", 15, LocalDateTime.of(2026, 10, 10, 10, 0));
            File b = new File(src, "B.java", 15, LocalDateTime.of(2026, 10, 10, 10, 0));

            Link rm = new Link(hw1, "rm", LocalDateTime.now(), readme);
    
            fs.appendRootDirs(root);
        }    
        return fs;
    }
}
