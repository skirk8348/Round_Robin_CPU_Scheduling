import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.Scanner;


public class Runtime
{

	public static int amountOfContextSwitches = 0;
	private static Stopwatch processAreRunning;
	private static int i = 0;

	public static void main(String[] args)
		{
		Scanner myKb = new Scanner(System.in);
		System.out.println("Enter the time quantum.");
		int timeQuantum = myKb.nextInt();
		myKb.close();

		Process myProcess1 = new Process(0, 8, timeQuantum);
		Process myProcess2 = new Process(1, 12, timeQuantum);
		Process myProcess3 = new Process(2, 18, timeQuantum);
		Process myProcess4 = new Process(3, 9, timeQuantum);


		//now execute processes in a round robin manner. Can be done using array list of processes
		ArrayList<Process> processQueue = new ArrayList<Process>();
		processQueue.add(myProcess1);
		processQueue.add(myProcess2);
		processQueue.add(myProcess3);
		processQueue.add(myProcess4);
		//now loop through array list and begin execution

		RunProcess.RRExecution(timeQuantum, processQueue, i);


		//what is the avg wait time? it's the sum of all process' wait time / the amount of processes. How to get the wait time of a process? CompletionTime - (arrival + burstTime) p1:6 p2:1 p3:0 p4:2

		//this variable represents the wait time of the first process
		long waitTimeP1 = (RunProcess.getCompletionTime()) - (0 + 8);
		long waitTimeP2 = (RunProcess.getCompletionTime() - (1 + 12));
		long waitTimeP3 = (RunProcess.getCompletionTime() - (2 + 18));
		long waitTimeP4 = (RunProcess.getCompletionTime() - (3 + 9));
		System.out.println("The average wait time was: " + ((waitTimeP1 + waitTimeP2 + waitTimeP3 + waitTimeP4) / 4) + " nanoseconds");


		long totalProcessTime = Process.getLifeTime() + Process.getLifeTime() + Process.getLifeTime() + Process.getLifeTime();
		long averageTurnaroundTime = totalProcessTime / 4;


		System.out.println("Average turnaround time was: " + averageTurnaroundTime + "ms");
//		System.out.println("CPU Utilization was: " + "87.5%");
//		System.out.println(amountOfContextSwitches + " context switches have been performed.");


		}

	public void contextSwitch(Process pSwitchedIn, int placeholder)//placeholder is really just time quantum
	{
	//keep track of idle time for first
	long start = System.currentTimeMillis();
	pSwitchedIn.execute(placeholder, pSwitchedIn);
	long end = System.currentTimeMillis();
	long totalIdleTime = +end - start;
	}
	//also need stopwatch running at the same time for utilization calculations
	//method is expecting a time quantum, a list to recursively go through, and a value to be

}