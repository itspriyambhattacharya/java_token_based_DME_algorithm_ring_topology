import java.util.LinkedList;
import java.util.Queue;

public class Process extends Thread {
    // Attributes
    private int id;
    private boolean hasToken;
    private Process next;
    private Queue<Integer> queue;
    public boolean wantsToEnter;

    // Constructor
    public Process(int id) {
        this.id = id;
        this.hasToken = false;
        this.next = null;
        this.queue = new LinkedList<>();
        this.wantsToEnter = true;
    }

    // Methods
    public int getProcessId() {
        return this.id;
    }

    public boolean getToken() {
        return this.hasToken;
    }

    public void setToken(boolean token) {
        this.hasToken = token;
    }

    public void setNext(Process p1) {
        this.next = p1;
    }

    public synchronized void exitCriticalSection() {
        System.out.println("Process " + id + " exiting critical section.");
    }

    public synchronized void enterCriticalSection() {
        System.out.println("Process " + id + " entering critical section...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void requestCriticalSection() {
        if (wantsToEnter) {
            if (hasToken) {
                enterCriticalSection();
                wantsToEnter = false;
            }
            hasToken = false;
            next.setToken(true);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100); // Small delay to simulate time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        requestCriticalSection();
    }
}