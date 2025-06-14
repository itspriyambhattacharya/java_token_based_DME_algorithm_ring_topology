import java.util.LinkedList;
import java.util.Queue;

public class Process extends Thread {
    private int id; // Process ID
    private boolean hasToken; // Whether this process has the token
    public Process next; // Next process in the ring
    private Queue<Integer> queue; // Queue of pending requests
    public boolean wantsToEnter; // Flag: does this process want to enter CS

    public Process(int id) {
        this.id = id;
        this.hasToken = false;
        this.queue = new LinkedList<>();
        this.wantsToEnter = true;
    }

    public void setNext(Process nextProcess) {
        this.next = nextProcess;
    }

    public void setToken(boolean token) {
        this.hasToken = token;
    }

    public int getProcessId() {
        return this.id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println("Process " + id + " requesting token...");
            requestToken(this.id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean requestToken(int requesterId) {
        if (this.hasToken) {
            System.out.println("Process " + id + " has token. Processing request from " + requesterId);
            if (!queue.contains(requesterId)) {
                queue.offer(requesterId);
            }
            checkTokenAndEnterCS();
            return true;
        } else {
            return next.requestToken(requesterId);
        }
    }

    public synchronized void checkTokenAndEnterCS() {
        if (this.hasToken) {
            if (!queue.isEmpty() && queue.peek() == this.id) {
                queue.poll(); // Remove self from queue
                enterCriticalSection();
                wantsToEnter = false;
            } else if (this.wantsToEnter && queue.isEmpty()) {
                enterCriticalSection();
                wantsToEnter = false;
            } else {
                passToken();
            }
        }
    }

    public synchronized void enterCriticalSection() {
        System.out.println("Process " + id + " is ENTERING critical section...");
        try {
            Thread.sleep(1000); // Simulate critical section execution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exitCriticalSection();
    }

    public synchronized void exitCriticalSection() {
        System.out.println("Process " + id + " is EXITING critical section.");
        passToken();
    }

    private synchronized void passToken() {
        this.hasToken = false;
        next.setToken(true);
        while (!queue.isEmpty()) {
            next.queue.offer(queue.poll());
        }
        next.checkTokenAndEnterCS();
    }
}
