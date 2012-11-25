// -------------------------------
// adapted from Kevin T. Manley
// CSE 593
// -------------------------------
package ResImpl;

import java.io.*;

// Resource manager data item
public abstract class RMItem implements Serializable
{

    RMItem() {
			super();
    }

    public abstract String getKey();
}
