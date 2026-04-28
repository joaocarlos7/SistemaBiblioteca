import java.time.LocalDate;

public class Author {

    private int id;
    private static int counter = 0;
    private String name;
    private String nationality;

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
        this.id = ++counter;
    }


    public String getName() {
        return name;
    }

    public int getCounter() {
        return counter;
    }

    public static void setCounter(int value) {
        Author.counter = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
