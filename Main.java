import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("\nEnter number of processes:\t");
        int n = Integer.parseInt(br.readLine());

        int processWithToken = (int) Math.floor(Math.random() * n);

        List<Process> listOfProcesses = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            System.out.println("Process " + (i + 1));
            System.out.print("\nEnter a process id:\t");
            int pid = Integer.parseInt(br.readLine());

            Process p = new Process(pid);
            listOfProcesses.add(p);
        }

        listOfProcesses.get(processWithToken).setToken(true);

        br.close();
    }
}
