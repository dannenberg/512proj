package ResImpl;

class Shutdown extends Thread
{
    public void run()
    {
        Thread.sleep(2000);
        System.exit();
    }
}