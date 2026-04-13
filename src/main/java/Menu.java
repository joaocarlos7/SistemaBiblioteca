import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

    public class Menu {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Menu
        public void mainExecute() {
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
                        System.out.println("Encerrando sistema...");
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
                        System.out.println("Encerrando sistema...");
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
            System.out.println("4. Sair");
            System.out.println("===================");

        }
        public void clientMenuDescription(){
            System.out.println("===================");
            System.out.println("Digite a opção desejada: ");
            System.out.println("1. Listar livros disponíveis.");
            System.out.println("2. Devolver livro.");
            System.out.println("3. Sair");
            System.out.println("===================");

        }

        // Admin Options Menu
        private void registerAuthor() {

            System.out.println();
            System.out.println("=== Cadastrar Autor ===");

            System.out.print("Nome: ");
            String name = sc.nextLine();

            System.out.print("Data de aniversário (dd:MM:yyyy): ");
            String bornDay = sc.nextLine();

            LocalDate bd = LocalDate.parse(bornDay, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Author a = new Author(name, bd);
            library.addAuthor(a);

            System.out.println("Autor cadastrado com sucesso!");
        }
        private void showAuthors() {
            System.out.println("==============");
            System.out.println("Autores registrados: ");
            for(int i = 0; i < library.getAuthors().size(); i++) {
                System.out.println(library.getAuthors().get(i).getName());
            }
        }
        private void registerBook() {

            System.out.println();
            System.out.println("=== Cadastro de livro emprestado ===");

            System.out.print("Título: ");
            String title = sc.nextLine();

            System.out.print("Autor: ");
            showAuthors();
            int indexAuthor = readInt("Escolha o autor pelo índice: ");
            Author author = library.findAuthor(indexAuthor);

            System.out.println("Total de páginas: ");
            int pages = sc.nextInt();

            Book b = new Book(title, author, pages);
            library.addBook(b);

            System.out.println("Livro cadastrado com sucesso!");
        }

        // Client Options Menu
        private void registerLoan() {

            showBooksAvailable();
            int id = readInt("Digite o ID do livro: ");
            sc.nextLine();

            System.out.println("Digite seu nome: ");
            String name = sc.nextLine();

            System.out.print("Digite a data de devolução (dd:MM:yyyy): ");
            String returnLoan = sc.nextLine();

            LocalDate now = LocalDate.now();

            LocalDate returnDate = LocalDate.parse(returnLoan, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Loan l = new Loan(library.findBookById(id), name, now, returnDate);
            library.addLoan(l);

        }
        private void showBooksAvailable(){
            System.out.println("==============");
            System.out.println("Livros disponíveis: ");
            for(int i = 0; i < library.getBooks().size(); i++) {
                System.out.println("=================================");
                System.out.println(library.getBooks().get(i).getId());
                System.out.println(library.getBooks().get(i).getTitle());
                System.out.println(library.getBooks().get(i).getAuthor().getName());
                System.out.println(library.getBooks().get(i).getPages());
                System.out.println("=================================");
            }
        }
        private void returnBook(){
            System.out.println("===================");
            System.out.println("Digite o índice do livro a qual quer devolver: ");
            for(Loan : library.getLoans().get()) {
            library.getLoans().get()

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
            sc.close();
        }
    }

}


