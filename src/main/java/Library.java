    import java.util.ArrayList;
    import java.util.List;

    public class Library {

        // Arrays
        private List<Book> books = new ArrayList<>();
        private List<Author> authors = new ArrayList<>();
        private List<Loan> loans = new ArrayList<>();
        private List<Client> clients = new ArrayList<>();


        // Book
        public void addBook(Book book) {
            books.add(book);

        }
        public List<Book> getBooks() {
            return this.books;
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

        // Loans
        public void addLoan(Loan loan) {
            loans.add(loan);
        }
        public List<Loan> getLoans() {
            return this.loans;
        }
        public void returnLoan(int index) {
            loans.remove(index);
        }

        //Client
        public void addClient(Client client) {
            clients.add(client);
        }
        public List<Client> getClients() {
            return this.clients;
        }
        public Client findClient(int index) {
            return this.clients.get(index);
        }
        public void removeClient(int index) {
            clients.remove(index);
        }


    }
