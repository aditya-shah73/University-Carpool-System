
public class Payment {
	private int creditPoint;
	private double cash;
	
	public Payment(){
		this.creditPoint = 20;
		this.cash = 20;
	}
	
	public void addCash(double cash){
		this.cash += cash;
	}
	
	public void addCredit(int credit){
		this.creditPoint += credit;
	}
	
	public void payByCash(double cashPaid){
		this.cash -= cashPaid;
		System.out.println("Paid by cash. Amount: $" + cashPaid);
	}
	
	public void payByCredit(int creditPaid){
		this.creditPoint -= creditPaid;
		System.out.println("Paid by credit. Amount: $" + creditPaid);
	}
	public int getCreditPoint(){
		return this.creditPoint;
	}
	public double getCash(){
		return this.cash;
	}
}
