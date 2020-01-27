import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RunProcess
{
	private static long timeInMilliseconds;
	private static Stopwatch processesAreRunning = Stopwatch.createUnstarted();


	public static long getCompletionTime()
		{
		return processesAreRunning.elapsed(TimeUnit.NANOSECONDS);
		}

	//also need stopwatch running at the same time for utilization calculations
	//method is expecting a time quantum, a list to recursively go through, and a value to be
	public static int RRExecution(int timeQuantum, ArrayList<Process> processQueue, int anI)
		{
		int amountOfContextSwitches = -1;
		if (processQueue.isEmpty())
		{
			System.out.println("Process queue has not been populated");
		}
		//if the list is non empty the following block will be executed:
		else
		{
			amountOfContextSwitches++;
			processesAreRunning.start();
			while (!processQueue.isEmpty())
			{
				//initialize j at 0
				int j = anI;
				//the following line will execute the first process in the list
				//use if to check if j is out of bounds
				if (j <= processQueue.size())
				{
					//start execution of the first process in the list
					processQueue.get(j).execute(timeQuantum, processQueue.get(j));
					//if the remaining burst time of the above process is less than or equal to zero remove it from the list
					if (processQueue.get(j).getBurstTime() <= 0)
					{
						processQueue.remove(j);
						amountOfContextSwitches++;
					}
				}
				if (j > processQueue.size())
				{
//						j = 0;
					RRExecution(timeQuantum, processQueue, j);
				}

			}
			processesAreRunning.stop();
		}
		long timeInNanoSeconds = (processesAreRunning.elapsed(TimeUnit.NANOSECONDS));
		long timeInMilliseconds = timeInNanoSeconds / (10 ^ 6);

		System.out.println("The amount of time spent executing in milliseconds is: " + timeInMilliseconds);
		long throughput = timeInMilliseconds / 4;
		System.out.println("Average throughput was: " + throughput + " processes/ms");
		return amountOfContextSwitches;
		}

	public static long getTimeInMilliseconds()
		{
		return timeInMilliseconds;
		}
//	long timeInMs = RunProcess.getTimeInMilliseconds();


}
