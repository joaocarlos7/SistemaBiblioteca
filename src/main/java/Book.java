import java.time.LocalDate;

public class Book {

    private String title;
    private Author author;
    private int pages;
    private int id;
    private boolean available;
    private LocalDate registDate;
    private static int counter = 0;

    // Constructor
    public Book(String title, Author author, int pages, LocalDate registDate) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.registDate = registDate;
        this.id = ++counter;

    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getCounter() {
        return counter;
    }
    public static void setCounter(int value) {
        Book.counter = value;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean isAvailable) {
        this.available = isAvailable;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
