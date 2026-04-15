import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

    public class Menu {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Menu
        public void mainExecute() {
            PersistenceManager.load(library);
            int option;

            do {
                mainMenuDescription();
                option = sc.nextInt();
                sc.nextLine();


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
                option = sc.nextInt();
                sc.nextLine();


                switch (option) {
                    case 1:
                        registerAuthor();
                        break;
                    case 2:
                        registerBook();
                        break;
                    case 3:
                        showBooksLoaned();
                        break;
                    case 4:
                        System.out.println("Encerrando sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            } while (option != 4);

        }
        public void clientMenu() {
            int option;

            do {
                clientMenuDescription();
                option = sc.nextInt();
                sc.nextLine();


                switch (option) {
                    case 1:
                        registerLoan();
                        break;
                    case 2:
                        returnBook();
                        break;
                    case 3:
                        System.out.println("=====================");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            } while (option != 3);

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
            System.out.println("1. Registrar Autor.");
            System.out.println("2. Registrar Livro.");
            System.out.println("3. Listar livros emprestados.");
            System.out.println("4. Voltar ao menu anterior.");
            System.out.println("===================");

        }
        public void clientMenuDescription(){
            System.out.println("===================");
            System.out.println("Digite a opção desejada: ");
            System.out.println("1. Listar livros disponíveis.");
            System.out.println("2. Devolver livro.");
            System.out.println("3. Voltar ao menu anterior");
            System.out.println("===================");

        }

        // Admin Options Menu
        private void registerAuthor() {

            System.out.println();
            System.out.println("=== Cadastrar Autor ===");

            System.out.print("Nome: ");
            String name = sc.nextLine();

            LocalDate bd = null;
            while (bd == null) {
                try {

                    System.out.print("Data de aniversário (dd/MM/yyyy): ");
                    String bornDay = sc.nextLine();
                    bd = LocalDate.parse(bornDay, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                } catch (DateTimeParseException e) {
                    System.out.println("Data inválida! Use o formato: dd/MM/yyyy");
                }
            }


            Author a = new Author(name, bd);
            library.addAuthor(a);

            System.out.println("Autor cadastrado com sucesso!");
            PersistenceManager.save(library);
        }
        private void showAuthors() {
            System.out.println("==============");
            System.out.println("Autores registrados: ");
            for(int i = 0; i < library.getAuthors().size(); i++) {
                System.out.println(i + " - " + library.getAuthors().get(i).getName());
            }
        }
        private void registerBook() {
            if(library.getAuthors().isEmpty()) {
                System.out.println("Cadastre um autor antes.");
            } else {
            System.out.println();
            System.out.println("=== Cadastro de livro ===");

            System.out.print("Título: ");
            String title = sc.nextLine();

            System.out.print("Autor: ");
            showAuthors();

            int indexAuthor = readInt("Escolha o autor pelo índice: ");

            if(indexAuthor <0 || indexAuthor >= library.getAuthors().size()) {
                    System.out.println("Índice Inválido!");

                } else {
                    Author author = library.findAuthor(indexAuthor);

                    int pages = readInt("Total de páginas: ");

                    Book b = new Book(title, author, pages);
                    library.addBook(b);
                    b.setAvailable(true);

                    System.out.println("Livro cadastrado com sucesso!");
                    PersistenceManager.save(library);
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
                    System.out.println(i + " - Livro: " + l.getBook().getTitle() + " | Cliente: " + l.getName());
                }
            }
        }

        // Client Options Menu
        private void registerLoan() {
            if(library.getBooks().size() > 0 && library.getBooks().getFirst().isAvailable()) {
            showBooksAvailable();
            int id = readInt("Digite o índice do livro: ");

            System.out.println("Digite seu nome: ");
            String name = sc.nextLine();

            System.out.print("Digite a data de devolução (dd:MM:yyyy): ");
            String returnLoan = sc.nextLine();

            LocalDate now = LocalDate.now();
            LocalDate returnDate = LocalDate.parse(returnLoan, DateTimeFormatter.ofPattern("dd/MM/yyyy"));


            Book book = library.findBook(id);
            Loan l = new Loan(book, name, now, returnDate);
            library.addLoan(l);
            book.setAvailable(false);
            PersistenceManager.save(library);
            } else {
                System.out.println("Nenhum livro disponível para empréstimo.");
            }


        }
        private void showBooksAvailable() {
            System.out.println("==============");
            System.out.println("Livros disponíveis: ");
            for (int i = 0; i < library.getBooks().size(); i++) {
                if (library.getBooks().get(i).isAvailable()) {
                    System.out.println("=================================");
                    System.out.println("Índice: " + i);
                    System.out.println("Título: " + library.getBooks().get(i).getTitle());
                    System.out.println("Autor: " + library.getBooks().get(i).getAuthor().getName());
                    System.out.println("Número de páginas: " + library.getBooks().get(i).getPages());
                    System.out.println("=================================");
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
                    System.out.println(i + " - " + l.getBook().getTitle() + " | Cliente: " + l.getName());
                }
                int returnBook = sc.nextInt();
                Book returnedBook = library.getLoans().get(returnBook).getBook();
                returnedBook.setAvailable(true);
                library.returnLoan(returnBook);
                PersistenceManager.save(library);
                System.out.println("Livro retornado para a biblioteca com sucesso!");
            }
        }

        // Menu Tools
        private int readInt(String message) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                int index = sc.nextInt();
                sc.nextLine();
                return index;
            }

            System.out.println("Digite um número válido.");
            sc.nextLine();
            return readInt(message);
        }
    }

}


