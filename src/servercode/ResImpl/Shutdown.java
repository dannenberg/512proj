package ResImpl;

class Shutdown extends Thread
{
    public void run()
    {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.print("we failed to shutdown... how can that be");
        }
        System.exit(0);
    }
}
