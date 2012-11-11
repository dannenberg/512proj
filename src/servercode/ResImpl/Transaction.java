package ResImpl;

public class Transaction
{
	public static enum Action {BOOK, CREATE, DELETE, UNBOOK};
	public int id;		// my programming language has no tuples
	public String key;	// then how does it smell?
	public Action action;	// terrible!
	private int numDeletedOrCustId;
	public int price;

	public Transaction(int id, String key, Action action)
	{	// CREATE
		this.id = id;
		this.key = key;
		this.action = action;
	}

	public Transaction(int id, String key, Action action, int custId)
	{	// BOOKING
		this.id = id;
		this.key = key;
		this.action = action;
		this.numDeletedOrCustId = custId;
	}

	public Transaction(int id, String key, Action action, int numDeleted, int price)
	{	// DELETING  / UNBOOKING
		this.id = id;
		this.key = key;
		this.action = action;
		this.numDeletedOrCustId = numDeleted;
	}

	public int custId()
		{return numDeletedOrCustId;}

	public int numDeleted()
		{return numDeletedOrCustId;}
}