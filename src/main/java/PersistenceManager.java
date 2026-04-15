import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class PersistenceManager {

    private static final String FILE = "library_data.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>)
                    (src, type, ctx) -> new JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>)
                    (json, type, ctx) -> LocalDate.parse(json.getAsString()))
            .setPrettyPrinting()
            .create();

    public static void save(Library library) {
        try (Writer writer = new FileWriter(FILE)) {
            JsonObject root = new JsonObject();
            root.add("authors", gson.toJsonTree(library.getAuthors()));
            root.add("books", gson.toJsonTree(library.getBooks()));
            root.add("loans", gson.toJsonTree(library.getLoans()));
            gson.toJson(root, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static void load(Library library) {
        File file = new File(FILE);
        if (!file.exists()) return;

        try (Reader reader = new FileReader(file)) {
            JsonObject root = gson.fromJson(reader, JsonObject.class);

            // 1. Carrega autores
            Type authorListType = new TypeToken<List<Author>>(){}.getType();
            List<Author> authors = gson.fromJson(root.get("authors"), authorListType);
            if (authors != null) authors.forEach(library::addAuthor);

            // 2. Carrega livros e remonta referência de Author por ID
            Type bookListType = new TypeToken<List<Book>>(){}.getType();
            List<Book> books = gson.fromJson(root.get("books"), bookListType);
            if (books != null) {
                for (Book b : books) {
                    int authorId = b.getAuthor().getId();
                    Author realAuthor = library.getAuthors().stream()
                            .filter(a -> a.getId() == authorId)
                            .findFirst().orElse(null);
                    b.setAuthor(realAuthor);
                    library.addBook(b);
                }
            }

            // 3. Carrega loans e remonta referência de Book por ID
            Type loanListType = new TypeToken<List<Loan>>(){}.getType();
            List<Loan> loans = gson.fromJson(root.get("loans"), loanListType);
            if (loans != null) {
                for (Loan l : loans) {
                    int bookId = l.getBook().getId();
                    Book realBook = library.findBookById(bookId);
                    l.setBook(realBook);
                    library.addLoan(l);
                }
            }

            // 4. Sincroniza contadores para evitar colisão de IDs
            library.getAuthors().stream()
                    .mapToInt(Author::getId).max()
                    .ifPresent(Author::setCounter);
            library.getBooks().stream()
                    .mapToInt(Book::getId).max()
                    .ifPresent(Book::setCounter);

        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}