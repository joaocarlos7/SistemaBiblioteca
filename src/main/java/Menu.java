import java.util.Scanner;

public class Menu {

        Scanner sc = new Scanner(System.in);

        Library library = new Library();



        public void execute() {
            int option;

            do {
                showOptions();
                option = sc.nextInt();
                sc.nextLine();


                switch (option) {
                    case 1:
                        registerClient();
                        break;
                    case 2:
                        registerBook();
                        break;
                    case 3:
                        showBooksLoaned();
                        break;
                    case 4:
                        clientWithBooks();
                        break;
                    case 5:
                        returnBook();
                        break;
                    case 6:
                        System.out.println("Encerrando sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            } while (option != 6);

            sc.close();
        }

        public void showOptions() {

        System.out.println("===================");
        System.out.println("Digite a opção desejada: ");
        System.out.println("1. Cadastrar pessoa.");
        System.out.println("2. Cadastrar livro emprestado");
        System.out.println("3. Verificar livros emprestados.");
        System.out.println("4. Verificar pessoas com livros.");
        System.out.println("5. Devolver livro.");
        System.out.println("6. Sair");
        System.out.println("===================");
        }

        private void registerClient() {
        if (customer >= client.length) {
            System.out.println("Limite de pessoas atingido.");
            return;
        }

        System.out.println("=== Cadastro de pessoas ===");

        System.out.print("Digite o nome: ");
        String name = sc.nextLine();

        System.out.print("Digite o sexo (Masculino 'M' ou Feminino 'F'): ");
        String gender = sc.nextLine();

        System.out.println("Digite a idade: ");
        int age = sc.nextInt();

        client[customer] = new Client(name, gender, age);

        library.addLoan();

        System.out.println("Pessoa cadastrada no índice " + customer + ": " + client[customer].getName());

    }

        private void registerBook() {
        if (booksLoaned >= book.length) {
            System.out.println("Limite de livros atingido.");
            return;
        }

        if (customer == 0) {
            System.out.println("Nenhuma pessoa cadastrada. Cadastre uma pessoa antes.");
            return;
        }

        System.out.println();
        System.out.println("=== Cadastro de livro emprestado ===");

        System.out.print("Título: ");
        String title = sc.nextLine();

        System.out.print("Autor: ");
        String author = sc.nextLine();

        System.out.println("Total de páginas: ");
        int pages = sc.nextInt();

        System.out.println("Escolha o leitor pelo índice:");
        showRegisterClient();

        int indexClient = sc.nextInt();

        if (indexClient < 0 || indexClient >= customer || client[indexClient] == null) {
            System.out.println("Pessoa inválida.");
            return;
        }

        book[booksLoaned] = new Book(title, author, pages, client[indexClient]);

        System.out.println("Livro cadastrado no índice " + booksLoaned + " para " + client[indexClient].getName());

        booksLoaned++;
        loans++;
    }

        private void showBooksLoaned() {

        System.out.println();
        System.out.println("=== Livros emprestados ===");

        if (booksLoaned == 0) {
            System.out.println("Nenhum livro emprestado.");
            return;
        }

        for (int i = 0; i < booksLoaned; i++) {
            Book b = book[i];

            if (book != null) {
                System.out.println("Índice do livro: " + i);
                System.out.println("Título: " + b.getTitle());
                System.out.println("Autor: " + b.getAuthor());
                System.out.println("Total de páginas: " + b.getPages());
                System.out.println("Leitor: " + b.getReader().getName());
                System.out.println("-------------------------");
            }
        }

        System.out.println("Total de livros atualmente emprestados: " + booksLoaned);
        System.out.println("Total histórico de empréstimos cadastrados: " + loans);
    }

        private void clientWithBooks() {
        System.out.println();
        System.out.println("=== Pessoas com livros ===");

        if (booksLoaned == 0) {
            System.out.println("Nenhuma pessoa está com livro emprestado.");
            return;
        }

        for (int i = 0; i < booksLoaned; i++) {
            Book b = book[i];

            if (book != null) {
                Client client = b.getReader();

                System.out.println("Livro índice " + i + ": " + b.getTitle());
                System.out.println("Pessoa: " + client.getName());
                System.out.println("Sexo: " + client.getGender());
                System.out.println("Idade: " + client.getAge());
                System.out.println("-------------------------");
            }
        }}

        private void returnBook() {
        System.out.println();
        System.out.println("=== Devolver livro ===");

        if (booksLoaned == 0) {
            System.out.println("Nenhum livro para devolver.");
            return;
        }

        showBooksLoaned();

        int indexBook = readInt("Digite o índice do livro a devolver: ");

        if (indexBook < 0 || indexBook >= booksLoaned || book[indexBook] == null) {
            System.out.println("Índice de livro inválido.");
            return;
        }

        System.out.println("Livro devolvido: " + book[indexBook].getTitle());

        for (int i = indexBook; i < booksLoaned - 1; i++) {
            book[i] = book[i + 1];
        }

        book[booksLoaned - 1] = null;
        booksLoaned--;
    }

        private void showRegisterClient() {
        if (customer == 0) {
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }

        for (int i = 0; i < customer; i++) {
            if (client[i] != null) {
                System.out.println(
                        i + " - " +
                                client[i].getName() +
                                " | sexo: " + client[i].getGender() +
                                " | idade: " + client[i].getAge()
                );
            }
        }
    }

        private int readInt(String message) {
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
}