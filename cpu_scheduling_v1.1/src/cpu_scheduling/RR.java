package cpu_scheduling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RR {

    private final int[] arrivalTimes;
    private final int[] burstTimes;
    private int[] trackingBurstTimes;
    private final int[] waitingTimes;
    private final int[] turnAroundTimes;
    List<Integer> trackingProcessExecution = new ArrayList<>();
    List<Integer> trackingProcessId = new ArrayList<>();
    private final int n;
    private final int q;
    private int tracking = 0;
    private int flag = 0;

    public RR(String path) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(
                new FileReader(path));
        String s = br.readLine();
        this.n = Integer.parseInt(s);
        arrivalTimes = new int[n];
        burstTimes = new int[n];
        waitingTimes = new int[n];
        turnAroundTimes = new int[n];
        trackingBurstTimes = new int[n];
        s = br.readLine();
        this.q = Integer.parseInt(s);
        for (int i = 0; i < n; i++) {
            s = br.readLine();
            String[] split = s.split("\\s+");
            arrivalTimes[i] = Integer.parseInt(split[0]);
            burstTimes[i] = Integer.parseInt(split[1]);
            trackingBurstTimes[i] = burstTimes[i];
        }
        sorting(arrivalTimes, burstTimes);
        calcWaitingAndTurnAroundTimesForEachProcess();
    }

    private void calcWaitingAndTurnAroundTimesForEachProcess() {
        for (int i = 0; i < n; i++) {
            if (trackingBurstTimes[i] > 0) {
                if (trackingBurstTimes[i] - q > 0) {
                    tracking = tracking + q;
                    trackingBurstTimes[i] = trackingBurstTimes[i] - q;
                    trackingProcessId.add(i);
                    trackingProcessExecution.add(tracking);
                } else if (trackingBurstTimes[i] - q == 0) {
                    tracking = tracking + q;
                    trackingBurstTimes[i] = 0;
                    flag++;
                    trackingProcessId.add(i);
                    trackingProcessExecution.add(tracking);
                    waitingTimes[i] = tracking - arrivalTimes[i] - burstTimes[i];
                    turnAroundTimes[i] = tracking - arrivalTimes[i];
                } else {
                    tracking = tracking + trackingBurstTimes[i];
                    trackingBurstTimes[i] = 0;
                    flag++;
                    trackingProcessId.add(i);
                    trackingProcessExecution.add(tracking);
                    waitingTimes[i] = tracking - arrivalTimes[i] - burstTimes[i];
                    turnAroundTimes[i] = tracking - arrivalTimes[i];
                }
            }
            if (i != n - 1) {
                if (arrivalTimes[i + 1] > tracking) {
                    i--;
                }
            }
            if (i == n - 1 && flag < n) {
                i = -1;
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

    public void averageTurnAroundTimes() {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + turnAroundTimes[i];
        }
        System.out.print("Average turn around time is: ");
        System.out.println(sum / n);
    }

    public void averagwaitingTimes() {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + waitingTimes[i];
        }
        System.out.print("Average waiting time is: ");
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
