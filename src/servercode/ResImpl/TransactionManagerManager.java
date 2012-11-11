package ResImpl;

import ResInterface.ResourceManager;
import LockManager.*;
import java.util.HashMap;
import java.util.HashSet;

public class TransactionManagerManager
{
	private HashMap<Integer, HashSet<ResourceManager>> transactionTouch;
	private LockManager lm;
	private int nextTrxnId;

	public TransactionManagerManager()
	{
		transactionTouch = new HashMap();
        lm = new LockManager();
		nextTrxnId = 0;
	}

	public int start()
	{
		//writes.put(nextTrxnId, new ArrayList());
		transactionTouch.put(nextTrxnId, new HashSet());
		return nextTrxnId++;
	}

	public void enlist(int t, ResourceManager rm)
	{
		if(!transactionTouch.containsKey(t))
			transactionTouch.put(t, new HashSet());
		transactionTouch.get(t).add(rm);
	}

	public void abort(int trxnId)
	{
		lm.UnlockAll(trxnId);
		// actually call the other Managers for the aborts
	}

	public void commit(int trxnId)
	{
		lm.UnlockAll(trxnId);
		// actually call the other Managers for the commits
	}

	public boolean lock(int trxnId, String strData, int lockType, ResourceManager rm) throws DeadlockException
	{
		if(rm == null)
			Trace.info("   !!!!! Hey, you didn't change the RM to stop being null when sent to lock.");
		if(lockType == LockManager.WRITE)
			enlist(trxnId, rm);
		return lm.Lock(trxnId, strData, lockType);
	}
}
