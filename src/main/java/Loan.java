import java.time.LocalDate;

public class Loan {

    private Book book;
    private String name;
    private LocalDate loanDate;
    private LocalDate returnDate;

    // Constructor
    public Loan(Book book, String name, LocalDate loanDate, LocalDate returnDate) {
        this.book = book;
        this.name = name;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    // Book
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    // Client Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Loan Date
    public LocalDate getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    // Return Date
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }


}
