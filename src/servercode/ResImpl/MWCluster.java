package ResImpl;

import ResInterface.*;

import java.util.*;
import java.rmi.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MWCluster extends RMCluster
implements MiddleWare
{
    public void setPrimary() throws RemoteException
    {    // DOESN'T HAPPEN HERE
        ;
    }

    public boolean addToRM(String clientName, int port, String server) throws RemoteException
    {   // DOESN'T HAPPEN HERE
        return true;
    }

    public synchronized void onError(int[] i)
    {
        remove(i[0]);
        if(i[0]-- == 0)
            while (size() > 0)
            {
                try {
                    ((MiddleWare)get(0)).setPrimary();
                    break;
                } catch (RemoteException re)
                {   // well this is awkward
                    remove(0);
                    if(i[0] >= 0)
                        i[0]--;
                }
            }
    }

    public synchronized boolean itinerary(int id, int customer, Vector flightNumbers, String location, boolean car, boolean room)
        throws RemoteException, TransactionAbortedException
    {
        boolean toR = false;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            MiddleWare r = (MiddleWare)get(i[0]);
            try {
                toR = r.itinerary(id, customer, flightNumbers, location, car, room);
            } catch (RemoteException re)
                {onError(i);}
        }
        return toR;
    }

    public synchronized int newCustomer(int id) throws RemoteException, TransactionAbortedException
    {
        int toR = -1;
        int[] i = {0};
        for(; i[0] < size(); i[0]++) {
            MiddleWare r = (MiddleWare)get(i[0]);
            try {
                toR = r.newCustomer(id);
                break;
            } catch (RemoteException re)
                {onError(i);}
        }
        for(; i[0] < size(); i[0]++) {
            MiddleWare r = (MiddleWare)get(i[0]);
            try {
                r.newCustomer(id, toR);
            } catch (RemoteException re)
                {onError(i);}
        }
        return toR;
    }
}