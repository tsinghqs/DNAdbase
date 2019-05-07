import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


/**
 * Class to execute commands
 * @author tsingh
 *
 */
public class CommandExecution {
    
    HashTable tab;
    MemoryManager mem;
    int hashTableSize;
    int numRecords = 0;
    /**
     * Constructor for command executioner class
     * @param hashTableSize size of hashtable
     * @param memFile memory file
     */
    public CommandExecution(HashTable hashing, MemoryManager meming)
    {
        tab = hashing;
        mem = meming;
        hashTableSize = tab.getHashSize();
    }
    
    /**
     * Method to insert a sequence and sequence id
     * @param sequenceId sequence id
     * @param sequence sequence
     * @throws IOException if there's IO
     */
    public void insert(String sequenceId, String sequence) throws IOException
    {
        Record insertion = new Record(mem.storeItem(sequenceId), mem.storeItem(sequence));
        int hash = (int)this.sfold(sequenceId);
        System.out.println("Hash: "+ hash);
        boolean inserted = this.tab.hashValue(insertion, hash);
        if (inserted)
        {
            numRecords++;
        }
        
    }
    
    /**
     * Search method
     * @param id the string we are searching for
     * @throws IOException io exception
     */
    public void search(String id) throws IOException
    {
        boolean found = this.tab.hasStringID(id);
        if (found){
            System.out.println("Sequence found: "+ id);
        }
        else {
            System.out.println("Sequence " + id+ " not found");
        }
    }
    
    /**
     * Method to remove a hash from the hashtable
     * @param id String to remove
     * @throws IOException IOexception
     */
    public void remove(String id) throws IOException
    {
        boolean found = this.tab.hasStringID(id);
        if (found){
            //remove vikram 
           Record rem = this.tab.removeHash(id);
           
           System.out.println("Sequence removed "+ id+ ":\n"+ this.mem.getHandleString(rem.getSeqHandle()));
            
        }
    }
    
    /**
     * Method to print the Values in our database
     * @throws IOException io
     */
    public void print() throws IOException {
        LinkedList<Handle> list = 
            this.mem.getFreelist();
        System.out.print("Sequence IDs:");
        System.out.println();
        Record[] recs = this.tab.getRecords();
        if (this.numRecords > 0) {
            for (int i = 0; i < this.hashTableSize; i++) {
                if (recs[i] != null && !recs[i].isTombstone()) {
                    Handle seqId = recs[i].getSeqIDHandle();
                    String id = this.mem.getHandleString(seqId);
                    System.out.println(id +
                        ": hash slot [" + i + "]");
                }
            }
        }
        System.out.print("Free Block List:");
        if (list.size() == 0) {
            System.out.println(" none");
        }
        else {
            System.out.println();
            for (int i = 0; i < list.size(); i++) {
                Handle hand = list.get(i);
                System.out.printf("[Block %d] Starting Byte" + 
                    " Location: %d, Size %d bytes\n", 
                    i + 1, hand.getOffset(), hand.getLength());
            }
        }
    }
    
    /**
     * Method to get hash for a String
     * @param s string to be hashed
     * @return the hashed value
     */
    public long sfold(String s) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
          char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
          long mult = 1;
          for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
          }
        }

        char c[] = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
          sum += c[k] * mult;
          mult *= 256;
        }

        sum = (sum * sum) >> 8;
        return(Math.abs(sum) % this.hashTableSize);
      }


}
