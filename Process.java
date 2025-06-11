public class Process {
    // Attributes
    private int id;
    private boolean token;
    private Process next;

    // Constructor
    public Process(int id) {
        this.id = id;
        this.token = false;
        this.next = null;
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

    public void setNext(Process p1) {
        this.next = p1;
    }
}