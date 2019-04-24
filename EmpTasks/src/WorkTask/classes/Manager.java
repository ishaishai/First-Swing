package WorkTask.classes;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
	private int factor;
	private ArrayList<Employee> Employees;
	public Manager(final String empName,final int id)
	{
		super(empName,id);
		Employees = new ArrayList<Employee>();
	}
	
	public void addEmployee(Employee e)
	{
		Employees.add(e);
	}
	
	public void setManagerBonus()
	{
		setM_bonus((this.getM_salary()/10)*this.m_tasks.size());
	}
	
	public ArrayList<Employee> getM_Emps()
	{
		return Employees;
	}
	@Override
	public String toString()
	{
		return new String(super.toString() + " - Rank: Manager");
	}
}
