package ResImpl;

import ResInterface.*;

import java.util.*;
import java.rmi.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMCluster extends ArrayList<ResourceManager>
implements ResourceManager
{
    public synchronized boolean addFlight(int id, int flightNum, int flightSeats, int flightPrice)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.addFlight(id, flightNum, flightSeats, flightPrice);
            } catch (RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized void onError(int[] i)
    {
        remove(i[0]--);
    }

    public synchronized boolean addCars(int id, String location, int numCars, int price)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.addCars(id, location, numCars, price);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized boolean addRooms(int id, String location, int numRooms, int price)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.addRooms(id, location, numRooms, price);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized boolean deleteItem(int id, String key)
    throws RemoteException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.deleteItem(id, key);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized boolean newCustomer(int id, int cid)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.newCustomer(id, cid);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized boolean deleteFlight(int id, int flightNum)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.deleteFlight(id, flightNum);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized boolean deleteCars(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.deleteCars(id, location);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized boolean deleteRooms(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.deleteRooms(id, location);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized boolean deleteCustomer(int id,int customer)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.deleteCustomer(id, customer);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized int queryFlight(int id, int flightNumber)
    throws RemoteException, TransactionAbortedException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                return r.queryFlight(id, flightNumber);
            } catch(RemoteException re)
                {onError(i);}
        }
        return -1;  /* TODO: should throw NoRMsLeftException */
    }

    public synchronized int queryCars(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                return r.queryCars(id, location);
            } catch(RemoteException re)
                {onError(i);}
        }
        return -1;
    }

    public synchronized int queryRooms(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                return r.queryRooms(id, location);
            } catch(RemoteException re)
                {onError(i);}
        }
        return -1;
    }

    public synchronized String queryCustomerInfo(int id,int customer)
    throws RemoteException, TransactionAbortedException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                return r.queryCustomerInfo(id, customer);
            } catch(RemoteException re)
                {onError(i);}
        }
        return "";
    }

    public synchronized int queryFlightPrice(int id, int flightNumber)
    throws RemoteException, TransactionAbortedException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                return r.queryFlightPrice(id, flightNumber);
            } catch(RemoteException re)
                {onError(i);}
        }
        return -1;
    }

    public synchronized int queryCarsPrice(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                return r.queryCarsPrice(id, location);
            } catch(RemoteException re)
                {onError(i);}
        }
        return -1;
    }

    public synchronized RMHashtable getCustomerReservations(int id, int customerID)
    throws RemoteException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                return r.getCustomerReservations(id, customerID);
            } catch(RemoteException re)
                {onError(i);}
        }
        return null;
    }

    /* return the num */
    public synchronized int queryNum(int id, String location)
    throws RemoteException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                return r.queryNum(id, location);
            } catch(RemoteException re)
                {onError(i);}
        }
        return -1;
    }

    /* return the price  */
    public synchronized int queryPrice(int id, String location)
    throws RemoteException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                return r.queryPrice(id, location);
            } catch(RemoteException re)
                {onError(i);}
        }
        return -1;
    }

    /* return the price of a room at a location */
    public synchronized int queryRoomsPrice(int id, String location)
    throws RemoteException, TransactionAbortedException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                return r.queryRoomsPrice(id, location);
            } catch(RemoteException re)
                {onError(i);}
        }
        return -1;
    }

    /* Reserve a seat on this flight*/
    public synchronized boolean reserveFlight(int id, int customer, int flightNumber)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.reserveFlight(id, customer, flightNumber);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    /* reserve a car at this location */
    public synchronized boolean reserveCar(int id, int customer, String location)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.reserveCar(id, customer, location);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    /* reserve a room certain at this location */
    public synchronized boolean reserveRoom(int id, int customer, String location)
    throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.reserveRoom(id, customer, location);
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized void incrementItem(int id, String key, int change)
    throws RemoteException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                r.incrementItem(id, key, change);
            } catch(RemoteException re)
                {onError(i);}
        }
    }

    public synchronized void decrementItem(int id, String key)
    throws RemoteException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                r.decrementItem(id, key);
            } catch(RemoteException re)
                {onError(i);}
        }
    }

    public synchronized int start() throws RemoteException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                r.start();
            } catch(RemoteException re)
                {onError(i);}
        }
        return 0;
    }

    public synchronized void commit(int transactionId) throws RemoteException, TransactionAbortedException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                r.commit(transactionId);
            } catch(RemoteException re)
                {onError(i);}
        }
    }

    public synchronized void abort(int transactionId) throws RemoteException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                r.abort(transactionId);
            } catch(RemoteException re)
                {onError(i);}
        }
    }

    public synchronized void enlist(int trxnId) throws RemoteException
    {
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                r.enlist(trxnId);
            } catch(RemoteException re)
                {onError(i);}
        }
    }

    public synchronized boolean shutdown() throws RemoteException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            ResourceManager r = get(i[0]);
            try {
                toR = r.shutdown();
            } catch(RemoteException re)
                {onError(i);}
        }
        return toR;
    }
}