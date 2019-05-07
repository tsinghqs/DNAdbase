import java.io.File;
import java.util.LinkedList;

/**
 * @author vpratha
 * @version 5.7.2019
 * 
 * 
 * 
 */
public class MemoryManager           //handle length is string.length
{
    private File memoryFile;
    private LinkedList<Handle> freelist;
    
    public MemoryManager(File memFile)
    {
        memoryFile = memFile;
        freelist = new LinkedList<Handle>();
    }
    
    /**
     * Stores item, the sequence or sequenceID
     * string, in binary representation to memoryFile,
     * and returns a Handle to that item.
     * @param item The sequence or sequenceID to be stored
     * @return a Handle to that item
     */
    public Handle storeItem(String item)
    {
        int length = item.length();
        
        // to do: figure out offset from freelist, print binary rep of item to memfile
        int offset = 0;
        
        
        Handle itemHandle = new Handle(offset, length);
        return itemHandle;
    }
    
    /**
     * Converts item, the sequence or sequenceID
     * string, to a byte[]. Each byte in the byte[]
     * will represent 4 characters in item, with
     * each character having a 2-bit representation:
     * A = 00
     * C = 01
     * G = 10
     * T = 11.
     * If the length of item is not divisible by 4,
     * then the remaining bits of the last byte in the 
     * byte[] will be 0s.
     * @param item The sequence or sequenceID to be stored
     * @return The byte[] representation of item
     */
    private byte[] getBinaryRep(String item)
    {
        int numBytes = item.length() / 4;
        if (item.length() % 4 != 0)
        {
            numBytes++;
        }
        
        byte[] rep = new byte[numBytes];
        
        char[] itemChars = item.toCharArray();
        for (int i = 0; i < itemChars.length; i++)
        {
            
        }
        
    }
}
