import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

//what goes into a cpu scheduling algorithm? You need processes, need to execute processes, need to keep track of idle time, cpu utilization, throughput,
//turnaround time, avg wait time, and response time
public class Process<Private> extends Runtime
{
	private static Stopwatch lifeTime = Stopwatch.createStarted();
	private int processId;
	private int burstTime;

	//Constructor must be used, default constructor will not work
	public Process(int thePid, int tbt, int tq)
		{
		this.processId = thePid;
		this.burstTime = tbt;
		}

	public static long getLifeTime()
		{
		return lifeTime.elapsed(TimeUnit.MILLISECONDS);
		}

	//this method needs to subtract timeToExecute from the processes burst time
	public void execute(int timeToExecute, Process processToExecute)
		{
		this.burstTime = this.burstTime - timeToExecute;
		}

	public int getProcessId()
		{
		return processId;
		}

	public void setProcessId(int processId)
		{
		this.processId = processId;
		}

	public int getBurstTime()
		{
		return burstTime;
		}

	public void setBurstTime(int burstTime)
		{
		this.burstTime = burstTime;
		}

}
