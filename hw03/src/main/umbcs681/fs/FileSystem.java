package umbcs681.fs;

import java.util.LinkedList;

public class FileSystem {
    
    private static FileSystem instance = null;

    private LinkedList<Directory> rootDirs;

    private FileSystem(LinkedList<Directory> rootDirs){
        this.rootDirs = rootDirs;
    }

    public static FileSystem getFileSystem(){
        if(instance == null){
            instance = new FileSystem(new LinkedList<Directory>());
        }
        return instance;
    }

    public LinkedList<Directory> getRootDirs(){
        return this.rootDirs;
    }

    public void appendRootDirs(Directory rootDir){
        this.rootDirs.add(rootDir);
    }
}
