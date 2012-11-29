import java.rmi.*;
import ResInterface.*;
import ResImpl.MWCluster;
import ResImpl.TransactionAbortedException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.*;
import java.io.*;


public class Client
{
    protected static String message = "blank";
    protected static MWCluster rm = new MWCluster();
    protected static HashSet<Integer> trxns = new HashSet();

    protected static Client obj = null;
    protected static boolean silent;

    public static void println(String toR)
    {
        if(!silent)
            System.out.println(toR);
    }

    public static void handleArgs(String args[])
    {
        String server;
        int port;
        for(int i = 0; i < args.length / 2; i++)
        {
            server = args[i * 2];
            port = Integer.parseInt(args[i * 2 + 1]);
            connect(server, port);
        }
    }

    public static void connect(String server, int port)
    {
        try 
        {
            // get a reference to the rmiregistry
            Registry registry = LocateRegistry.getRegistry(server, port);
            MiddleWare mw = null;
            // get the proxy and the remote reference by rmiregistry lookup
            mw = (MiddleWare) registry.lookup("Group13Middleware");
            if(mw!=null)
            {
                println("Successful");
                println("Connected to Middleware");
                rm.add(mw);
            }
            else
            {
                println("Unsuccessful middleware");
            }
            // make call on remote method
        }
        catch (Exception e) 
        {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        /*
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        */
    }

    public static void main(String args[])
    {
        obj = new Client();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        silent = false;

        handleArgs(args);

        println("\n\n\tClient Interface");
        println("Type \"help\" for list of supported commands");
        while(true){
            System.out.print("\n>");
            try{
                //read the next command
                command =stdin.readLine();
            }
            catch (IOException io){
                println("Unable to read from standard in");
                System.exit(1);
            }
            //remove heading and trailing white space
            handleInput(command, false);
        }//end of while(true)
    }

    public static int handleInput(String command, boolean silent)
    {
        Vector arguments  = new Vector();
        int Id = -1;
        int Cid;
        int flightNum;
        int flightPrice;
        int flightSeats;
        boolean room;
        boolean car;
        int price;
        int numRooms;
        int numCars;
        String location;
        int choice;

        command=command.trim();
        arguments=obj.parse(command);

        if (arguments.isEmpty())
            return -2;

        choice = obj.findChoice((String)arguments.elementAt(0));
        if(choice != 1 && choice != 23 && arguments.size() >= 2)
        {
            Id = Integer.parseInt((String)arguments.elementAt(1));
            if(!trxns.contains(Id))
            {
                println("Hey, you haven't opened that transaction!");
                return -2;
            }
        }
        //decide which of the commands this was
        switch(choice) {
            case 1: //help section
                if(arguments.size()==1)   //command was "help"
                    obj.listCommands();
                else if (arguments.size()==2)  //command was "help <commandname>"
                    obj.listSpecific((String)arguments.elementAt(1));
                else  //wrong use of help command
                    System.out.println("Improper use of help command. Type help or help, <commandname>");
                break;

            case 2:  //new flight
                if(arguments.size()!=5){
                    obj.wrongNumber();
                    break;
                }
                println("Adding a new Flight using id: "+arguments.elementAt(1));
                println("Flight number: "+arguments.elementAt(2));
                println("Add Flight Seats: "+arguments.elementAt(3));
                println("Set Flight Price: "+arguments.elementAt(4));

                try{
                    
                    flightNum = obj.getInt(arguments.elementAt(2));
                    flightSeats = obj.getInt(arguments.elementAt(3));
                    flightPrice = obj.getInt(arguments.elementAt(4));
                    if(rm.addFlight(Id,flightNum,flightSeats,flightPrice))
                        println("Flight added");
                    else
                        println("Flight could not be added");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 3:  //new Car
                if(arguments.size()!=5){
                    obj.wrongNumber();
                    break;
                }
                println("Adding a new Car using id: "+arguments.elementAt(1));
                println("Car Location: "+arguments.elementAt(2));
                println("Add Number of Cars: "+arguments.elementAt(3));
                println("Set Price: "+arguments.elementAt(4));
                try{
                    
                    location = obj.getString(arguments.elementAt(2));
                    numCars = obj.getInt(arguments.elementAt(3));
                    price = obj.getInt(arguments.elementAt(4));
                    if(rm.addCars(Id,location,numCars,price))
                        println("Cars added");
                    else
                        println("Cars could not be added");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 4:  //new Room
                if(arguments.size()!=5){
                    obj.wrongNumber();
                    break;
                }
                println("Adding a new Room using id: "+arguments.elementAt(1));
                println("Room Location: "+arguments.elementAt(2));
                println("Add Number of Rooms: "+arguments.elementAt(3));
                println("Set Price: "+arguments.elementAt(4));
                try{
                    
                    location = obj.getString(arguments.elementAt(2));
                    numRooms = obj.getInt(arguments.elementAt(3));
                    price = obj.getInt(arguments.elementAt(4));
                    if(rm.addRooms(Id,location,numRooms,price))
                        println("Rooms added");
                    else
                        println("Rooms could not be added");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 5:  //new Customer
                if(arguments.size()!=2){
                    obj.wrongNumber();
                    break;
                }
                println("Adding a new Customer using id:"+arguments.elementAt(1));
                try{
                    
                    int customer=rm.newCustomer(Id);
                    println("new customer id:"+customer);
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 6: //delete Flight
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Deleting a flight using id: "+arguments.elementAt(1));
                println("Flight Number: "+arguments.elementAt(2));
                try{
                    
                    flightNum = obj.getInt(arguments.elementAt(2));
                    if(rm.deleteFlight(Id,flightNum))
                        println("Flight Deleted");
                    else
                        println("Flight could not be deleted");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 7: //delete Car
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Deleting the cars from a particular location  using id: "+arguments.elementAt(1));
                println("Car Location: "+arguments.elementAt(2));
                try{
                    
                    location = obj.getString(arguments.elementAt(2));

                    if(rm.deleteCars(Id,location))
                        println("Cars Deleted");
                    else
                        println("Cars could not be deleted");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 8: //delete Room
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Deleting all rooms from a particular location  using id: "+arguments.elementAt(1));
                println("Room Location: "+arguments.elementAt(2));
                try{
                    
                    location = obj.getString(arguments.elementAt(2));
                    if(rm.deleteRooms(Id,location))
                        println("Rooms Deleted");
                    else
                        println("Rooms could not be deleted");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 9: //delete Customer
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Deleting a customer from the database using id: "+arguments.elementAt(1));
                println("Customer id: "+arguments.elementAt(2));
                try{
                    
                    int customer = obj.getInt(arguments.elementAt(2));
                    if(rm.deleteCustomer(Id,customer))
                        println("Customer Deleted");
                    else
                        println("Customer could not be deleted");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 10: //querying a flight
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Querying a flight using id: "+arguments.elementAt(1));
                println("Flight number: "+arguments.elementAt(2));
                try{
                    
                    flightNum = obj.getInt(arguments.elementAt(2));
                    int seats=rm.queryFlight(Id,flightNum);
                    println("Number of seats available:"+seats);
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 11: //querying a Car Location
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Querying a car location using id: "+arguments.elementAt(1));
                println("Car location: "+arguments.elementAt(2));
                try{
                    
                    location = obj.getString(arguments.elementAt(2));
                    numCars=rm.queryCars(Id,location);
                    println("number of Cars at this location:"+numCars);
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 12: //querying a Room location
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Querying a room location using id: "+arguments.elementAt(1));
                println("Room location: "+arguments.elementAt(2));
                try{
                    
                    location = obj.getString(arguments.elementAt(2));
                    numRooms=rm.queryRooms(Id,location);
                    println("number of Rooms at this location:"+numRooms);
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 13: //querying Customer Information
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Querying Customer information using id: "+arguments.elementAt(1));
                println("Customer id: "+arguments.elementAt(2));
                try{
                    
                    int customer = obj.getInt(arguments.elementAt(2));
                    String bill=rm.queryCustomerInfo(Id,customer);
                    println("Customer info:"+bill);
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;             

            case 14: //querying a flight Price
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Querying a flight Price using id: "+arguments.elementAt(1));
                println("Flight number: "+arguments.elementAt(2));
                try{
                    
                    flightNum = obj.getInt(arguments.elementAt(2));
                    price=rm.queryFlightPrice(Id,flightNum);
                    println("Price of a seat:"+price);
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 15: //querying a Car Price
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Querying a car price using id: "+arguments.elementAt(1));
                println("Car location: "+arguments.elementAt(2));
                try{
                    
                    location = obj.getString(arguments.elementAt(2));
                    price=rm.queryCarsPrice(Id,location);
                    println("Price of a car at this location:"+price);
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }          
                return -1;

            case 16: //querying a Room price
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Querying a room price using id: "+arguments.elementAt(1));
                println("Room Location: "+arguments.elementAt(2));
                try{
                    
                    location = obj.getString(arguments.elementAt(2));
                    price=rm.queryRoomsPrice(Id,location);
                    println("Price of Rooms at this location:"+price);
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 17:  //reserve a flight
                if(arguments.size()!=4){
                    obj.wrongNumber();
                    break;
                }
                println("Reserving a seat on a flight using id: "+arguments.elementAt(1));
                println("Customer id: "+arguments.elementAt(2));
                println("Flight number: "+arguments.elementAt(3));
                try{
                    
                    int customer = obj.getInt(arguments.elementAt(2));
                    flightNum = obj.getInt(arguments.elementAt(3));
                    if(rm.reserveFlight(Id,customer,flightNum))
                        println("Flight Reserved");
                    else
                        println("Flight could not be reserved.");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 18:  //reserve a car
                if(arguments.size()!=4){
                    obj.wrongNumber();
                    break;
                }
                println("Reserving a car at a location using id: "+arguments.elementAt(1));
                println("Customer id: "+arguments.elementAt(2));
                println("Location: "+arguments.elementAt(3));

                try{
                    
                    int customer = obj.getInt(arguments.elementAt(2));
                    location = obj.getString(arguments.elementAt(3));

                    if(rm.reserveCar(Id,customer,location))
                        println("Car Reserved");
                    else
                        println("Car could not be reserved.");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 19:  //reserve a room
                if(arguments.size()!=4){
                    obj.wrongNumber();
                    break;
                }
                println("Reserving a room at a location using id: "+arguments.elementAt(1));
                println("Customer id: "+arguments.elementAt(2));
                println("Location: "+arguments.elementAt(3));
                try{
                    
                    int customer = obj.getInt(arguments.elementAt(2));
                    location = obj.getString(arguments.elementAt(3));

                    if(rm.reserveRoom(Id,customer,location))
                        println("Room Reserved");
                    else
                        println("Room could not be reserved.");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 20:  //reserve an Itinerary
                if(arguments.size()<7){
                    obj.wrongNumber();
                    break;
                }
                println("Reserving an Itinerary using id:"+arguments.elementAt(1));
                println("Customer id:"+arguments.elementAt(2));
                for(int i=0;i<arguments.size()-6;i++)
                    println("Flight number"+arguments.elementAt(3+i));
                println("Location for Car/Room booking:"+arguments.elementAt(arguments.size()-3));
                println("Car to book?:"+arguments.elementAt(arguments.size()-2));
                println("Room to book?:"+arguments.elementAt(arguments.size()-1));
                try{
                    
                    int customer = obj.getInt(arguments.elementAt(2));
                    Vector flightNumbers = new Vector();
                    for(int i=0;i<arguments.size()-6;i++)
                        flightNumbers.addElement(arguments.elementAt(3+i));
                    location = obj.getString(arguments.elementAt(arguments.size()-3));
                    car = obj.getBoolean(arguments.elementAt(arguments.size()-2));
                    room = obj.getBoolean(arguments.elementAt(arguments.size()-1));

                    if(rm.itinerary(Id,customer,flightNumbers,location,car,room))
                        println("Itinerary Reserved");
                    else
                        println("Itinerary could not be reserved.");
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;

            case 21:  //quit the client
                if(arguments.size()!=1){
                    obj.wrongNumber();
                    break;
                }
                println("Quitting client.");
                System.exit(1);


            case 22:  //new Customer given id
                if(arguments.size()!=3){
                    obj.wrongNumber();
                    break;
                }
                println("Adding a new Customer using id:"+arguments.elementAt(1) + " and cid " +arguments.elementAt(2));
                try{
                    
                    Cid = obj.getInt(arguments.elementAt(2));
                    boolean customer=rm.newCustomer(Id,Cid);
                    println("new customer id:"+Cid);
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return -1;


            case 23: // start 
                try {
                    Id = rm.start();
                }
                catch (Exception e) {
                    println("EXCEPTION:");
                    println(e.getMessage());
                    e.printStackTrace();
                }
                println("started transaction #" + Id);
                trxns.add(Id);
                return Id;

            case 24: // commit
                if(arguments.size()!=2){
                    obj.wrongNumber();
                    break;
                }
                println("commiting transaction " + arguments.elementAt(1));
                try {
                    rm.commit(Id);
                    trxns.remove(Id);
                }
                catch(TransactionAbortedException t) {
                    System.out.println("Transaction Aborted: " + t.getMessage());
                    trxns.remove(t.getTrxnId());
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                break;


            case 25: // abort
                if(arguments.size()!=2){
                    obj.wrongNumber();
                    break;
                }
                println("aborting transaction " + arguments.elementAt(1));
                try {
                    rm.abort(Id);
                    trxns.remove(Id);
                }
                catch(Exception e){
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                break;

            case 26:
                System.out.println("shutting down network");
                try {
                    rm.shutdown();
                    Thread.sleep(2000);
                }
                catch (Exception e) {
                    System.out.println("EXCEPTION:");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }

                System.out.println("network shut down");
                break;

            default:
                println("The interface does not support this command.");
                break;
        }//end of switch
        return -2;
    }

    public Vector parse(String command)
    {
        Vector arguments = new Vector();
        StringTokenizer tokenizer = new StringTokenizer(command,",");
        String argument ="";
        while (tokenizer.hasMoreTokens())
        {
            argument = tokenizer.nextToken();
            argument = argument.trim();
            arguments.add(argument);
        }
        return arguments;
    }
    public int findChoice(String argument)
    {
        if (argument.compareToIgnoreCase("help")==0)
            return 1;
        else if(argument.compareToIgnoreCase("newflight")==0)
            return 2;
        else if(argument.compareToIgnoreCase("newcar")==0)
            return 3;
        else if(argument.compareToIgnoreCase("newroom")==0)
            return 4;
        else if(argument.compareToIgnoreCase("newcustomer")==0)
            return 5;
        else if(argument.compareToIgnoreCase("deleteflight")==0)
            return 6;
        else if(argument.compareToIgnoreCase("deletecar")==0)
            return 7;
        else if(argument.compareToIgnoreCase("deleteroom")==0)
            return 8;
        else if(argument.compareToIgnoreCase("deletecustomer")==0)
            return 9;
        else if(argument.compareToIgnoreCase("queryflight")==0)
            return 10;
        else if(argument.compareToIgnoreCase("querycar")==0)
            return 11;
        else if(argument.compareToIgnoreCase("queryroom")==0)
            return 12;
        else if(argument.compareToIgnoreCase("querycustomer")==0)
            return 13;
        else if(argument.compareToIgnoreCase("queryflightprice")==0)
            return 14;
        else if(argument.compareToIgnoreCase("querycarprice")==0)
            return 15;
        else if(argument.compareToIgnoreCase("queryroomprice")==0)
            return 16;
        else if(argument.compareToIgnoreCase("reserveflight")==0)
            return 17;
        else if(argument.compareToIgnoreCase("reservecar")==0)
            return 18;
        else if(argument.compareToIgnoreCase("reserveroom")==0)
            return 19;
        else if(argument.compareToIgnoreCase("itinerary")==0)
            return 20;
        else if (argument.compareToIgnoreCase("quit")==0)
            return 21;
        else if (argument.compareToIgnoreCase("newcustomerid")==0)
            return 22;
        else if (argument.compareToIgnoreCase("start")==0)
            return 23;
        else if (argument.compareToIgnoreCase("commit")==0)
            return 24;
        else if (argument.compareToIgnoreCase("abort")==0)
            return 25;
        else if (argument.compareToIgnoreCase("shutdown")==0)
            return 26;
        else
            return 666;

    }

    public void listCommands()
    {
        println("\nWelcome to the client interface provided to test your project.");
        println("Commands accepted by the interface are:");
        println("help");
        println("newflight\nnewcar\nnewroom\nnewcustomer\nnewcusomterid\ndeleteflight\ndeletecar\ndeleteroom");
        println("deletecustomer\nqueryflight\nquerycar\nqueryroom\nquerycustomer");
        println("queryflightprice\nquerycarprice\nqueryroomprice");
        println("reserveflight\nreservecar\nreserveroom\nitinerary");
        println("quit");
        println("\ntype help, <commandname> for detailed info(NOTE the use of comma).");
    }


    public void listSpecific(String command)
    {
        System.out.print("Help on: ");
        switch(findChoice(command))
        {
            case 1:
                println("Help");
                println("\nTyping help on the prompt gives a list of all the commands available.");
                println("Typing help, <commandname> gives details on how to use the particular command.");
                break;

            case 2:  //new flight
                println("Adding a new Flight.");
                println("Purpose:");
                println("\tAdd information about a new flight.");
                println("\nUsage:");
                println("\tnewflight,<id>,<flightnumber>,<flightSeats>,<flightprice>");
                break;

            case 3:  //new Car
                println("Adding a new Car.");
                println("Purpose:");
                println("\tAdd information about a new car location.");
                println("\nUsage:");
                println("\tnewcar,<id>,<location>,<numberofcars>,<pricepercar>");
                break;

            case 4:  //new Room
                println("Adding a new Room.");
                println("Purpose:");
                println("\tAdd information about a new room location.");
                println("\nUsage:");
                println("\tnewroom,<id>,<location>,<numberofrooms>,<priceperroom>");
                break;

            case 5:  //new Customer
                println("Adding a new Customer.");
                println("Purpose:");
                println("\tGet the system to provide a new customer id. (same as adding a new customer)");
                println("\nUsage:");
                println("\tnewcustomer,<id>");
                break;


            case 6: //delete Flight
                println("Deleting a flight");
                println("Purpose:");
                println("\tDelete a flight's information.");
                println("\nUsage:");
                println("\tdeleteflight,<id>,<flightnumber>");
                break;

            case 7: //delete Car
                println("Deleting a Car");
                println("Purpose:");
                println("\tDelete all cars from a location.");
                println("\nUsage:");
                println("\tdeletecar,<id>,<location>,<numCars>");
                break;

            case 8: //delete Room
                println("Deleting a Room");
                println("\nPurpose:");
                println("\tDelete all rooms from a location.");
                println("Usage:");
                println("\tdeleteroom,<id>,<location>,<numRooms>");
                break;

            case 9: //delete Customer
                println("Deleting a Customer");
                println("Purpose:");
                println("\tRemove a customer from the database.");
                println("\nUsage:");
                println("\tdeletecustomer,<id>,<customerid>");
                break;

            case 10: //querying a flight
                println("Querying flight.");
                println("Purpose:");
                println("\tObtain Seat information about a certain flight.");
                println("\nUsage:");
                println("\tqueryflight,<id>,<flightnumber>");
                break;

            case 11: //querying a Car Location
                println("Querying a Car location.");
                println("Purpose:");
                println("\tObtain number of cars at a certain car location.");
                println("\nUsage:");
                println("\tquerycar,<id>,<location>");       
                break;

            case 12: //querying a Room location
                println("Querying a Room Location.");
                println("Purpose:");
                println("\tObtain number of rooms at a certain room location.");
                println("\nUsage:");
                println("\tqueryroom,<id>,<location>");      
                break;

            case 13: //querying Customer Information
                println("Querying Customer Information.");
                println("Purpose:");
                println("\tObtain information about a customer.");
                println("\nUsage:");
                println("\tquerycustomer,<id>,<customerid>");
                break;             

            case 14: //querying a flight for price 
                println("Querying flight.");
                println("Purpose:");
                println("\tObtain price information about a certain flight.");
                println("\nUsage:");
                println("\tqueryflightprice,<id>,<flightnumber>");
                break;

            case 15: //querying a Car Location for price
                println("Querying a Car location.");
                println("Purpose:");
                println("\tObtain price information about a certain car location.");
                println("\nUsage:");
                println("\tquerycarprice,<id>,<location>");      
                break;

            case 16: //querying a Room location for price
                println("Querying a Room Location.");
                println("Purpose:");
                println("\tObtain price information about a certain room location.");
                println("\nUsage:");
                println("\tqueryroomprice,<id>,<location>");     
                break;

            case 17:  //reserve a flight
                println("Reserving a flight.");
                println("Purpose:");
                println("\tReserve a flight for a customer.");
                println("\nUsage:");
                println("\treserveflight,<id>,<customerid>,<flightnumber>");
                break;

            case 18:  //reserve a car
                println("Reserving a Car.");
                println("Purpose:");
                println("\tReserve a given number of cars for a customer at a particular location.");
                println("\nUsage:");
                println("\treservecar,<id>,<customerid>,<location>,<nummberofCars>");
                break;

            case 19:  //reserve a room
                println("Reserving a Room.");
                println("Purpose:");
                println("\tReserve a given number of rooms for a customer at a particular location.");
                println("\nUsage:");
                println("\treserveroom,<id>,<customerid>,<location>,<nummberofRooms>");
                break;

            case 20:  //reserve an Itinerary
                println("Reserving an Itinerary.");
                println("Purpose:");
                println("\tBook one or more flights.Also book zero or more cars/rooms at a location.");
                println("\nUsage:");
                println("\titinerary,<id>,<customerid>,<flightnumber1>....<flightnumberN>,<LocationToBookCarsOrRooms>,<NumberOfCars>,<NumberOfRoom>");
                break;


            case 21:  //quit the client
                println("Quitting client.");
                println("Purpose:");
                println("\tExit the client application.");
                println("\nUsage:");
                println("\tquit");
                break;

            case 22:  //new customer with id
                println("Create new customer providing an id");
                println("Purpose:");
                println("\tCreates a new customer with the id provided");
                println("\nUsage:");
                println("\tnewcustomerid, <id>, <customerid>");
                break;

            case 23:  // start trxn
                println("Start a new transaction.");
                println("Purpose:");
                println("\tStarts a transaction and gives the transaction number.");
                println("\nUsage:");
                println("\tstart");

            case 24:  // commit
                println("Commit a transaction.");
                println("Purpose:");
                println("\tCommits a transaction.");
                println("\nUsage:");
                println("\tcommit <trxnId>");

            case 25:  // abort
                println("Abort a transaction.");
                println("Purpose:");
                println("\tAborts a transaction.");
                println("\nUsage:");
                println("\tabort <trxnId>");

            default:
                println(command);
                println("The interface does not support this command.");
                break;
        }
    }

    public void wrongNumber() {
        System.out.println("The number of arguments provided in this command are wrong.");
        System.out.println("Type help, <commandname> to check usage of this command.");
    }



    public int getInt(Object temp) throws Exception {
        try {
            return (new Integer((String)temp)).intValue();
        }
        catch(Exception e) {
            throw e;
        }
    }

    public boolean getBoolean(Object temp) throws Exception {
        try {
            return (new Boolean((String)temp)).booleanValue();
        }
        catch(Exception e) {
            throw e;
        }
    }

    public String getString(Object temp) throws Exception {
        try {   
            return (String)temp;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
