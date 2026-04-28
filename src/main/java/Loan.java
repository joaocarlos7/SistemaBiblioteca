import java.time.LocalDate;

public class Loan {

    private Book book;
    private Client client;
    private LocalDate loanDate;
    private LocalDate returnDate;

    // Constructor
    public Loan(Book book, Client client, LocalDate loanDate, LocalDate returnDate) {
        this.book = book;
        this.client = client;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
