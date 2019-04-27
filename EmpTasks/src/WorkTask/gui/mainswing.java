package WorkTask.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;

import WorkTask.classes.*;
import javax.swing.JCheckBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainswing {

	private JFrame frmEmployeeTasksManager;
	private JLayeredPane panelBase;
	private ArrayList<Task> m_Tasks;
	private ArrayList<Employee> m_Emps;
	private Task m_SelectedTask;
	private Employee m_SelectedEmp;
	private Manager m_SelectedManager;
	private JTextField textEmpName;
	private JTextField textEmpID;
	private JTextField textEmpSalary;
	private JTextField textTimeComplete;
	private JTextField textTimeDone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainswing window = new mainswing();
					window.frmEmployeeTasksManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainswing() {
		m_Tasks = new ArrayList<Task>();
		m_Emps = new ArrayList<Employee>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployeeTasksManager = new JFrame();
		frmEmployeeTasksManager.setTitle("Employee Tasks Manager");
		frmEmployeeTasksManager.setBounds(100, 100, 775, 429);
		frmEmployeeTasksManager.getContentPane().setBackground(new Color(44, 58, 71));
		frmEmployeeTasksManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployeeTasksManager.getContentPane().setLayout(null);
		
		panelBase = new JLayeredPane();
		panelBase.setBounds(177, 25, 572, 342);
		frmEmployeeTasksManager.getContentPane().add(panelBase);
		panelBase.setLayout(new CardLayout(0, 0));
		
		
		//DefaultListModels 
		
		//EmployeesPanel
		DefaultListModel<String> infoListEmp = new DefaultListModel<String>(); //for list of Employees
		
		//ManagerPanel
		DefaultListModel<String> modelManager = new DefaultListModel<String>();	
		DefaultListModel<String> modelManageEmp = new DefaultListModel<String>();
		
		//TasksPanel		
		DefaultListModel<String> modelTaskMatch = new DefaultListModel<String>();
		DefaultListModel<String> modelEmpMatch = new DefaultListModel<String>();
		DefaultListModel<String> modelManagerMatch = new DefaultListModel<String>();
		DefaultListModel<String> modelETasks = new DefaultListModel<String>();
		
		JPanel panelEmp = new JPanel();
		panelEmp.setBackground(new Color(27, 156, 252));
		panelBase.add(panelEmp, "name_445276970651538");
		panelEmp.setLayout(null);
		
		JLabel lblEmployees = new JLabel("Employees:");
		lblEmployees.setBounds(6, 6, 92, 17);
		panelEmp.add(lblEmployees);
		
		textEmpName = new JTextField();
		textEmpName.setBounds(6, 51, 86, 20);
		panelEmp.add(textEmpName);
		textEmpName.setColumns(10);
		
		textEmpID = new JTextField();
		textEmpID.setColumns(10);
		textEmpID.setBounds(6, 105, 86, 20);
		panelEmp.add(textEmpID);
		
		textEmpSalary = new JTextField();
		textEmpSalary.setColumns(10);
		textEmpSalary.setBounds(6, 160, 86, 20);
		panelEmp.add(textEmpSalary);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 26, 86, 14);
		panelEmp.add(lblName);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(6, 82, 86, 14);
		panelEmp.add(lblId);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(6, 136, 86, 14);
		panelEmp.add(lblSalary);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(126, 51, 261, 132);
		panelEmp.add(scrollPane_1);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(243, 209, 260, 58);
		JList<String> listETasks = new JList<String>(modelETasks);
		scrollPane_8.setViewportView(listETasks);
		
		
		JList<String> listEmps = new JList<String>(infoListEmp);
		scrollPane_1.setViewportView(listEmps);
		
		JLabel lblEmployeesList = new JLabel("Employees list:");
		lblEmployeesList.setBounds(126, 26, 117, 14);
		panelEmp.add(lblEmployeesList);
		
		JCheckBox chckbxManager = new JCheckBox("Manager?");
		chckbxManager.setBounds(6, 212, 97, 23);
		panelEmp.add(chckbxManager);
		
		JButton btnAddEmp = new JButton("Add");
		btnAddEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String value = addEmployeeToEmployees(textEmpName,textEmpID,textEmpSalary);
				infoListEmp.add(infoListEmp.getSize(),value);
			}

			private String addEmployeeToEmployees(JTextField textname, JTextField textid,JTextField textsalary) {
				Employee emp;
				if(chckbxManager.isSelected())
				{
					emp = new Manager(textname.getText(),Integer.parseInt(textid.getText()));
				}
				else
				{
					emp = new TeamMember(textname.getText(),Integer.parseInt(textid.getText()));
				}
				emp.setM_salary(Integer.parseInt(textsalary.getText()));
				m_Emps.add(emp);
				
				return emp.toString();
			}
		});
		btnAddEmp.setBounds(126, 212, 64, 23);
		panelEmp.add(btnAddEmp);
		
		
		JPanel panelManager = new JPanel();
		panelBase.add(panelManager, "name_48114720106936");
		panelManager.setLayout(null);
		panelManager.setBackground(new Color (119, 139, 235));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 56, 377, 86);
		panelManager.add(scrollPane_5);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(243, 127, 167, 58);

		
		JList<String> listEmpMatch = new JList<String>(modelEmpMatch);
		scrollPane_3.setViewportView(listEmpMatch);
		
		JList<String> listManagers = new JList<String>(modelManager);
		scrollPane_5.setViewportView(listManagers);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(10, 187, 377, 86);
		panelManager.add(scrollPane_7);
		
		JList<String> listManagerMatch = new JList<String>();
		listManagerMatch.setModel(modelManagerMatch);
		
		listManagerMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String value = listManagerMatch.getSelectedValue();
				
				for(Employee emp : m_Emps)
				{
					if(emp.getM_name().equals(value))
					{
						m_SelectedManager = ((Manager)emp);
					}
				}
				DefaultListModel<String> model = (DefaultListModel<String>) listEmpMatch.getModel();
				model.removeAllElements();
			}
		});
		JList<String> listManageEmp = new JList<String>(modelManageEmp);
		scrollPane_7.setViewportView(listManageEmp);
		
		JButton btnManage = new JButton("Manage");
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Manager manager = (Manager) searchEmpByString(listManagers.getSelectedValue());
				Employee e = searchEmpByString(listManageEmp.getSelectedValue());
				if(manager!=null && e!=null)
				{
					manager.addEmployee(e);
					m_Emps.remove(e);
					modelManageEmp.remove(listManageEmp.getSelectedIndex());
				}
				
			}
		});
		btnManage.setBounds(136, 153, 91, 23);
		panelManager.add(btnManage);
		
		JLabel lblPickManagerAnd = new JLabel("Pick manager and employers to manage:");
		lblPickManagerAnd.setBounds(10, 11, 217, 14);
		panelManager.add(lblPickManagerAnd);
		
		JPanel panelTask = new JPanel();
		panelTask.setBackground(new Color(234, 181, 67));
		panelBase.add(panelTask, "name_445273951423097");
		panelTask.setLayout(null);
		
		JLabel lblTasks = new JLabel("Tasks:");
		lblTasks.setBounds(6, 6, 55, 17);
		panelTask.add(lblTasks);
		
		
		
		
		
		
		listEmpMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				
				Employee manager = searchEmpByString(listManagerMatch.getSelectedValue());
				Employee e = null;
				for(Employee e_search : ((Manager)manager).getM_Emps())
				{
					if(e_search.getM_name().equals(listEmpMatch.getSelectedValue()))
					{
						e = e_search;
						break;
					}
				}
				
				
				modelETasks.removeAllElements();
				
				if(e.getTasks().size()!=0)
				{
					MessageBox("Size "+e.getTasks().size(),"BAB");
					for(Task t : e.getTasks())
					{
						modelETasks.add(modelETasks.getSize(), t.toString());
					}
				}
			}
		});
		
		listEmpMatch.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
			}
			
			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 24, 190, 116);
		panelTask.add(scrollPane);
		JList<String> listTaskMatch = new JList<String>(modelTaskMatch);
		scrollPane.setViewportView(listTaskMatch);
		
		JLabel lblTask = new JLabel("Task:");
		lblTask.setBounds(6, 152, 42, 17);
		panelTask.add(lblTask);
		
		JTextField textTask = new JTextField();
		textTask.setBounds(43, 151, 154, 18);
		panelTask.add(textTask);
		
		JLabel lblDaysToComplete = new JLabel("Days To Complete:");
		lblDaysToComplete.setBounds(6, 194, 114, 17);
		panelTask.add(lblDaysToComplete);
		
		JTextField textDays = new JTextField();
		textDays.setBounds(108, 188, 42, 23);
		panelTask.add(textDays);
		
		panelTask.add(scrollPane_3);
		
		JButton btnAddTask = new JButton("Add");
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = addTaskToTasks(textTask,textDays);
				modelTaskMatch.add(modelTaskMatch.getSize(),value);
			}
			
			private String addTaskToTasks(JTextField task,JTextField days)
			{
				String task_name = task.getText();
				int task_days = Integer.parseInt(days.getText());
				
				m_Tasks.add(new Task(task_name,task_days));
				
				return m_Tasks.get(m_Tasks.size()-1).toString();
			}
		});
		btnAddTask.setBounds(92, 276, 68, 20);
		panelTask.add(btnAddTask);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(243, 23, 167, 58);
		panelTask.add(scrollPane_2);
		
		scrollPane_2.setViewportView(listManagerMatch);
		
		JButton btnMatch = new JButton("Match");
		btnMatch.setBounds(243, 293, 68, 20);
		panelTask.add(btnMatch);
		btnMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String valueEmp = listEmpMatch.getSelectedValue();
				String valueTask = listTaskMatch.getSelectedValue();
				String valueManager = listManagerMatch.getSelectedValue();
				
				Employee Manager,employee;
				
				Task task;
				
				for(Task t_value : m_Tasks)
				{
					if(t_value.toString().equals(valueTask))
					{
						m_SelectedTask = t_value;
						break;
					}
					
				}
				
				for(Employee e : m_Emps)
				{
					if(e.getM_name().equals(valueManager))
					{
						if(valueEmp == null)
						{
							e.addTask(m_SelectedTask);
							m_Tasks.remove(m_SelectedTask);
							modelTaskMatch.remove(listTaskMatch.getSelectedIndex());
							break;
						}
						for(Employee e2 : ((Manager)e).getM_Emps())
						{
							if(e2.getM_name().equals(valueEmp)) 
							{
								e2.addTask(m_SelectedTask);
								m_Tasks.remove(m_SelectedTask);
								modelTaskMatch.remove(listTaskMatch.getSelectedIndex());
								break;
							}
						}
					}
				}
				
			}
			
		});
		
		JPanel panelTasksByEmp = new JPanel();
		panelBase.add(panelTasksByEmp, "name_47076661387660");
		panelTasksByEmp.setLayout(null);
		panelTasksByEmp.setBackground(new Color(120, 111, 166));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 46, 175, 102);
		panelTasksByEmp.add(scrollPane_4);
		
		
		
		JList<String> listTaskManagers = new JList<String>(modelManagerMatch);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(245, 46, 244, 102);
		panelTasksByEmp.add(scrollPane_6);
		
		DefaultListModel<String> modelEmpTasks = new DefaultListModel<String>();
		JList<String> listEmpTasks = new JList<String>(new DefaultListModel<String>());
		listEmpTasks.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				DefaultListModel<String> model = ((DefaultListModel<String>)listEmpTasks.getModel());
				String value = listEmpTasks.getSelectedValue();
				if(m_SelectedEmp==null)
					return;
				for(Task t : m_SelectedEmp.getTasks())
				{
					if(t.toString().equals(value))
					{
						m_SelectedTask = t;
						textTimeComplete.setText(m_SelectedTask.getM_DaysToComplete()+" days");
					}
				}
				
				
			}
		});
		scrollPane_6.setViewportView(listEmpTasks);
		
		
		scrollPane_4.setViewportView(listTaskManagers);
		
		JLabel lblNewLabel = new JLabel("Pick manager:");
		lblNewLabel.setBounds(10, 21, 90, 14);
		panelTasksByEmp.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Time done:");
		lblNewLabel_1.setBounds(245, 200, 90, 14);
		panelTasksByEmp.add(lblNewLabel_1);
		
		JLabel lblTime = new JLabel("Time to complete:");
		lblTime.setBounds(245, 163, 90, 14);
		panelTasksByEmp.add(lblTime);
		
		textTimeComplete = new JTextField();
		textTimeComplete.setEditable(false);
		textTimeComplete.setBounds(345, 160, 44, 20);
		panelTasksByEmp.add(textTimeComplete);
		textTimeComplete.setColumns(10);
		
		textTimeDone = new JTextField();
		textTimeDone.setBounds(318, 197, 44, 20);
		panelTasksByEmp.add(textTimeDone);
		textTimeDone.setColumns(10);
		
		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listEmpTasks.getSelectedValue().equals(m_SelectedTask.toString()))
				{
					m_SelectedTask.setM_DaysDone(Integer.parseInt(textTimeDone.getText()));
					MessageBox("SET","SET");
				}
			}
		});
		btnSet.setBounds(425, 223, 64, 23);
		panelTasksByEmp.add(btnSet);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(10, 197, 175, 102);
		panelTasksByEmp.add(scrollPane_9);
		
		JList<String> listTasksEmp = new JList<String>(new DefaultListModel<String>());
		listTasksEmp.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Employee manager = searchEmpByString(listTaskManagers.getSelectedValue());
				if(manager==null)
					return;
				Employee e = searchEmpInManager(listTasksEmp.getSelectedValue(),((Manager)manager));
				m_SelectedEmp = e;
				DefaultListModel<String> tasksmodel = (DefaultListModel<String>)listEmpTasks.getModel();
				
				tasksmodel.removeAllElements();
				
				if(manager!=null)
				{
					if(e!=null)
					{		
						for(Task task : e.getTasks())
						{
							tasksmodel.add(tasksmodel.getSize(), task.toString());
						}
					}
				}
			}
			
			private Employee searchEmpInManager(String value,Manager manager)
			{
				for(Employee e : manager.getM_Emps())
				{
					if(e.getM_name().equals(value))
					{
						
						return e;
					}
				}
				
				return null;
			}
		});
		scrollPane_9.setViewportView(listTasksEmp);
		
		JLabel lblPickEmployee = new JLabel("Pick employee:");
		lblPickEmployee.setBounds(10, 172, 90, 14);
		panelTasksByEmp.add(lblPickEmployee);
		
		JLabel lblTasks_1 = new JLabel("Tasks:");
		lblTasks_1.setBounds(245, 21, 46, 14);
		panelTasksByEmp.add(lblTasks_1);
		
		
		listTaskManagers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Employee e = searchEmpByString(listTaskManagers.getSelectedValue());
				DefaultListModel<String> model = (DefaultListModel<String>)listTasksEmp.getModel();
				DefaultListModel<String> tasksmodel = (DefaultListModel<String>)listEmpTasks.getModel();
				
				if(model.getSize()>0)
					model.removeAllElements();
				if(tasksmodel.getSize()>0)
					tasksmodel.removeAllElements();
				
				if(e!=null)
				{
					for(Employee emp : ((Manager)e).getM_Emps())
					{
						model.add(model.getSize(), emp.getM_name());
					}
					
					for(Task task : e.getTasks())
					{
						tasksmodel.add(tasksmodel.getSize(), task.toString());
					}
				}
				
				
				
			}
		});
		
		
		JLabel lblManagers = new JLabel("Managers:");
		lblManagers.setBounds(246, 6, 78, 14);
		panelTask.add(lblManagers);
		
		
		panelTask.add(scrollPane_8);
		
		JLabel lblEmployeeTasks = new JLabel("Employee tasks:");
		lblEmployeeTasks.setBounds(243, 196, 103, 14);
		panelTask.add(lblEmployeeTasks);
		
		JButton btnShowEmployees = new JButton("Show Employees");
		btnShowEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//modelEmpMatch.removeAllElements();
				DefaultListModel<String> model = (DefaultListModel<String>) listEmpMatch.getModel();
				if(m_SelectedManager.getM_name().equals(listManagerMatch.getSelectedValue()))
				{
					model.removeAllElements();
					for(Employee emp : m_SelectedManager.getM_Emps())
					{
						model.add(model.getSize(), emp.getM_name());
					}
				}
			}
			
		});
		btnShowEmployees.setBounds(253, 92, 138, 23);
		panelTask.add(btnShowEmployees);
		
		JPanel panelBonus = new JPanel();
		panelBase.add(panelBonus, "name_784385680774971");
		panelBonus.setBackground(new Color(123, 237, 159));
		panelBonus.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(10, 25, 157, 203);
		panelMenu.setBackground(new Color(154, 236, 219));
		frmEmployeeTasksManager.getContentPane().add(panelMenu);
		
		
		
		JButton btnTasks = new JButton("Tasks");
		btnTasks.setBounds(10, 79, 137, 23);
		btnTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(panelTask);
				modelManagerMatch.removeAllElements();
				
				for(Employee e : m_Emps)
				{
					if(e instanceof Manager)
					{
						modelManagerMatch.add(modelManagerMatch.getSize(), e.getM_name());
					}
				}
				
			//	listTasksMatch.setModel(listTask.getModel()); // switch to action listener on object in order to load new items from task list
				
				//create new list for only TeamMembers
				
			}
		});
		
		JButton btnEmp = new JButton("Employees");
		btnEmp.setBounds(10, 11, 137, 23);
		btnEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panelEmp);
			}
		});
		panelMenu.setLayout(null);
		panelMenu.add(btnTasks);
		panelMenu.add(btnEmp);
		
		JButton btnTasksByEmp = new JButton("Tasks by employee");
		btnTasksByEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(panelTasksByEmp);	
				
				

			}
		});
		btnTasksByEmp.setBounds(10, 113, 137, 23);
		panelMenu.add(btnTasksByEmp);
		
		JButton btnManager = new JButton("Manager");
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(panelManager);
				modelManageEmp.removeAllElements();
				modelManager.removeAllElements();
				for(Employee e: m_Emps)
				{
					if(e instanceof TeamMember)
					{
						modelManageEmp.add(modelManageEmp.getSize(), e.toString());
					}
					else
					{
						modelManager.add(modelManager.getSize(), e.toString());
					}
				}
				
			}
		});
		btnManager.setBounds(10, 45, 137, 23);
		panelMenu.add(btnManager);
		
		JButton btnBonusInfo = new JButton("Bonus info");
		btnBonusInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panelBonus);
			}
		});
		btnBonusInfo.setBounds(10, 147, 137, 23);
		panelMenu.add(btnBonusInfo);
		
		panelBase.removeAll();
	}
	
	
	private void switchPanel(JPanel panel)
	{
		for(Component j : panel.getComponents())
		{
			if(j instanceof JTextField)
			{
				((JTextField) j).setText("");
			}
		}
		
		panelBase.removeAll();
		panelBase.add(panel);
		panelBase.repaint();
		panelBase.revalidate();
	}
	
	private void MessageBox(String header,String text)
	{
		JDialog jd = new JDialog(frmEmployeeTasksManager,header);
		JLabel jl = new JLabel(text,JLabel.CENTER);
		jd.getContentPane().add(jl);
		jd.setBounds(200,300,200,100);
	
		jd.setVisible(true);
	}
	
	private Employee searchEmpByString(String value)
	{
		for(Employee e : m_Emps)
		{
			if(e.getM_name().equals(value) || e.toString().equals(value))
			{
				m_SelectedEmp = e;
				return e;
			}
		}
		
		return null;
	}
}
