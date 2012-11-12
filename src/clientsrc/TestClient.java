import java.util.ArrayList;

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
        ArrayList<Integer> tIds = new ArrayList();
        Pattern regex = Pattern.compile(",\\s*(\\d+)");
        Matcher matcher;
        int start = -1;
        silent = true;
        handleArgs(args);
        BufferedReader in = new BufferedReader(new FileReader(file));
        while ((command = in.readLine()) != null)
        {
            matcher = regex.matcher(command);
            command = matcher.replaceFirst("," + tIds.get(Integer.parseInt(matcher.group(1))));
            start = handleInput(command);
            if(start != -1)
                tIds.add(start);
        }
    }
}