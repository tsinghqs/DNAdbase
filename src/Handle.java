/**
 * Class to store offset and length in a Handle object
 * @author tsingh
 *
 */
public class Handle {

    int offset;
    int length;
    int bytes; 
    /**
     * Handle class constructor
     * @param offset offset to be stored
     * @param length length to be stored
     */
    public Handle(int offset, int length)
    {
        this.offset = offset;
        this.length = length;
        this.bytes = getNumBytes(this.length);
    }
    
    /**
     * Method to get offset
     * @return offset the offset of Handle
     */
    public int getOffset()
    {
        return this.offset;
    }
    
    /**
     * Method to get length
     * @return length of handle
     */
    public int getLength()
    {
        return this.length;
    }
    
    /**
     * Method to get bytes
     * @return num bytes of handle
     */
    public int getBytes()
    {
        return this.bytes;
    }
    
    
    /**
     * Method to set a new offset
     * @param newOffset
     */
    public void setOffset(int newOffset)
    {
        this.offset = newOffset;
    }
    
    /**
     * Method to set 
     * @param newLen New length to be set
     */
    public void setLength(int newLen)
    {
        this.length = newLen;
    }
    
    /**
     * Method to set 
     * @param newBytes New bytes to be set
     */
    public void setBytes(int newBytes)
    {
        this.bytes = newBytes;
    }
    
    public int getNumBytes(int len)
    {        
        int numBytes = len / 4;
        if (len % 4 != 0)
        {
            numBytes++;
        }
        return numBytes;
    }
}
