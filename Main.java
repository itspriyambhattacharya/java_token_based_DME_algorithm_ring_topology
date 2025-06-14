import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter number of processes: ");
        int n = Integer.parseInt(br.readLine());

        List<Process> processList = new ArrayList<>();

        // Input process IDs
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID " + (i + 1) + ": ");
            int pid = Integer.parseInt(br.readLine());
            Process p = new Process(pid);
            processList.add(p);
        }

        // Randomly assign token to one process
        int tokenHolder = (int) (Math.random() * n);
        processList.get(tokenHolder).setToken(true);

        // Form ring in order of input
        for (int i = 0; i < n - 1; i++) {
            processList.get(i).setNext(processList.get(i + 1));
        }
        processList.get(n - 1).setNext(processList.get(0)); // Last points to first

        // Start all processes
        for (Process p : processList) {
            p.start();
        }

        br.close();
    }
}
