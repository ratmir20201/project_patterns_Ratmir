package homework;

import java.util.ArrayList;
import java.util.List;

public class composite {
    public static void main(String[] args) {
        File file1 = new File("file1.txt", 10);
        File file2 = new File("file2.txt", 20);
        File file3 = new File("file3.txt", 30);

        Directory dir1 = new Directory("Documents");
        Directory dir2 = new Directory("Images");
        Directory dir3 = new Directory("Projects");

        dir1.add(file1);
        dir1.add(file2);

        dir2.add(file3);

        dir3.add(dir1);
        dir3.add(dir2);

        dir3.display("");
        System.out.println("Общий размер " + dir3.name + ": " + dir3.getSize() + " KB");

        dir1.add(file1);
        dir1.remove(file3);
    }
}


abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }

    public abstract void display(String indent);

    public abstract int getSize();
}

class File extends FileSystemComponent {
    private int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "- " + name + " (File, " + size + " KB)");
    }

    @Override
    public int getSize() {
        return size;
    }
}

class Directory extends FileSystemComponent {
    private List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }

    public void add(FileSystemComponent component) {
        if (!children.contains(component)) {
            children.add(component);
        } else {
            System.out.println("Компонент " + component.name + " уже существует в " + name);
        }
    }

    public void remove(FileSystemComponent component) {
        if (children.contains(component)) {
            children.remove(component);
        } else {
            System.out.println("Компонент " + component.name + " не найден в " + name);
        }
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "+ " + name + " (Directory)");
        for (FileSystemComponent component : children) {
            component.display(indent + "  ");
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}

