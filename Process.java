import java.util.LinkedList;
import java.util.Queue;

public class Process extends Thread {
    // Attributes
    private int id;
    private boolean token;
    private Process next;
    private Queue<Integer> queue;
    public boolean wantToEnter;

    // Constructor
    public Process(int id) {
        this.id = id;
        this.token = false;
        this.next = null;
        this.queue = new LinkedList<>();
        this.wantToEnter = false;
    }

    // Methods
    public int getProcessId() {
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

    public synchronized void enterCriticalSection() {
        System.out.println("Process " + id + " entering critical section...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Process " + id + " exiting critical section.");
    }
}