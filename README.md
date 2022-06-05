# Cpu_scheduling ( FCFS, SJF, Round Robin, Priority )

The inputs are given in a text file for each class, where each class represents one algorithm.

## In case of ( FCFS ) class :
The first line of the txt file must be the number of processes.
The remaining lines are the processes data ( arrival time + burst time ). \
e.g :\
4 ---> the number of processes.\
5 6 ---> the first number is the arrival time and the second number is the burst time and so on for the remaining lines.\
3 4\
0 2\
1 7


## In case of ( RR ) class :
The first line of the txt file must be the number of processes.
The second line the time quantum
The remaining lines are the processes data ( arrival time + burst time ). \
e.g :\
4 ---> the number of processes.\
3 ---> the time quantum. \
5 6 ---> the first number is the arrival time and the second number is the burst time and so on for the remaining lines.\
3 4\
0 2\
1 7


## In case of ( SJF ) class :
The first line of the txt file must be the number of processes.
The remaining lines are the processes data ( arrival time + burst time ) same as FCFS.


## In case of ( Priority ) class :
The first line of the txt file must be the number of processes.
The remaining lines are the processes data ( arrival time + burst time + priority ). \
e.g :\
4 ---> the number of processes.\
5 6 5 ---> the first number is the arrival time and the second number is the burst time and the third number is the priority number and so on for the remaining lines.\
3 4 3\
0 2 2\
1 7 4
