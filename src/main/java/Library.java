import java.util.ArrayList;
import java.util.List;


public class Library {

    // Arrays
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();


    // Book
    public void addBook(Book book) {
        books.add(book);
    }
    public List<Book> getBooks() {
        return this.books;
    }
    public Book findBook(int index) {
        return this.books.get(index);
    }
    public void removeBook(int index) {
        books.remove(index);
    }


    //  Author
    public void addAuthor(Author author) {
        authors.add(author);
    }
    public List<Author> getAuthors() {
        return this.authors;
    }
    public Author findAuthor(int index) {
        return this.authors.get(index);
    }
    public void removeAuthor(int index) {
        authors.remove(index);
    }


    // Loans
    public void addLoan(Loan loan) {
        loans.add(loan);
    }
    public List<Loan> getLoans() {
        return this.loans;
    }
    public Loan findLoan(int index) {
        return this.loans.get(index);
    }
    public void returnLoan(int index) {
        loans.remove(index);
    }










}
