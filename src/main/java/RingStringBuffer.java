import java.util.Arrays;

//import javax.annotation.concurrent.ThreadSafe;

/**
 * A ring string buffer with a initial size.
 * When there is enough space in the buffer, append the input string.
 * When there is not enough space in the buffer, double the size.
 *
 * The class should be thread safe.
 *
 * Example:
 * Assume initial size = 5.
 *
 * RingStringBuffer buffer = new RingStringBuffer(5);
 * buffer.put("abc"); // abc
 * buffer.get(1); -> return "a".
 * buffer.put("def"); // bcdef
 * buffer.put("aaa"); // bcdefaaa. When the size is not enough, double the size. size=10
 * buffer.size(); -> return 10;
 * buffer.get(6); -> return "bcdefa"
 * buffer.size(); -> return 10;
 * buffer.put("aaa")// aaaaa
 * buffer.get(10); -> throw exception
 * buffer.get(5) -> return "aaaaa"
 *
 */
//@ThreadSafe
public class RingStringBuffer {

	 char[] value;
	 int initialNum;
	 int count;
  /**
   * Constructor.
   * @param size Initial size of the StringBuffer.
   */
  public RingStringBuffer(int size){
	  initialNum=size;
	  count=0;
	  value = new char[size];
  }


  /**
   * Put input string to the buffer.
   * @param input input string.
   */
  public synchronized void put(String input){
	  if (input == null) input = "null";
      int len = input.length();
      int minimumCapacity=count+len;
      while (minimumCapacity - value.length > 0)
      {
          int newCapacity = initialNum * 2;
          initialNum*=2;
          value = Arrays.copyOf(value, newCapacity);
      }
      input.getChars(0, len, value, count);
      count += len;
  }


  /**
   * Return the string with length from buffer.
   *
   * @param length the length of result string.
   * @return the result string.
   */
  public synchronized String get(int length){
      if (length < 0)
          throw new StringIndexOutOfBoundsException(length);
      if (length > count)
          throw new StringIndexOutOfBoundsException(length);
      String str=new String(value, 0, length);
      char[] tempValue=new char[initialNum];
      System.arraycopy(value, length, tempValue, 0, count-length);
      count -= length;
      value=tempValue;
      return str;
	  
  }


  /**
   * @return return the size of the buffer.
   */
  public int size(){
	  return initialNum;
  }



}
