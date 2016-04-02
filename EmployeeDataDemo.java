// Written by Robert Herrera
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class EmployeeDataDemo 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		String fileName = "employeeData.txt";			//change as needed
		FileReader file = new FileReader(fileName);
		Scanner fileScanner = new Scanner(file);
		String line;
		ArrayList employeeContainer = new ArrayList();	//will hold employee objects

		while (fileScanner.hasNext())
		{
			line = fileScanner.nextLine();				
			String[] employeeData = line.split(";");	//temporarily store line from textfile 
			for (int i = 0; i < employeeData.length; i++)
			{
				if(employeeData[i].equalsIgnoreCase("Hourly"))	// name,  address,  employerID,  bossID,  pay,  hours
				{
					employeeContainer.add(new HourlyEmployee(employeeData[1],employeeData[2],employeeData[3],employeeData[4],Double.parseDouble(employeeData[5]),Double.parseDouble(employeeData[6])));		
				}
				if(employeeData[i].equalsIgnoreCase("Salaried"))											//salary
				{
					employeeContainer.add(new SalariedEmployee(employeeData[1],employeeData[2],employeeData[3],employeeData[4],Double.parseDouble(employeeData[5])));		
				}
				if(employeeData[i].equalsIgnoreCase("Supervisor"))											//salary,  bonus
				{
					employeeContainer.add(new Supervisor(employeeData[1],employeeData[2],employeeData[3],employeeData[4],Double.parseDouble(employeeData[5]), Double.parseDouble(employeeData[6])));		
				}
			}	
		}
				
		//objects have been created
		Scanner kb = new Scanner(System.in);
		NumberFormat formatter = new DecimalFormat("#0.00");	
		
		char choice;
		System.out.println("Employee Lookup Program");
		System.out.println("A) Find all employees with a given title");
		System.out.println("B) Find a single employee");
		System.out.println("X) Exit the program");
		System.out.println("Enter your choice:");
		choice = kb.next().charAt(0);
		switch (choice)
			{
				case 'a':	
				case 'A': System.out.println("1) Hourly Employees");
						  System.out.println("2) Salaried Employees");
						  System.out.println("3) Supervisory Employees");
						  System.out.println("Enter 1, 2, or 3:");
						  choice = (char) kb.nextInt();
						  switch (choice)
						  	{
						  		case 1:		
						  				System.out.println("Name\t\t\tID\t\tGross");
						  				for(int i=0; i<employeeContainer.size();i++)
						  				{
						  					if (employeeContainer.get(i) instanceof HourlyEmployee)
											{
												String name = ((HourlyEmployee) employeeContainer.get(i)).getName();
												String empID = ((HourlyEmployee) employeeContainer.get(i)).getID();
												double pay = ((HourlyEmployee) employeeContainer.get(i)).getGrossWeeklyPay();
												System.out.println(name +"\t\t" + empID +"\t\t" + formatter.format(pay));
											}
						  				}
						  				break;
						  		case 2:
						  				System.out.println("Name\t\t\tID\t\tGross");
							  			for(int i=0; i<employeeContainer.size();i++)
					  					{																//negate supervisor otherwise will print their info since it is derived from salaried
					  						if ((employeeContainer.get(i) instanceof SalariedEmployee)&& (!(employeeContainer.get(i) instanceof Supervisor)))	
											{
					  							String name = ((SalariedEmployee) employeeContainer.get(i)).getName();
												String empID = ((SalariedEmployee) employeeContainer.get(i)).getID();
												double pay = ((SalariedEmployee) employeeContainer.get(i)).getGrossWeeklyPay();
												System.out.println(name +"\t\t" + empID +"\t\t" + formatter.format(pay));
											}
					  					}
							  			break;
						  		case 3:
						  				System.out.println("Name\t\t\tID\t\tGross Weekly Pay\t\tDirect Reports");
							  			for(int i=0; i<employeeContainer.size();i++)
					  					{
					  						if (employeeContainer.get(i) instanceof Supervisor)
											{		
					  							int singleEmpIndex = i;
					  							String name = ((Supervisor) employeeContainer.get(i)).getName();
												String empID = ((Supervisor) employeeContainer.get(i)).getID();
												String temp;
												String directReports = "";
												for(int index=0; index <employeeContainer.size();index++)
					  							{	// check all employees' bossIDs to see if supervisor at this index is their boss
					  								if (empID.equals(((Employee) employeeContainer.get(index)).getBossID()))
					  								{
					  									temp = ((Employee) employeeContainer.get(index)).getID();
					  									directReports +=temp +" ";
					  								}
					  							}					  							
												double pay = ((Supervisor) employeeContainer.get(i)).getGrossWeeklyPay();
												System.out.println(name +"\t\t" + empID +"\t\t" + formatter.format(pay) + "\t\t\t\t"+directReports);
											}
					  					}
							  			break;
						  	}
						break;
				case 'b':
				case 'B': 
						System.out.println("Enter the ID of the employee: ");
						String input = kb.next();
						Boolean status = false;
						int singleEmpIndex = 0;
						for (int i=0; i<employeeContainer.size();i++)
						{
							if (input.equals(((Employee) employeeContainer.get(i)).getID()))
							{
								status = true;
								singleEmpIndex = i;
								if (employeeContainer.get(singleEmpIndex) instanceof HourlyEmployee)
								{
									String name = ((HourlyEmployee) employeeContainer.get(singleEmpIndex)).getName();
									String empID = ((HourlyEmployee) employeeContainer.get(singleEmpIndex)).getID();
									double pay = ((HourlyEmployee) employeeContainer.get(singleEmpIndex)).getGrossWeeklyPay();
									System.out.println("Name\t\t\tID\t\tGross");
									System.out.println(name +"\t\t" + empID +"\t\t" + formatter.format(pay));
								}
								if (employeeContainer.get(singleEmpIndex) instanceof Supervisor)
								{
		  							
									String name = ((Supervisor) employeeContainer.get(singleEmpIndex)).getName();
									String empID = ((Supervisor) employeeContainer.get(singleEmpIndex)).getID();
									double pay = ((Supervisor) employeeContainer.get(singleEmpIndex)).getGrossWeeklyPay();
									String temp;
									String directReports = "";
									for(int index=0; index <employeeContainer.size();index++)
		  							{
		  								if (empID.equals(((Employee) employeeContainer.get(index)).getBossID()))
		  								{
		  									temp = ((Employee) employeeContainer.get(index)).getID();
		  									directReports +=temp +" ";
		  								}
		  							}	
									System.out.println("Name\t\t\tID\t\tGross Weekly Pay\t\tDirect Reports");
									System.out.println(name +"\t\t" + empID +"\t\t" + formatter.format(pay) +"\t\t\t\t" + directReports);
								}
								if (employeeContainer.get(singleEmpIndex) instanceof SalariedEmployee && (!(employeeContainer.get(i) instanceof Supervisor)))
								{
		  							String name = ((SalariedEmployee) employeeContainer.get(singleEmpIndex)).getName();
									String empID = ((SalariedEmployee) employeeContainer.get(singleEmpIndex)).getID();
									double pay = ((SalariedEmployee) employeeContainer.get(singleEmpIndex)).getGrossWeeklyPay();
									System.out.println("Name\t\t\tID\t\tGross");
									System.out.println(name +"\t\t" + empID +"\t\t" + formatter.format(pay));
								}
							}
						}
						if(!status)
						{
							System.out.println("Sorry, no employee with ID "+input +" was found.");
						}
						break;
				case 'x':
				case 'X':	System.exit(1);
						break;
			}
	}
}
