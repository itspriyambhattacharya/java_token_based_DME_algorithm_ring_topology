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
        this.next.hasToken = true; // passing the token to the next connected node
        while (!queue.isEmpty()) {
            this.next.queue.offer(this.queue.poll());
        }
    }

    public synchronized void enterCriticalSection() {
        System.out.println("Process " + id + " entering critical section...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exitCriticalSection();
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
            int rand = (int) Math.floor(Math.random() * 1000 + 1);
            Thread.sleep(rand); // Small delay to simulate time
            requestCriticalSection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}