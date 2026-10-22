package umbcs681.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{

    private LinkedList<FSElement> children;

    public Directory(Directory parent, String name, LocalDateTime creationTime){
        super(parent, name, 0, creationTime);
        this.children = new LinkedList<FSElement>();
    }

    public LinkedList<FSElement> getChildren(){
        return this.children;
    }

    public void appendChild(FSElement child){
        this.children.add(child);
    }

    public boolean isDirectory(){
        return true;
    }

    public int countChildren(){
        return children.size();
    }

    public LinkedList<Directory> getSubDirectories(){
        
        LinkedList<Directory> subdirs = new LinkedList<Directory>();

        for (FSElement c : this.children)
            if(c.isDirectory())
                subdirs.add((Directory)c);
        
        return subdirs;
    }

    public LinkedList<File> getFiles(){
        
        LinkedList<File> files = new LinkedList<File>();

        for (FSElement c : this.children)
            if(c instanceof File)
                files.add((File)c);
        
        return files;
    }

    public int getTotalSize(){
        
        int totalSize = 0;
        
        for (FSElement c : this.children){
            if(c.isDirectory()){
                Directory dir = (Directory)c;
                totalSize += dir.getTotalSize();
            } 
            else if (!c.isDirectory()){
                totalSize += c.getSize();
            }
        }
        return totalSize;
    }

    public LinkedList<Link> getLinks(){
        
        LinkedList<Link> links = new LinkedList<Link>();

        for (FSElement c : this.children)
            if(c instanceof Link)
                links.add((Link)c);
        
        return links;
    }

    public void accept(FSVisitor v){
        v.visit(this);
        for(FSElement e : this.children)
            e.accept(v);
    }
}
