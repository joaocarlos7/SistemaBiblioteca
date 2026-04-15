import java.time.LocalDate;

public class Author {

    private int id;
    private static int counter = 0;
    private String name;
    private LocalDate birthDate;


    public Author(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.id = ++counter;
    }

    // ID
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public static void setCounter(int value) {
        counter = value;
    }

    // Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Birthday
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
