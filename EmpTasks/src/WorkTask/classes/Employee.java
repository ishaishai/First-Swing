package WorkTask.classes;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String m_empName;
	private int m_id;
	protected ArrayList<Task> m_tasks;
	private int m_salary;
	private int m_bonus;
	
	public Employee(final String empName,final int id)
	{
		this.m_empName = empName;
		this.m_id = id;
		m_tasks = new ArrayList<Task>();
		
	}
	
	public Employee()
	{
		this("Default",0);
	}
	
	public String getM_name()
	{
		return m_empName;
	}
	
	public int getM_salary() {
		return m_salary;
	}
	public void setM_salary(final int m_salary) {
		this.m_salary = m_salary;
	}
	public int getM_bonus() {
		return m_bonus;
	}
	protected void setM_bonus(final int m_bonus) {
		this.m_bonus = m_bonus;
	}
	
	@Override
	public String toString()
	{		
		return new String(String.format("Employee ID - %d, Name - %s, Salary - %d", this.m_id,this.m_empName,this.m_salary));
	}
	
	public void addTask(final Task t)
	{
		m_tasks.add(t);
	}
	
	public ArrayList<Task> getTasks()
	{
		return m_tasks;
	}

	public void setM_Bonus() {
		// TODO Auto-generated method stub
		
	}
}

