// Written by Robert Herrera
public class SalariedEmployee extends Employee
{
	private double salary;
	SalariedEmployee(String name, String address, String eID, String boss, double sal)
	{
		super(name, address, eID, boss);
		salary = sal;
	}
	public double getSalary()
	{
		return salary;
	}
	public double getGrossWeeklyPay()
	{
		return getSalary() / 52;
	}
}
