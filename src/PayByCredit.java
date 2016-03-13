public class PayByCredit extends Payment
{
	public PayByCredit()
	{
		super();
	}
	
	public void addCredit(int credit)
	{
		this.creditPoint += credit;
	}
	
	public void payByCredit(int creditPaid)
	{
		this.creditPoint -= creditPaid;
		System.out.println("Paid by credit. Amount: $" + creditPaid);
	}
	
	public int getCreditPoint()
	{
		return this.creditPoint;
	}
}
