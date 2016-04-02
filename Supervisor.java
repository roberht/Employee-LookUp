// Written by Robert Herrera
public class Supervisor extends SalariedEmployee
{
	double annualBonus;
	double salary;
	Supervisor(String name, String address, String eID, String boss, double sal, double bonus)
	{
		super(name, address, eID, boss, sal);
		annualBonus = bonus;
		salary = sal;
	}
	public double getAnnualBonus()
	{
		return annualBonus;
	}
	public double getSalary()
	{
		return salary;
	}
	public double getGrossWeeklyPay()
	{
		return (getSalary() + getAnnualBonus())/52;
	}
}
