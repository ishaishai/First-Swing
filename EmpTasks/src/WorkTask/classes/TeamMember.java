package WorkTask.classes;

public class TeamMember extends Employee {
	private int factor;
	
	public TeamMember(final String empName,final int id)
	{
		super(empName,id);
		setM_bonus(0);
		int done_time;
		for(Task t : this.m_tasks)
		{
			done_time = t.calc_TaskTime();
			if(done_time == 0)
			{
				this.setM_bonus(this.getM_bonus() + factor);
			}
			else if(done_time > 0)
			{
				this.setM_bonus(this.getM_bonus() + 2*factor);
			}
		}
	}
	
	public void addTask(final Task t)
	{
		int numTasks = this.m_tasks.size()+1;
		this.m_tasks.add(t);
		
		this.factor = this.getM_salary()/numTasks;
	}
	
}
