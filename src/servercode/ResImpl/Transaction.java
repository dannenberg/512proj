package ResImpl;

public class Transaction
{
	public static enum Action {BOOK, CREATE, DELETE, UNBOOK, STOCK, UPDATE};
	public int id;		// my programming language has no tuples
	public String key;	// then how does it smell?
	public Action action;	// terrible!
	private int numDeletedOrCustIdOrAmount;
	public int price;

	public Transaction(int id, String key, Action action)
	{	// CREATE
		this.id = id;
		this.key = key;
		this.action = action;
	}

	public Transaction(int id, String key, Action action, int custIdOrAmount)
	{	// BOOK / STOCK
		this.id = id;
		this.key = key;
		this.action = action;
		this.numDeletedOrCustIdOrAmount = custIdOrAmount;
	}

	public Transaction(int id, String key, Action action, int numDeletedOrAmount, int price)
	{	// DELETE  / UNBOOK / UPDATE
		this.id = id;
		this.key = key;
		this.price = price;
		this.action = action;
		this.numDeletedOrCustIdOrAmount = numDeleted;
	}

	public int custId()
		{return numDeletedOrCustIdOrAmount;}

	public int numDeleted()
		{return numDeletedOrCustIdOrAmount;}

	public int amount()
		{return numDeletedOrCustIdOrAmount;}
}