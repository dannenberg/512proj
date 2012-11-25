package ResInterface;


import java.rmi.Remote;
import java.rmi.RemoteException;
import ResImpl.TransactionAbortedException;

import java.util.*;
/** 
 * Simplified version from CSE 593 Univ. of Washington
 *
 * Distributed  System in Java.
 * 
 * failure reporting is done using two pieces, exceptions and boolean 
 * return values.  Exceptions are used for systemy things. Return
 * values are used for operations that would affect the consistency
 * 
 * If there is a boolean return value and you're not sure how it 
 * would be used in your implementation, ignore it.  I used boolean
 * return values in the interface generously to allow flexibility in 
 * implementation.  But don't forget to return true when the operation
 * has succeeded.
 */

public interface MiddleWare extends ResourceManager 
{
    public void clientAbort(int transactionId) throws RemoteException;
    public void clientCommit(int transactionId)
        throws RemoteException, TransactionAbortedException;
    public boolean itinerary(int id,int customer,Vector flightNumbers,String location,boolean Car,boolean Room)
        throws RemoteException, TransactionAbortedException;
    public int newCustomer(int id) throws RemoteException, TransactionAbortedException;
}
