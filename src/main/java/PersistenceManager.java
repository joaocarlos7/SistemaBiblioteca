import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersistenceManager {

    private static final String FILE = "library_data.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class,
                    (JsonSerializer<LocalDate>) (src, type, ctx) -> new JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDate.class,
                    (JsonDeserializer<LocalDate>) (json, type, ctx) -> LocalDate.parse(json.getAsString()))
            .setPrettyPrinting()
            .create();

    public static void save(Library library) {
        try (Writer writer = new FileWriter(FILE)) {
            JsonObject root = new JsonObject();
            root.add("authors", gson.toJsonTree(library.getAuthors()));
            root.add("clients", gson.toJsonTree(library.getClients()));
            root.add("books",   gson.toJsonTree(library.getBooks()));
            root.add("loans",   gson.toJsonTree(library.getLoans()));
            gson.toJson(root, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static void load(Library library) {
        File file = new File(FILE);
        if (!file.exists()) return;

        try (Reader reader = new FileReader(file)) {
            JsonObject root = gson.fromJson(reader, JsonObject.class);

            // 1. Autores
            Type authorListType = new TypeToken<List<Author>>(){}.getType();
            List<Author> authors = gson.fromJson(root.get("authors"), authorListType);
            if (authors != null) authors.forEach(library::addAuthor);

            // Índice por ID para resolução de referências em O(1)
            Map<Integer, Author> authorIndex = library.getAuthors().stream()
                    .collect(Collectors.toMap(Author::getId, a -> a));

            // 2. Clients
            Type clientListType = new TypeToken<List<Client>>(){}.getType();
            List<Client> clients = gson.fromJson(root.get("clients"), clientListType);
            if (clients != null) clients.forEach(library::addClient);

            // 3. Books — resolve Author por ID (não por nome)
            Type bookListType = new TypeToken<List<Book>>(){}.getType();
            List<Book> books = gson.fromJson(root.get("books"), bookListType);
            if (books != null) {
                for (Book b : books) {
                    Author realAuthor = authorIndex.get(b.getAuthor().getId());
                    if (realAuthor == null) {
                        System.err.println("Autor não encontrado para livro: " + b.getTitle());
                        continue;
                    }
                    b.setAuthor(realAuthor);
                    library.addBook(b);
                }
            }

            // Índice de Books por ID para resolver Loans
            Map<Integer, Book> bookIndex = library.getBooks().stream()
                    .collect(Collectors.toMap(Book::getId, b -> b));

            // Índice de Clients por ID para resolver Loans
            Map<Integer, Client> clientIndex = library.getClients().stream()
                    .collect(Collectors.toMap(Client::getId, c -> c));

            // 4. Loans — resolve Book e Client por ID
            Type loanListType = new TypeToken<List<Loan>>(){}.getType();
            List<Loan> loans = gson.fromJson(root.get("loans"), loanListType);
            if (loans != null) {
                for (Loan l : loans) {
                    Book realBook = bookIndex.get(l.getBook().getId());
                    Client realClient = clientIndex.get(l.getClient().getId());

                    if (realBook == null) {
                        System.err.println("Livro não encontrado ao carregar empréstimo.");
                        continue;
                    }
                    if (realClient == null) {
                        System.err.println("Cliente não encontrado ao carregar empréstimo.");
                        continue;
                    }

                    l.setBook(realBook);
                    l.setClient(realClient);
                    library.addLoan(l);
                }
            }

            // 5. Sincroniza todos os contadores corretamente
            library.getAuthors().stream().mapToInt(Author::getId).max()
                    .ifPresent(Author::setCounter);
            library.getBooks().stream().mapToInt(Book::getId).max()
                    .ifPresent(Book::setCounter);
            library.getClients().stream().mapToInt(Client::getId).max()
                    .ifPresent(Client::setCounter);

        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}