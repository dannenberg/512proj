package ResImpl;

import ResInterface.*;

import java.util.*;
import java.rmi.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMCluster extends HashSet<ResourceManager>
implements ResourceManager
{
    public synchronized boolean addFlight(int id, int flightNum, int flightSeats, int flightPrice)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.addFlight(id, flightNum, flightSeats, flightPrice);
        return toR;
    }

    public synchronized boolean addCars(int id, String location, int numCars, int price)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.addCars(id, location, numCars, price);
        return toR;
    }

    public synchronized boolean addRooms(int id, String location, int numRooms, int price)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.addRooms(id, location, numRooms, price);
        return toR;
    }

    public synchronized boolean deleteItem(int id, String key)
    throws RemoteException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.deleteItem(id, key);
        return toR;
    }

    public synchronized boolean newCustomer(int id, int cid)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.newCustomer(id, cid);
        return toR;
    }

    public synchronized boolean deleteFlight(int id, int flightNum)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.deleteFlight(id, flightNum);
        return toR;
    }

    public synchronized boolean deleteCars(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.deleteCars(id, location);
        return toR;
    }

    public synchronized boolean deleteRooms(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.deleteRooms(id, location);
        return toR;
    }

    public synchronized boolean deleteCustomer(int id,int customer)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.deleteCustomer(id, customer);
        return toR;
    }

    public synchronized int queryFlight(int id, int flightNumber)
    throws RemoteException, TransactionAbortedException
    {
        // int toR = -1;
        // int temp;
        // for(ResourceManager r : this)
        // {
        //     temp = r.queryFlight(id, flightNumber);
        //     if (toR != -1 && temp != toR)
        //     {   // inconsistent data
        //         ;
        //     }
        //     toR = temp;
        // }
        // return toR;
        for(ResourceManager r : this)
            return r.queryFlight(id, flightNumber);
        return -1;
    }

    public synchronized int queryCars(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        for(ResourceManager r : this)
            return r.queryCars(id, location);
        return -1;
    }

    public synchronized int queryRooms(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        for(ResourceManager r : this)
            return r.queryRooms(id, location);
        return -1;
    }

    public synchronized String queryCustomerInfo(int id,int customer)
    throws RemoteException, TransactionAbortedException
    {
        for(ResourceManager r : this)
            return r.queryCustomerInfo(id, customer);
        return "";
    }

    public synchronized int queryFlightPrice(int id, int flightNumber)
    throws RemoteException, TransactionAbortedException
    {
        for(ResourceManager r : this)
            return r.queryFlightPrice(id, flightNumber);
        return -1;
    }

    public synchronized int queryCarsPrice(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        for(ResourceManager r : this)
            return r.queryCarsPrice(id, location);
        return -1;
    }

    public synchronized RMHashtable getCustomerReservations(int id, int customerID)
    throws RemoteException
    {
        for(ResourceManager r : this)
            return r.getCustomerReservations(id, customerID);
        return null;
    }

    /* return the num */
    public synchronized int queryNum(int id, String location)
    throws RemoteException
    {
        for(ResourceManager r : this)
            return r.queryNum(id, location);
        return -1;
    }

    /* return the price  */
    public synchronized int queryPrice(int id, String location)
    throws RemoteException
    {
        for(ResourceManager r : this)
            return r.queryPrice(id, location);
        return -1;
    }

    /* return the price of a room at a location */
    public synchronized int queryRoomsPrice(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        for(ResourceManager r : this)
            return r.queryRoomsPrice(id, location);
        return -1;
    }

    /* Reserve a seat on this flight*/
    public synchronized boolean reserveFlight(int id, int customer, int flightNumber)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.reserveFlight(id, customer, flightNumber);
        return toR;
    }

    /* reserve a car at this location */
    public synchronized boolean reserveCar(int id, int customer, String location)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.reserveCar(id, customer, location);
        return toR;
    }

    /* reserve a room certain at this location */
    public synchronized boolean reserveRoom(int id, int customer, String location)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.reserveRoom(id, customer, location);
        return toR;
    }

    public synchronized void incrementItem(int id, String key, int change)
    throws RemoteException
    {
        for(ResourceManager r : this)
            r.incrementItem(id, key, change);
    }

    public synchronized void decrementItem(int id, String key)
    throws RemoteException
    {
        for(ResourceManager r : this)
            toR = r.decrementItem(id, key);
    }

    public synchronized int start() throws RemoteException
    {
        for(ResourceManager r : this)
            r.start();
        return 0;
    }

    public synchronized boolean commit(int transactionId) throws RemoteException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.commit(transactionId);
        return toR;
    }

    public synchronized void abort(int transactionId) throws RemoteException
    {
        for(ResourceManager r : this)
            r.reserveFlight(id, customer, flightNumber);
    }

    public synchronized void enlist(int trxnId) throws RemoteException
    {
        for(ResourceManager r : this)
            r.enlist(trxnId);
    }

    public synchronized boolean shutdown() throws RemoteException
    {
        boolean toR = false;
        for(ResourceManager r : this)
            toR = r.shutdown();
        return toR;
    }
}