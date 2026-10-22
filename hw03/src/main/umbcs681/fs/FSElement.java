package umbcs681.fs;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class FSElement {
    private Directory parent;
    private String name;
    private int size;
    private LocalDateTime creationTime;

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime){
        this.parent = parent;
        if(this.parent != null)    
            this.parent.appendChild(this);
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public Directory getParent(){
        return this.parent;
    }

    public int getSize(){
        return this.size;
    }

    public String getName(){
        return this.name;
    }

    public LocalDateTime getCreationTime(){
        return this.creationTime;
    }

    abstract public boolean isDirectory();

    public String[] toStringArray(){
        String[] fseInfo = {
            this.getName(),
            String.valueOf(this.getSize()),
            String.valueOf(this.getParent() != null ? this.getParent().getName() : "null"),
        };
        return fseInfo;
    }

    public abstract void accept(FSVisitor v);

    @Override
    public boolean equals(Object that){
        if (this == that) return true;
        if (that == null || this.getClass() != that.getClass()) return false;

        FSElement fse = (FSElement) that;

        return size == fse.size &&
            Objects.equals(parent, fse.parent) &&
            Objects.equals(name, fse.name) &&
            Objects.equals(creationTime, fse.creationTime) && // Not used for test
            this.isDirectory() == fse.isDirectory();
    }


}
