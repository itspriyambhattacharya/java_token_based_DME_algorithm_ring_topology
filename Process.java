import java.util.LinkedList;
import java.util.Queue;

public class Process extends Thread {
    private int id; // Unique Process ID
    private boolean hasToken; // Whether this process has the token
    private Process next; // Next process in the ring
    private Queue<Integer> queue; // Queue to hold pending requests
    public boolean wantsToEnter; // Whether this process wants to enter CS

    // Constructor
    public Process(int id) {
        this.id = id;
        this.hasToken = false;
        this.next = null;
        this.queue = new LinkedList<>();
        this.wantsToEnter = true; // Initially every process wants to enter CS
    }

    // Set the next process (ring link)
    public void setNext(Process nextProcess) {
        this.next = nextProcess;
    }

    // Set token status
    public void setToken(boolean token) {
        this.hasToken = token;
    }

    // Get process ID
    public int getProcessId() {
        return this.id;
    }

    // Critical Section Methods
    public synchronized void enterCriticalSection() {
        System.out.println("Process " + id + " is ENTERING critical section...");
        try {
            Thread.sleep(1000); // Simulate work in CS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exitCriticalSection();
    }

    public synchronized void exitCriticalSection() {
        System.out.println("Process " + id + " is EXITING critical section.");
        this.hasToken = false;

        // Pass token and queue to the next process
        next.setToken(true);
        while (!queue.isEmpty()) {
            int nextRequest = queue.poll();
            next.queue.offer(nextRequest);
        }
        next.checkTokenAndEnterCS(); // Start token handling in next
    }

    // Token Request Method
    public synchronized boolean requestToken(int requesterId) {
        // If this process has the token
        if (this.hasToken) {
            if (this.id == requesterId) {
                // Requester has the token and is itself
                return true;
            } else {
                // Another process is requesting
                if (!queue.contains(requesterId)) {
                    queue.offer(requesterId); // Add request to queue
                }

                // If this process doesn't want to enter CS, pass token
                if (!this.wantsToEnter) {
                    this.hasToken = false;
                    next.setToken(true);
                    while (!queue.isEmpty()) {
                        next.queue.offer(queue.poll());
                    }
                    next.checkTokenAndEnterCS();
                }

                return false;
            }
        } else {
            // If no token, forward the request to the next process
            return next.requestToken(requesterId);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 1000));

            requestCriticalSection();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void requestCriticalSection() {
        if (wantsToEnter) {
            boolean granted = requestToken(this.id);
            if (granted) {
                enterCriticalSection();
                wantsToEnter = false;
            }
        }
    }

    // After receiving token, check if should enter CS
    public synchronized void checkTokenAndEnterCS() {
        if (this.hasToken) {
            if (!queue.isEmpty() && queue.peek() == this.id) {
                queue.poll();
                enterCriticalSection();
                wantsToEnter = false;
            } else if (this.wantsToEnter && queue.isEmpty()) {
                enterCriticalSection();
                wantsToEnter = false;
            } else {
                // No need to enter CS, pass token
                this.hasToken = false;
                next.setToken(true);
                while (!queue.isEmpty()) {
                    next.queue.offer(queue.poll());
                }
                next.checkTokenAndEnterCS();
            }
        }
    }
}
