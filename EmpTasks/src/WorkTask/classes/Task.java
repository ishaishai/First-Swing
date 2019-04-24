package WorkTask.classes;

public class Task {
	private String m_TaskName;
	private int m_DaysToComplete;
	private int m_DaysDone;
	
	public Task(final String taskName,final int m_toComplete,final int m_gone)
	{
		this.m_TaskName = taskName;
		this.m_DaysToComplete = m_toComplete;
		this.m_DaysDone = 0;
	}
	
	public Task(final String taskName,final int m_toComplete)
	{
		this(taskName,m_toComplete,0);
	}
	
	public String getM_TaskName() {
		return m_TaskName;
	}

	public void setM_TaskName(String m_TaskName) {
		this.m_TaskName = m_TaskName;
	}

	public int getM_DaysToComplete() {
		return m_DaysToComplete;
	}

	public void setM_DaysToComplete(int m_DaysToComplete) {
		this.m_DaysToComplete = m_DaysToComplete;
	}

	public int getM_DaysDone() {
		return m_DaysDone;
	}

	public void setM_DaysDone(int m_DaysDone) {
		this.m_DaysDone = m_DaysDone;
	}
	
	public int calc_TaskTime()
	{
		return this.m_DaysToComplete - this.m_DaysDone;
	}
	
	public String toString()
	{
		return new String(m_TaskName);
	}

	
}
