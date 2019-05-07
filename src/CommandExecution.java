import java.io.File;
import java.io.IOException;


/**
 * Class to execute commands
 * @author tsingh
 *
 */
public class CommandExecution {
    
    HashTable tab;
    MemoryManager mem;
    int hashTableSize;
    /**
     * Constructor for command executioner class
     * @param hashTableSize size of hashtable
     * @param memFile memory file
     */
    public CommandExecution(HashTable hashing, MemoryManager meming)
    {
        tab = hashing;
        mem = meming;
        hashTableSize = 512;
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
        int hash = this.sfold(sequenceId);
        boolean inserted = this.tab.hashValue(insertion, hash);
        if (inserted)
        {
            System.out.println("Inserted");
        }
        
    }
    /**@Override
    public void print() throws IOException {
        LinkedList<Handle> list = 
            this.mem.getList();
        System.out.print("Sequence IDs:");
        System.out.println();
        if (this.numRecords > 0) {
            for (int i = 0; i < this.capacity(); i++) {
                if (this.records[i] != null && 
                    !this.records[i].isTombstone()) {
                    Handle seqId = records[i].getSeqID();
                    String id = mem.getHandleInfo(seqId);
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
    }**/
    
    /**
     * Method to get hash for a String
     * @param s string to be hashed
     * @return the hashed value
     */
    public int sfold(String s) {
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
        return (int)(Math.abs(sum) % this.hashTableSize);
      }


}
