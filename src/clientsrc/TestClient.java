import java.util.ArrayList;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class TestClient extends Client
{
    private static String file;
    public static void handleArgs(String args[])
    {
        server = "willy.cs.mcgill.ca";
        port = 9988;
        if (args.length == 3)
        {
            port = Integer.parseInt(args[2]);
            server = args[1];
            file = args[0];
        }
        else if (args.length == 2)
        {
            server = args[1];
            file = args[0];
        }
        else if (args.length == 1)
        {
            file = args[0];
        }
        else if (args.length != 0)
        {
            System.out.println ("Usage: java client [testfile] [rmihost] [port]");
            System.exit(1); 
        }
    }

    public static void main(String args[])
    {
        obj = new TestClient();
        ArrayList<Integer> tIds = new ArrayList();
        Pattern regex = Pattern.compile(",\\s*(\\d+)");
        Matcher matcher;
        int start = -1;
        String command = null;
        silent = true;
        handleArgs(args);
        connect();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while ((command = in.readLine()) != null)
            {
                try{
                    matcher = regex.matcher(command);
                    command = matcher.replaceFirst("," + tIds.get(Integer.parseInt(matcher.group(1))));
                }
                catch (IllegalStateException e) {}
                start = handleInput(command, true);
                if(start != -1)
                    tIds.add(start);
            }
        }
        catch (IOException e) {
            System.out.println("since this is a test function only we will use I am not going to do anything with caught exceptions aside from print them:" + e.toString());
            e.printStackTrace();
        }
    }
}
