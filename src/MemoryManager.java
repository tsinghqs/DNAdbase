import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

/**
 * @author vpratha
 * @version 5.7.2019
 * 
 * 
 * 
 */
public class MemoryManager          
{
    private RandomAccessFile memoryFile;
    /**
     * Offset in bytes for EOF in memoryFile.
     */
    private int eof;
    private LinkedList<Handle> freelist;
    
    public MemoryManager(RandomAccessFile memFile)
    {
        memoryFile = memFile;
        eof = 0;
        freelist = new LinkedList<Handle>();
    }
    
    /**
     * Stores item, the sequence or sequenceID
     * string, in binary representation to memoryFile,
     * and returns a Handle to that item.
     * @param item the sequence or sequenceID to be stored
     * @return a Handle to that item
     * @throws IOException 
     */
    public Handle storeItem(String item) throws IOException
    {
        int length = item.length();
        
        byte[] rep = getBinaryRep(item);
        int offset = firstFit(item);
        int numBytes = rep.length;
        
        memoryFile.write(rep, offset, numBytes);
        
        Handle itemHandle = new Handle(offset, length);
        return itemHandle;
    }
    
    /**
     * Gets the offset of the first free block
     * in freelist that can accommodate item;
     * updates that block's offset and length.
     * If there are no free blocks that qualify, 
     * returns eof.
     * @param item the sequence or sequenceID to be stored
     * @return the first valid offset where item can be stored
     *         in memoryFile
     */
    public int firstFit(String item)
    {
        if (freelist.size() == 0)
        {
            return eof;
        }
        
        int offset = eof;
        
        for (Handle freeBlock : freelist)
        {
            if (freeBlock.getLength() >= item.length())
            {
                offset = freeBlock.getOffset();
                freeBlock.setOffset(offset + getNumBytes(item));
                freeBlock.setLength(freeBlock.getLength() - item.length());
                return offset;
            }
        }
        
        return offset;
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
     * @param item the sequence or sequenceID to be stored
     * @return The byte[] representation of item
     */
    public byte[] getBinaryRep(String item)
    {   
        char[] itemChars = item.toCharArray();
        
        int numBytes = itemChars.length / 4;
        int fillerLetters = 0;
        if (item.length() % 4 != 0)
        {
            numBytes++;
            fillerLetters += 4 - (item.length() % 4);
        }
        
        //System.out.println("numBytes: " + numBytes);
        
        byte[] rep = new byte[numBytes];
        
        int currByte = 0;
        int remainingLettersInCurrByte = 4;
        int canvas = 0;
        for (int i = 0; i < itemChars.length + fillerLetters; i++)
        {     
            int letterBits = 0;
            
            if (i < itemChars.length)
            {
                char letter = itemChars[i];
                
                
                if (letter == 'C')
                {
                    letterBits = 1;
                }
                else if (letter == 'G')
                {
                    letterBits = 2;
                }
                else if (letter == 'T')
                {
                    letterBits = 3;
                }
            }
            
            //System.out.println("letterBits: " + letterBits);
            
            //canvas = canvas | letterBits;
            //canvas = canvas << (2 * (remainingLettersInCurrByte - 1));
            int shiftAmt = 2 * (remainingLettersInCurrByte - 1);
            canvas += (letterBits * (int) Math.pow(2, shiftAmt));
            
            //System.out.println("canvas: " + canvas);
            
            remainingLettersInCurrByte--;
            
            if (remainingLettersInCurrByte == 0)
            {
                rep[currByte] = (byte)(canvas); //& 0xFF);
                currByte++;
                canvas = 0;
                remainingLettersInCurrByte = 4;
            } 
        }
        
        return rep;
    }
    
    /**
     * Returns the number of bytes required
     * to store item in memoryFile.
     * @param item the sequence or sequenceID to be stored
     * @return the number of bytes required
     *         to store item in memoryFile
     */
    public int getNumBytes(String item)
    {
        char[] itemChars = item.toCharArray();
        
        int numBytes = itemChars.length / 4;
        if (item.length() % 4 != 0)
        {
            numBytes++;
        }
        
        return numBytes;
    }
    
    /**
     * Gets string from a handle offset
     * 
     * @param binRep input handle
     * @return String representation of Handle bytes
     * @throws IOException If can't seek
     */
    public String getHandleString(Handle binRep) throws IOException {
        StringBuilder strn = new StringBuilder();
        int num = 0;
        int off = binRep.getOffset();
        int len = binRep.getLength();
        if (len % 4 == 0) {
            num = 1;
        }
        num += len / 4;
        byte[] bytes = new byte[num];
        memoryFile.seek(off);
        memoryFile.read(bytes);
        int appCount = 0;
        for (int i = 0; i < num; i++) {
            String s1 = String.format("%8s", Integer.toBinaryString(bytes[i]
                & 0xFF)).replace(' ', '0');
            for (int j = 0; j < 8; j++) {
                String check = s1.substring(j, j + 1);
                if (check.equals("00")) {
                    strn.append("A");
                }
                else if (check.equals("01")) {
                    strn.append("C");
                }
                else if (check.equals("10")) {
                    strn.append("G");
                }
                else if (check.equals("11")) {
                    strn.append("T");
                }
                appCount++;
                if (appCount == len) {
                    break;
                }
            }
            if (appCount == len) {
                break;
            }
        }
        return strn.toString();

    }
}
