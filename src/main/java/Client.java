public class Client {

    private String name;
    private int age;
    private String phoneNumber;
    private int id;
    private static int counter = 0;

    public Client(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.id = ++counter;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static int getCounter() {
        return counter;
    }
    public static void setCounter(int value) {
        Client.counter = value;
    }
}
