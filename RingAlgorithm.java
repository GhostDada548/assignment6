import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Process {
    public int id;
    public boolean hasToken;

    public Process(int id) {
        this.id = id;
        this.hasToken = false;
    }
}

public class RingAlgorithm {
    Scanner sc;
    Process[] processes;
    int n;

    public RingAlgorithm() {
        sc = new Scanner(System.in);
    }

    public void setupRing() {
        System.out.print("Enter total number of processes: ");
        n = sc.nextInt();

        processes = new Process[n];
        for (int i = 0; i < n; i++) {
            processes[i] = new Process(i);
        }
    }

    public void performElection() {
        System.out.println("Process 0 initiates the election and passes the token to Process 1.");
        processes[0].hasToken = true;

        int initiator = 0;
        List<Integer> sequence = new ArrayList<>();
        sequence.add(0); // Starting with process 0

        while (true) {
            int nextProcess = (initiator + 1) % n;

            if (processes[initiator].hasToken) {
                if (nextProcess == 0) {
                    System.out.println("Process " + initiator + " wins the election and becomes the leader.");
                    break;
                } else {
                    System.out.println("Process " + initiator + " passes the token to Process " + nextProcess + ".");
                    sequence.add(nextProcess); // Add the process to the sequence
                    processes[nextProcess].hasToken = true;
                    processes[initiator].hasToken = false;
                    initiator = nextProcess;
                }
            } else {
                System.out.println("Process " + initiator + " is waiting for the token.");
                initiator = nextProcess;
            }
        }

        System.out.println("Sequence of processes as token passed: " + sequence);
    }

    public static void main(String[] args) {
        RingAlgorithm ring = new RingAlgorithm();
        ring.setupRing();
        ring.performElection();
    }
}

