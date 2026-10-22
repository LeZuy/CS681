package umbcs681.util;

import umbcs681.fs.*;
import java.util.stream.Stream;
import java.util.LinkedList;

public class FileCrawlingVisitor implements FSVisitor {

    private LinkedList<File> files;
    private Directory rootDirectory;
    
    public FileCrawlingVisitor() {
        this.files = new LinkedList<File>();
        this.rootDirectory = null;
    }

    @Override
    public void visit(Directory directory) {
        if (rootDirectory == null) {
            rootDirectory = directory;
        }
    }

    @Override
    public void visit(Link link) {
        return;
    }

    @Override
    public void visit(File file) {
        if (rootDirectory != null) {
            files.add(file);
        }
    }

    public LinkedList<File> getFiles() {
        return files;
    }

    public Stream<File> files(){
        return files.stream();
    }
}
