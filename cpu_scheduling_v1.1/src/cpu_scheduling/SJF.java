package cpu_scheduling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SJF {

    private final int[] burstTimes;
    private final int[] arrivalTimes;
    private final int[] trackingBurstTimes;
    private final int[] waitingTimes;
    private final int[] turnAroundTimes;
    private final int[] startingTimes;
    private final int[] finishingTimes;
    private final int n;

    public SJF(String path) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(
                new FileReader(path));
        String s = br.readLine();
        this.n = Integer.parseInt(s);
        arrivalTimes = new int[n];
        trackingBurstTimes = new int[n];
        burstTimes = new int[n];
        waitingTimes = new int[n];
        turnAroundTimes = new int[n];
        startingTimes = new int[n];
        finishingTimes = new int[n];
        for (int i = 0; i < n; i++) {
            s = br.readLine();
            String[] splitted = s.split("\\s+");
            arrivalTimes[i] = Integer.parseInt(splitted[0]);
            burstTimes[i] = Integer.parseInt(splitted[1]);
            trackingBurstTimes[i] = burstTimes[i];
        }
        sorting(burstTimes, arrivalTimes, trackingBurstTimes);
        calcWaitingAndTurnAroundTimesForEachProcess();
    }

    private void calcWaitingAndTurnAroundTimesForEachProcess() {
        int firstProcessIndex = 0;
        for (int i = 1; i < n; i++) {
            if (arrivalTimes[i] < arrivalTimes[firstProcessIndex]) {
                firstProcessIndex = i;
            } else if (arrivalTimes[i] == arrivalTimes[firstProcessIndex] && burstTimes[i] < burstTimes[firstProcessIndex]) {
                firstProcessIndex = i;
            }
        }
        int tracking = trackingBurstTimes[firstProcessIndex];
        trackingBurstTimes[firstProcessIndex] = 0;
        int flag = 1;
        startingTimes[firstProcessIndex] = arrivalTimes[firstProcessIndex];
        finishingTimes[firstProcessIndex] = tracking;
        waitingTimes[firstProcessIndex] = 0;
        turnAroundTimes[firstProcessIndex] = finishingTimes[firstProcessIndex];
        while (true) {
            for (int i = 0; i < n; i++) {
                if (trackingBurstTimes[i] != 0) {
                    if (arrivalTimes[i] <= tracking) {
                        startingTimes[i] = tracking;
                        tracking = tracking + trackingBurstTimes[i];
                        finishingTimes[i] = tracking;
                        waitingTimes[i] = startingTimes[i] - arrivalTimes[i];
                        turnAroundTimes[i] = finishingTimes[i] - arrivalTimes[i];
                        trackingBurstTimes[i] = 0;
                        flag++;
                        i = -1;
                    }
                }

            }
            for (int i = 0; i < n; i++) {
                if (trackingBurstTimes[i] != 0) {
                    if (arrivalTimes[i] > tracking) {
                        startingTimes[i] = arrivalTimes[i];
                        tracking = startingTimes[i] + trackingBurstTimes[i];
                        finishingTimes[i] = tracking;
                        waitingTimes[i] = startingTimes[i] - arrivalTimes[i];
                        turnAroundTimes[i] = finishingTimes[i] - arrivalTimes[i];
                        trackingBurstTimes[i] = 0;
                        flag++;
                        i = n - 1;
                    }
                }
            }
            if (flag == n) {
                break;
            }
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

    private void sorting(int[] arr, int[] arr2, int[] arr3) {
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
            temp = arr3[i];
            arr3[i] = arr3[index];
            arr3[index] = temp;
        }
    }
}
