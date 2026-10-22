package umbcs681.util;

import umbcs681.fs.*;
import java.util.LinkedList;

public class FileSearchVisitor implements FSVisitor {
    
    private String fileName;
    private LinkedList<File> foundFiles;

    public FileSearchVisitor(String fileName) {
        this.fileName = fileName;
        this.foundFiles = new LinkedList<File>();
    }

    public LinkedList<File> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public void visit(Directory directory) {
        return;
    }

    @Override
    public void visit(Link link) {
        return;
    }

    @Override
    public void visit(File file) {
        if (file.getName().equals(fileName)) 
            foundFiles.add(file);
    }
}
