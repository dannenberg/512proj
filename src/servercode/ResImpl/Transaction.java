package ResImpl;

public class Transaction
{
	public static enum Action {BOOK, CREATE, DELETE};
	public int id;		// my programming language has no tuples
	public String key;	// then how does it smell?
	public Action action;	// terrible!
	public int numDeleted;

	public Transaction(int id, String key, Action action)
	{
		this.id = id;
		this.key = key;
		this.action = action;
		this.numDeleted = 0;
	}

	public Transaction(int id, String key, Action action, int numDeleted)
	{
		this.id = id;
		this.key = key;
		this.action = action;
		this.numDeleted = numDeleted;
	}
}