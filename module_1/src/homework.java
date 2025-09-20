import java.util.*;

class Book {
    public String title;
    public String author;
    public String isbn;
    public int copies;

    public Book(String title, String author, String isbn, int copies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.copies = copies;
    }

    public void addCopies(int count) {
        this.copies += count;
    }

    public boolean removeCopy() {
        if (copies > 0) {
            copies--;
            return true;
        }
        return false;
    }

    public void returnCopy() {
        copies++;
    }
}

class Reader {
    public String name;
    public int id;

    public Reader(String name, int id) {
        this.name = name;
        this.id = id;
    }
}

class Library {
    public List<Book> books;
    public List<Reader> readers;

    public Library() {
        books = new ArrayList<>();
        readers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void registerReader(Reader reader) {
        readers.add(reader);
    }

    public void removeReader(Reader reader) {
        readers.remove(reader);
    }

    public boolean getBookToReader(Book book, Reader reader) {
        return book != null && book.removeCopy();
    }

    public boolean returnBookFromReader(Book book, Reader reader) {
        if (book != null) {
            book.returnCopy();
            return true;
        }
        return false;
    }
}

public class homework {
    public static void main(String[] args) {
        Library library = new Library();
        System.out.println(library.books.toString() + library.readers.toString());

        Book b1 = new Book("Война и мир", "Толстой", "1", 1);
        Book b2 = new Book("Преступление и наказание", "Достоевский", "2", 2);
        Book b3 = new Book("Мастер", "Булгаков", "3", 1);

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        System.out.println(library.books);
        library.removeBook(b3);
        System.out.println("После удаления: " + library.books);


        Reader r1 = new Reader("Серик", 1);
        Reader r2 = new Reader("Берик", 2);

        library.registerReader(r1);
        library.registerReader(r2);
        System.out.println(library.readers);


        System.out.println("Серик берет 'Война и мир': " + library.getBookToReader(b1, r1));

        System.out.println("Берик берет 'Война и мир': " + library.getBookToReader(b1, r2));
    }
}
