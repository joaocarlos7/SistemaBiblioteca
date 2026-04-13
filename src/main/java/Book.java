public class Book {

    private String title;
    private String author;
    private int pages;
    private Client reader;

    public Book(String title, String author, int pages, Client reader) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.reader = reader;
    }

    // Getters & Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


    public Client getReader() {
        return reader;
    }

    public void setReader(Client reader) {
        this.reader = reader;
    }


}
