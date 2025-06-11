public class Process {
    // Attributes
    private int id;
    private boolean token;

    // Constructor
    public Process(int id) {
        this.id = id;
        this.token = false;
    }

    // Methods
    public int getId() {
        return this.id;
    }

    public boolean getToken() {
        return this.token;
    }

    public void setToken(boolean token) {
        this.token = token;
    }
}