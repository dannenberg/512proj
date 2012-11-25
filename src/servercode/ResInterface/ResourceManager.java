package ResInterface;


import ResImpl.TransactionAbortedException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import ResImpl.RMHashtable;

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

public interface ResourceManager extends Remote 
{
    /* Add seats to a flight.  In general this will be used to create a new
     * flight, but it should be possible to add seats to an existing flight.
     * Adding to an existing flight should overwrite the current price of the
     * available seats.
     *
     * @return success.
     */
    public boolean addFlight(int id, int flightNum, int flightSeats, int flightPrice) 
	throws RemoteException, TransactionAbortedException; 
    
    /* Add cars to a location.  
     * This should look a lot like addFlight, only keyed on a string location
     * instead of a flight number.
     */
    public boolean addCars(int id, String location, int numCars, int price) 
	throws RemoteException, TransactionAbortedException; 
   
    /* Add rooms to a location.  
     * This should look a lot like addFlight, only keyed on a string location
     * instead of a flight number.
     */
    public boolean addRooms(int id, String location, int numRooms, int price) 
	throws RemoteException, TransactionAbortedException; 			    

    public boolean deleteItem(int id, String key)
    throws RemoteException;
    
    /* new customer with providing id */
    public boolean newCustomer(int id, int cid)
    throws RemoteException, TransactionAbortedException;

    /**
     *   Delete the entire flight.
     *   deleteflight implies whole deletion of the flight.  
     *   all seats, all reservations.  If there is a reservation on the flight, 
     *   then the flight cannot be deleted
     *
     * @return success.
     */   
    public boolean deleteFlight(int id, int flightNum) 
	throws RemoteException, TransactionAbortedException; 
    
    /* Delete all Cars from a location.
     * It may not succeed if there are reservations for this location
     *
     * @return success
     */		    
    public boolean deleteCars(int id, String location) 
	throws RemoteException, TransactionAbortedException; 

    /* Delete all Rooms from a location.
     * It may not succeed if there are reservations for this location.
     *
     * @return success
     */
    public boolean deleteRooms(int id, String location) 
	throws RemoteException, TransactionAbortedException; 
    
    /* deleteCustomer removes the customer and associated reservations */
    public boolean deleteCustomer(int id,int customer) 
	throws RemoteException, TransactionAbortedException; 

    /* queryFlight returns the number of empty seats. */
    public int queryFlight(int id, int flightNumber) 
	throws RemoteException, TransactionAbortedException; 

    /* return the number of cars available at a location */
    public int queryCars(int id, String location) 
	throws RemoteException, TransactionAbortedException; 

    /* return the number of rooms available at a location */
    public int queryRooms(int id, String location) 
	throws RemoteException, TransactionAbortedException; 

    /* return a bill */
    public String queryCustomerInfo(int id,int customer) 
	throws RemoteException, TransactionAbortedException; 
    
    /* queryFlightPrice returns the price of a seat on this flight. */
    public int queryFlightPrice(int id, int flightNumber) 
	throws RemoteException, TransactionAbortedException; 

    /* return the price of a car at a location */
    public int queryCarsPrice(int id, String location) 
	throws RemoteException, TransactionAbortedException; 

    public RMHashtable getCustomerReservations(int id, int customerID)
    throws RemoteException;

    /* return the num */
    public int queryNum(int id, String location) 
	throws RemoteException; 

    /* return the price  */
    public int queryPrice(int id, String location) 
	throws RemoteException; 

    /* return the price of a room at a location */
    public int queryRoomsPrice(int id, String location) 
	throws RemoteException, TransactionAbortedException; 

    /* Reserve a seat on this flight*/
    public boolean reserveFlight(int id, int customer, int flightNumber) 
	throws RemoteException, TransactionAbortedException; 

    /* reserve a car at this location */
    public boolean reserveCar(int id, int customer, String location) 
	throws RemoteException, TransactionAbortedException; 

    /* reserve a room certain at this location */
    public boolean reserveRoom(int id, int customer, String locationd) 
	throws RemoteException, TransactionAbortedException; 

    public void incrementItem(int id, String key, int change) 
    throws RemoteException;

    public void decrementItem(int id, String key) 
    throws RemoteException;

    /* transaction stuff */
    public int start() throws RemoteException;
    public boolean commit(int transactionId) throws RemoteException; //, TransactionAbortedException,  InvalidTransactionException;
    public void abort(int transactionId) throws RemoteException; //, InvalidTransactionException;

    public void enlist(int trxnId) throws RemoteException;

    public boolean shutdown() throws RemoteException;
}
