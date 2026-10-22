package umbcs681.util;

import umbcs681.fs.*;

public class CountingVisitor implements FSVisitor {

    private int dirNum;
    private int fileNum;
    private int linkNum;

    public CountingVisitor() {
        this.dirNum = 0;
        this.fileNum = 0;
        this.linkNum = 0;
    }

    @Override
    public void visit(Directory directory) {
        dirNum++;
    }

    @Override
    public void visit(File file) {
        fileNum++;
    }

    @Override
    public void visit(Link link) {
        linkNum++;
    }

    public int getDirNum() {
        return dirNum;
    }
    
    public int getFileNum() {
        return fileNum;
    }

    public int getLinkNum() {
        return linkNum;
    }
}
