/**
 * Class to create our hashtable
 * @author tsingh
 *
 */
public class HashTable {

    Record[] recs;
    int size;
    
    /**
     * Hashtable constructor
     * @param size size of hashtable
     */
    public HashTable(int size)
    {
        recs = new Record[size];
        this.size = size;
    }
    
    /**
     * Method to get the records in a HashTable
     * @return Record[] of records
     */
    public Record[] getRecords()
    {
        return recs;
    }
    

    /**
     * Method to add a record to the array
     * 
     * @param hashing
     *            The record we are adding
     * @param sfold
     *            the computed hash function value
     */
    public boolean hashValue(Record hashing, int sfold) {
        if (!this.hasKey(hashing)) {
            if (this.recs[sfold] == null) {
                recs[sfold] = hashing;
                return true;
            }
            else {
                for (int i = (sfold - sfold % 32); i < 32; i++)
                {
                    if (this.recs[i] == null)
                    {
                        recs[i] = hashing;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    
    /**
     * Method to see if key is already in the HashTable
     * 
     * @param compare
     *            Record we are comparing to
     * @return boolean if they key is in the table or not
     */
    public boolean hasKey(Record compare) {
        for (int i = 0; i < this.size; i++) {
            if (recs[i] != null) {
                if (recs[i].getSeqIDHandle().getOffset() == compare
                    .getSeqIDHandle().getOffset()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
}
