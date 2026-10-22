package umbcs681.fs;

import java.time.LocalDateTime;

public class Link extends FSElement {

    private FSElement target;

    public Link(Directory parent, String name, LocalDateTime creationTime, FSElement target) {
        super(parent, name, 0, creationTime);
        this.target = target;
    }

    public FSElement getTarget() {
        return target;
    }

    public boolean isDirectory() {
        return false;
    }

    public void accept(FSVisitor v){
        v.visit(this);
    }
}
