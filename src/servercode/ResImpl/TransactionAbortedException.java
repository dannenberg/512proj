package ResImpl;

/*
    The transaction must abort!
*/

public class TransactionAbortedException extends Exception
{
    private int trxnid;
    public TransactionAbortedException (int trxnid, String msg)
    {
        super("The transaction " + trxnid + " must abort!:" + msg);
        this.trxnid = trxnid;
    }
    
    int getTrxnId()
    {
        return trxnid;
    }
}
