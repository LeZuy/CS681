package umbcs681.util;
import umbcs681.fs.*;

import java.util.Map;
import java.util.List;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.IntSummaryStatistics;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingInt;

import static org.junit.jupiter.api.Assertions.*;


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
        assertEquals(0, visitor.files().count());
    }

    @Test
    public void testFileCrawlingVisitorRootDirReturn4Files(){
        Directory root = fs.getRootDirs().get(0);
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        root.accept(visitor);
        assertEquals(4, visitor.files().count());
    }

    @Test
    public void testFileCrawlingVisitorHw1DirReturn3Files(){
        Directory hw1 = fs.getRootDirs().get(0).getSubDirectories().get(0);
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        hw1.accept(visitor);
        assertEquals(3, visitor.files().count());
    }

    @Test
    public void testFileCrawlingVisitorGetAFile(){

        Directory root = fs.getRootDirs().get(0);

        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        root.accept(visitor);

        File a = visitor.files().filter((f) -> f.getName().equals("A.java")).collect(Collectors.toList()).get(0);
        String[] testATarget = {"A.java", "15", "src"};

        assertArrayEquals(testATarget, a.toStringArray());
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

        assertEquals(filesCreatedAfterTargetList.size(), filesCreatedAfterList.size());

        for(int i = 0; i < filesCreatedAfterList.size(); i++)
            assertArrayEquals(filesCreatedAfterTargetList.get(i), filesCreatedAfterList.get(i));
    }

    @Test
    public void testFileCrawlVisiterGetJavaFile(){

        Directory root = fs.getRootDirs().get(0);

        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        root.accept(visitor);

        List<String[]> filesCreatedAfterTargetList = Stream.of(
            new String[]{"A.java", "15", "src"},
            new String[]{"B.java", "10", "src"}
        ).sorted(Comparator.comparing(info -> info[0]))
            .collect(Collectors.toList());

        Stream<File> filesCreatedAfter = visitor.files()
            .filter((f) -> f.getName().endsWith(".java"));

        List<String[]> filesCreatedAfterList = filesCreatedAfter
            .map(File::toStringArray)
            .sorted(Comparator.comparing(info -> info[0]))
            .collect(Collectors.toList());

        assertEquals(filesCreatedAfterTargetList.size(), filesCreatedAfterList.size());

        for(int i = 0; i < filesCreatedAfterList.size(); i++)
            assertArrayEquals(filesCreatedAfterTargetList.get(i), filesCreatedAfterList.get(i));
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
        assertArrayEquals(testATarget, buildFile.toStringArray());
    }

    @Test 
    public void testFilesSummarize(){
        Directory root = fs.getRootDirs().get(0);
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        root.accept(visitor);

        Map<String, IntSummaryStatistics> summaryFileExtBySizeTarget = Map.of(
            "java", new IntSummaryStatistics(2, 10, 15, 25),
            "md", new IntSummaryStatistics(1, 10, 10, 10),
            "xml", new IntSummaryStatistics(1, 20, 20, 20)
        );

        Map<String, IntSummaryStatistics> summaryFileExtBySize = visitor.files()
            .collect(groupingBy(File::getExtension,
                                summarizingInt(File::getSize)));
                                
        summaryFileExtBySizeTarget.forEach((key, stats) -> {
            IntSummaryStatistics e = summaryFileExtBySize.get(key);
            assertEquals(e.getCount(), stats.getCount());
            assertEquals(e.getSum(), stats.getSum());
            assertEquals(e.getMin(), stats.getMin());
            assertEquals(e.getMax(), stats.getMax());
            assertEquals(e.getAverage(), stats.getAverage());
        });
    }
}
