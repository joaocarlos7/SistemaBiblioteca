import java.time.LocalDate;

public class Book {

    private String title;
    private Author author;
    private int pages;
    private Client reader;
    private int id;
    private boolean available;
    private LocalDate registDate;
    private LocalDate dateUpdate;

    // Constructor
    public Book(String title, Author author, int pages, Client reader) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.reader = reader;
    }

    // Getters & Setters

    // Tittle
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // Author
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) {
        this.author = author;
    }

    // Pages
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }

    // Reader
    public Client getReader() {
        return reader;
    }
    public void setReader(Client reader) {
        this.reader = reader;
    }

    // ID
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Availability
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Register Date
    public LocalDate getRegistDate() {
        return registDate;
    }
    public void setRegistDate(LocalDate registDate) {
        this.registDate = registDate;
    }

    // UpdateBook
    public LocalDate getDateUpdate() {
        return dateUpdate;
    }
    public void setDateUpdate(LocalDate dateUpdate) {
        this.dateUpdate = dateUpdate;
    }



}
