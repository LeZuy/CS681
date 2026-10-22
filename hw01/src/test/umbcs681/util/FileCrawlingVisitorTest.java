package umbcs681.util;

import umbcs681.fs.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;

public class FileCrawlingVisitorTest {

    private static FileSystem fs;
    
    @BeforeAll
    public static void setupFS(){
        fs = TestFixtureInitializer.createFS();
    }
    
    @Test
    public void testFileCrawlingVisitorConstructorReturn0File(){
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        assertEquals(visitor.files().count(), 0);
    }

    @Test
    public void testFileCrawlingVisitorRootDirReturn4Files(){
        Directory root = fs.getRootDirs().get(0);
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        root.accept(visitor);
        assertEquals(visitor.files().count(), 4);
    }

    @Test
    public void testFileCrawlingVisitorHw1DirReturn3Files(){
        Directory hw1 = fs.getRootDirs().get(0).getSubDirectories().get(0);
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        hw1.accept(visitor);
        assertEquals(visitor.files().count(), 3);
    }

    @Test
    public void testFileCrawlingVisitorGetAFile(){

        Directory root = fs.getRootDirs().get(0);

        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        root.accept(visitor);

        File a = visitor.files().filter((f) -> f.getName().equals("A.java")).collect(Collectors.toList()).get(0);
        String[] testATarget = {"A.java", "15", "src"};

        assertArrayEquals(a.toStringArray(), testATarget);
    }

    @Test
    public void testFileCrawlVisiterGetFileAfterOct10(){

        LocalDateTime oct10DateTime = LocalDateTime.of(2026, 10, 10, 23, 59);

        Directory root = fs.getRootDirs().get(0);

        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        root.accept(visitor);

        List<String[]> filesCreatedAfterTargetList = Stream.of(
            new String[]{"readme.md", "10", "umbcs681"},
            new String[]{"build.xml", "20", "hw01"}
        ).sorted(Comparator.comparing(info -> info[0]))
            .collect(Collectors.toList());

        Stream<File> filesCreatedAfter = visitor.files()
            .filter((f) -> f.getCreationTime().isAfter(oct10DateTime));

        List<String[]> filesCreatedAfterList = filesCreatedAfter
            .map(File::toStringArray)
            .sorted(Comparator.comparing(info -> info[0]))
            .collect(Collectors.toList());

        assertEquals(filesCreatedAfterList.size(), filesCreatedAfterTargetList.size());

        for(int i = 0; i < filesCreatedAfterList.size(); i++)
            assertArrayEquals(filesCreatedAfterList.get(i), filesCreatedAfterTargetList.get(i));
    }

    @Test
    public void testFileCrawlVisiterGetJavaFile(){

        Directory root = fs.getRootDirs().get(0);

        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        root.accept(visitor);

        List<String[]> filesCreatedAfterTargetList = Stream.of(
            new String[]{"A.java", "15", "src"},
            new String[]{"B.java", "15", "src"}
        ).sorted(Comparator.comparing(info -> info[0]))
            .collect(Collectors.toList());

        Stream<File> filesCreatedAfter = visitor.files()
            .filter((f) -> f.getName().endsWith(".java"));

        List<String[]> filesCreatedAfterList = filesCreatedAfter
            .map(File::toStringArray)
            .sorted(Comparator.comparing(info -> info[0]))
            .collect(Collectors.toList());

        assertEquals(filesCreatedAfterList.size(), filesCreatedAfterTargetList.size());

        for(int i = 0; i < filesCreatedAfterList.size(); i++)
            assertArrayEquals(filesCreatedAfterList.get(i), filesCreatedAfterTargetList.get(i));
    }

    @Test
    public void testFileCrawlVisiterGetXMLFile(){

        Directory root = fs.getRootDirs().get(0);

        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        root.accept(visitor);

        String[] testATarget = {"build.xml", "20", "hw01"};

        File buildFile = visitor.files()
            .filter((f) -> f.getName().endsWith(".xml"))
            .collect(Collectors.toList())
            .get(0);
        assertArrayEquals(buildFile.toStringArray(), testATarget);
    }
}
