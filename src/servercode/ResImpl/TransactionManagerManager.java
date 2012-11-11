package ResImpl;

import ResInterface.ResourceManager;
import LockManager.*;
import java.util.HashMap;
import java.util.HashSet;

public class TransactionManagerManager
{
	private HashMap<Integer, HashSet<ResourceManager>> transactionTouch;
	public TransactionManagerManager()
	{
		transactionTouch = new HashMap();
	}

	public void enlist(int t, ResourceManager rm)
	{
		if(!transactionTouch.containsKey(t))
			transactionTouch.put(t, new HashSet());
		transactionTouch.get(t).add(rm);
	}
}
