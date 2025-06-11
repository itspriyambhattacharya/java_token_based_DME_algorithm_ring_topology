import java.util.LinkedList;
import java.util.Queue;

public class Process {
    // Attributes
    private int id;
    private boolean token;
    private Process next;
    private Queue queue;

    // Constructor
    public Process(int id) {
        this.id = id;
        this.token = false;
        this.next = null;
        this.queue = new LinkedList<>();
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