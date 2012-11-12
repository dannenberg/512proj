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
        try {
		rm.enlist(t);
        }
        catch (Exception e) {
            System.out.println("BNOOOOOOO");
        }
	}

	public void abort(int trxnId)
	{
		// actually call the other Managers for the aborts
		for(ResourceManager rm : transactionTouch.get(trxnId))
		{
			try {
				rm.abort(trxnId);
			} catch (Exception e)
			{
                System.out.println("EXCEPTION:");
                System.out.println(e.getMessage());
                e.printStackTrace();
			}
		}
		transactionTouch.remove(trxnId);
		lm.UnlockAll(trxnId);
	}

	public void commit(int trxnId)
	{
		// actually call the other Managers for the commits
		for(ResourceManager rm : transactionTouch.get(trxnId))
		{
			try {
				rm.commit(trxnId);
			} catch (Exception e)
			{
                System.out.println("EXCEPTION:");
                System.out.println(e.getMessage());
                e.printStackTrace();
			}
		}
		transactionTouch.remove(trxnId);
		lm.UnlockAll(trxnId);
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
