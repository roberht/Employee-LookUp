// Written by Robert Herrera
abstract class Employee
{
	private String name;
	private String address;
	private String employerID;
	private String bossID;
	
	public Employee(String n, String a, String empID, String boss)
	{
		name = n;
		address = a;
		employerID = empID;
		bossID = boss;
	}
	public String getName()
	{
		return name;
	}
	public String getID()
	{
		return employerID;
	}
	public String getBossID()
	{
		return bossID;
	}
}
