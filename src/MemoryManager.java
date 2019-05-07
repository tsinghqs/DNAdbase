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
        
    }
    

}
