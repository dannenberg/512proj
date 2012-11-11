package ResImpl;

import LockManager.*;
import java.util.HashMap;
import java.util.ArrayList;

class TransactionManager
{
	private HashMap<Integer, ArrayList<Transaction>> writes;
	private LockManager lm;
	private int nextTrxnId;
	public TransactionManager()
	{
		writes = new HashMap();
		lm = new LockManager();
		nextTrxnId = 0;
	}

	public boolean lock(int trxnId, String strData, int lockType) throws DeadlockException
	{
		return lm.Lock(trxnId, strData, lockType);
	}

	public int start()
	{
		writes.put(nextTrxnId, new ArrayList());
		return nextTrxnId++;
	}

	public void add(int trxnId, int id, String key, Transaction.Action action)
	{
		writes.get(trxnId).add(new Transaction(id, key, action));
	}

	public void addDelete(int trxnId, int id, String key, int numDeleted)
	{
		writes.get(trxnId).add(new Transaction(id, key, Transaction.Action.DELETED, numDeleted));
	}

	public ArrayList<Transaction> getTrxns(int trxnId)
	{
		return writes.get(trxnId);
	}

	public void abort(int trxnId)
	{
		lm.UnlockAll(trxnId);
		writes.remove(trxnId);
	}

	public void commit(int trxnId)
	{	// DONE
		lm.UnlockAll(trxnId);
		writes.remove(trxnId);
	}
}
