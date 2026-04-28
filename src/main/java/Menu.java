import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Menu {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Menu
        public void mainExecute() {
            PersistenceManager.load(library);
            int option;

            do {
                mainMenuDescription();
                option = readInt("Opção: ");

                switch (option) {
                    case 1:
                        clientMenu();
                        break;
                    case 2:
                        admMenu();
                        break;
                    case 3:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            } while (option != 3);

            sc.close();
        }
        public void admMenu() {
            int option;

            do {
                admMenuDescription();
                option = readInt("Opção: ");

                switch (option) {
                    case 1:
                        registerClient();
                        break;
                    case 2:
                        registerAuthor();
                        break;
                    case 3:
                        registerBook();
                        break;
                    case 4:
                        showBooksLoaned();
                        break;
                    case 5:
                        System.out.println("Encerrando sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            } while (option != 5);

        }
        public void clientMenu()        {
            int option;

            do {
                clientMenuDescription();
                option = readInt("Opção: ");

                switch (option) {
                    case 1:
                        showBooksAvailable();
                        break;
                    case 2:
                        registerLoan();
                        break;
                    case 3:
                        returnBook();
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            } while (option != 4);

        }

        // Menu Descriptions
        public void mainMenuDescription() {

            System.out.println("===================");
            System.out.println("Digite a opção desejada: ");
            System.out.println("1. Cliente.");
            System.out.println("2. Administrador.");
            System.out.println("3. Sair");
            System.out.println("===================");
        }
        public void admMenuDescription() {

            System.out.println("===================");
            System.out.println("Digite a opção desejada: ");
            System.out.println("1. Registrar Cliente.");
            System.out.println("2. Registrar Autor.");
            System.out.println("3. Registrar Livro.");
            System.out.println("4. Listar livros emprestados.");
            System.out.println("5. Voltar ao menu anterior.");
            System.out.println("===================");

        }
        public void clientMenuDescription() {
            System.out.println("===================");
            System.out.println("Digite a opção desejada: ");
            System.out.println("1. Pesquisar livros disponíveis.");
            System.out.println("2. Registrar empréstimo.");
            System.out.println("3. Devolver livro.");
            System.out.println("4. Voltar ao menu anterior");
            System.out.println("===================");

        }

        private void registerClient() {

            System.out.println(" === Registro de Cliente ===");

            System.out.println("Digite o nome do cliente: ");
            String name = sc.nextLine();

            System.out.println("Digite a idade: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.println("Digite um telefone de contato: ");
            String phone = sc.nextLine();

            Client c = new Client(name, age, phone);
            library.addClient(c);
            System.out.println("Cliente adicionado com sucesso!");
            PersistenceManager.save(library);
        }
        private void registerAuthor() {

            System.out.println();
            System.out.println("=== Cadastrar Autor ===");

            System.out.print("Nome: ");
            String name = sc.nextLine();

            System.out.println("Nacionalidade: ");
            String nationality = sc.nextLine();

            Author a = new Author(name, nationality);
            library.addAuthor(a);
            System.out.println("Autor cadastrado com sucesso!");
            PersistenceManager.save(library);
        }
        private void registerBook() {
            if (library.getAuthors().isEmpty()) {
                System.out.println("Cadastre um autor antes.");
            } else {
                System.out.println("=== Cadastro de livro ===");

                System.out.print("Título: ");
                String title = sc.nextLine();

                System.out.print("Autor: ");
                showAuthors();

                int indexAuthor = readInt("Escolha o autor pelo índice: ");

                if (indexAuthor < 0 || indexAuthor >= library.getAuthors().size()) {
                    System.out.println("Índice Inválido!");

                } else {
                    Author author = library.findAuthor(indexAuthor);

                    int pages = readInt("Total de páginas: ");

                    System.out.println("Digite a data de lançamento (dd/MM/yyyy): ");
                    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
                            .toFormatter();

                    LocalDate releaseDate = null;

                    while (releaseDate == null) {
                        try {
                            String input = sc.nextLine();
                            releaseDate = LocalDate.parse(input, formatter);
                        } catch (DateTimeParseException e){
                            System.out.println("Data inválida! Use o formato dd/MM/YYYY: ");
                        }
                    }

                    Book b = new Book(title, author, pages, releaseDate);
                    library.addBook(b);
                    b.setAvailable(true);

                    System.out.println("Livro cadastrado com sucesso!");
                    PersistenceManager.save(library);
                }
            }
        }
        private void registerLoan() {
            List<Book> availableBooks = showBooksAvailable();
                if(availableBooks.isEmpty()) return;

            int id = readInt("Digite o índice do livro: ");

            if (id < 0 || id >= availableBooks.size()) {
                System.out.println("Índice inválido!");
                return;
            }

            LocalDate now = LocalDate.now();
            System.out.print("Digite a data de devolução (dd/MM/yyyy): ");

            LocalDate returnDate = null;
            while (returnDate == null) {
                try {
                    returnDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } catch (DateTimeParseException e) {
                    System.out.print("Data inválida. Use dd/MM/yyyy: ");
                }
            }

            showClients();
            int clientIndex = readInt("Escolha o nome do cliente pelo número do índice: ");
            if (clientIndex < 0 || clientIndex >= library.getClients().size()) {
                System.out.println("Índice inválido!");
                return;
            }
            try {
                System.out.println(library.getClients().get(clientIndex).getName());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Índice inválido.");
            }
            Book book = availableBooks.get(id);
            Client client = library.findClient(clientIndex);

            Loan l = new Loan(book, client, now, returnDate);
            library.addLoan(l);
            book.setAvailable(false);

            PersistenceManager.save(library);
            System.out.println("Boa leitura!");
        }

        private void showClients() {
            System.out.println("===============");
            System.out.println("Clientes registrados: ");
            for (int i = 0; i < library.getClients().size(); i++) {
                System.out.println(i + " - " + library.getClients().get(i).getName());
                System.out.println("===================================");
            }
        }
        private void showAuthors() {
            System.out.println("==============");
            System.out.println("Autores registrados: ");
            for (int i = 0; i < library.getAuthors().size(); i++) {
                System.out.println(i + " - " + library.getAuthors().get(i).getName());
            }

        }
        private List<Book> showBooksAvailable() {

            if (library.getBooks().stream().noneMatch(Book::isAvailable)) {
                System.out.println("Nenhum livro disponível no momento.");
                return Collections.emptyList();
            }
            while(true) {
                System.out.println("==============");
                System.out.println("Digite o título do livro: ");
                String title = sc.nextLine();

                List<Book> result = library.getBooks().stream()
                        .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                        .filter(b -> b.isAvailable())
                        .collect(Collectors.toList());

                if (result.isEmpty()) {
                    System.out.println("Livro não encontrado ou indisponível.");
                } else {
                    System.out.println("Livros disponíveis:");
                    for(int i = 0; i < result.size(); i++) {
                        System.out.println(i + " - " + result.get(i).getTitle()
                                + " | Autor: " + result.get(i).getAuthor().getName());
                    }
                    return result;
                }
            }
        }
        private void showBooksLoaned() {
            if (library.getLoans().isEmpty()) {
                System.out.println("Nenhum livro emprestado no momento");
            } else {
                System.out.println("==============");
                System.out.println("Livros emprestados: ");
                for (int i = 0; i < library.getLoans().size(); i++) {
                    Loan l = library.getLoans().get(i);
                    System.out.println(i + " - Livro: " + l.getBook().getTitle() + " | Cliente: " + l.getClient().getName());
                }
            }
        }

        private void returnBook() {
            if (library.getLoans().isEmpty()) {
                System.out.println("Nenhum livro emprestado.");
            } else {
                System.out.println("===================");
                System.out.println("Digite o índice do livro a qual quer devolver: ");
                for (int i = 0; i < library.getLoans().size(); i++) {
                    Loan l = library.getLoans().get(i);
                    System.out.println(i + " - " + l.getBook().getTitle() + " | Cliente: " + l.getClient().getName());
                }
                int returnBook = readInt("Índice: ");
                Book returnedBook = library.getLoans().get(returnBook).getBook();
                returnedBook.setAvailable(true);
                library.returnLoan(returnBook);
                PersistenceManager.save(library);
                System.out.println("Livro retornado para a biblioteca com sucesso!");
            }
        }

        private int readInt (String message){
                while (true) {
                    System.out.print(message);

                    if (sc.hasNextInt()) {
                        int index = sc.nextInt();
                        sc.nextLine();
                        return index;
                    }

                    System.out.println("Digite um número válido.");
                    sc.nextLine();
                }
            }









            // End
        }
