package cpu_scheduling;

import java.io.IOException;
import java.util.Scanner;

public class Cpu_scheduling {

    public static void main(String[] args) throws IOException {
        
        Priority p = new Priority("priority_inputs.txt");
        p.averageWaitingTime();
        p.averageTurnAroundTime();

//          SJF s = new SJF("sjf_inputs.txt");
//          s.averageWaitingTime();
        
    }
    
}
