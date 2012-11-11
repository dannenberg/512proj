package ResImpl;

import LockManager.*;
import java.util.HashMap;
import java.util.ArrayList;

class TransactionManager
{
	private HashMap<Integer, ArrayList<Transaction>> writes;
	private int nextTrxnId;
	public TransactionManager()
	{
		writes = new HashMap();
		nextTrxnId = 0;
	}

	public int start()
	{
		writes.put(nextTrxnId, new ArrayList());
		return nextTrxnId++;
	}

	public void addCreate(int id, String key)
	{
		writes.get(id).add(0, new Transaction(id, key, Transaction.Action.CREATE));
	}

	public void addBook(int id, String key, int custId)
	{
		writes.get(id).add(0, new Transaction(id, key, Transaction.Action.BOOK, custId));
	}

	public void addDelete(int id, String key, int numDeleted, int price)
	{
		writes.get(id).add(0, new Transaction(id, key, Transaction.Action.DELETE, numDeleted, price));
	}

	public void addUnbook(int id, String key, int custId, int price)
	{
		writes.get(id).add(0, new Transaction(id, key, Transaction.Action.UNBOOK, custId, price));
	}

	public ArrayList<Transaction> getTrxns(int trxnId)
	{
		return writes.get(trxnId);
	}

	public void abort(int trxnId)
	{
		writes.remove(trxnId);
	}

	public void commit(int trxnId)
	{	// DONE
		writes.remove(trxnId);
	}
}
