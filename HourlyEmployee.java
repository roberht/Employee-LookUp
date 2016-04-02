// Written by Robert Herrera
public class HourlyEmployee extends Employee
{
	private double hourlyPay;
	private double hoursWorked;
	HourlyEmployee(String name, String address, String eID, String boss, double pay, double hours)
	{
		super(name, address, eID, boss);
		hourlyPay = pay;
		hoursWorked = hours;
	}
	public double getHourlyPay()
	{
		return hourlyPay;
	}
	public double getHoursWorked()
	{
		return hoursWorked;
	}
	public double getGrossWeeklyPay()
	{
		return getHourlyPay() * getHoursWorked();
	}
}
