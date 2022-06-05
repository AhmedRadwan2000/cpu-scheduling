package cpu_scheduling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FCFS {

    private final int[] arrivalTimes;
    private final int[] burstTimes;
    private final int[] waitingTimes;
    private final int[] turnAroundTimes;
    private int[] startingTimes;
    private int[] finishingTimes;
    private final int n;

    public FCFS(String path) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(
                new FileReader(path));
        String s = br.readLine();
        this.n = Integer.parseInt(s);
        arrivalTimes = new int[n];
        burstTimes = new int[n];
        waitingTimes = new int[n];
        turnAroundTimes = new int[n];
        for (int i = 0; i < n; i++) {
            s = br.readLine();
            String[] split = s.split("\\s+");
            arrivalTimes[i] = Integer.parseInt(split[0]);
            burstTimes[i] = Integer.parseInt(split[1]);
        }
        sorting(arrivalTimes, burstTimes);
        calcWaitingAndTurnAroundTimesForEachProcess();
    }

    private void calcWaitingAndTurnAroundTimesForEachProcess() {
        startingTimes = new int[n];
        finishingTimes = new int[n];
        startingTimes[0] = arrivalTimes[0];
        for (int i = 0; i < n; i++) {
            finishingTimes[i] = startingTimes[i] + burstTimes[i];
            int temp = startingTimes[i] + burstTimes[i];
            if (i < n - 1) {
                if (arrivalTimes[i + 1] >= temp) {
                    startingTimes[i + 1] = arrivalTimes[i + 1];
                } else {
                    startingTimes[i + 1] = startingTimes[i] + burstTimes[i];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            turnAroundTimes[i] = finishingTimes[i] - arrivalTimes[i];
            waitingTimes[i] = startingTimes[i] - arrivalTimes[i];
        }
        System.out.print("Id\t");
        System.out.print("AT\t");
        System.out.print("BT\t");
        System.out.print("WT\t");
        System.out.println("TT");
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + "\t");
            System.out.print(arrivalTimes[i] + "\t");
            System.out.print(burstTimes[i] + "\t");
            System.out.print(waitingTimes[i] + "\t");
            System.out.println(turnAroundTimes[i] + "\t");
        }
    }

    public void averageWaitingTime() {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + waitingTimes[i];
        }
        System.out.print("Average waiting time is: ");
        System.out.println(sum / n);
    }

    public void averageTurnAroundTime() {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + turnAroundTimes[i];
        }
        System.out.print("Average turn around Time is: ");
        System.out.println(sum / n);
    }

    private void sorting(int[] arr, int[] arr2) {
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
            temp = arr2[i];
            arr2[i] = arr2[index];
            arr2[index] = temp;
        }
    }
}
