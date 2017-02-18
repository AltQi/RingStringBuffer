import org.junit.Test;




public class Testq {
  	@Test
  	public void test(){
  		StringBuffer sb=new StringBuffer();
  		 RingStringBuffer buffer = new RingStringBuffer(5);
  		 buffer.put("abc"); // abc
  		 System.out.println(buffer.get(1)); 	//return "a".
  		 buffer.put("def"); // bcdef
  		 buffer.put("aaa"); // bcdefaaa. When the size is not enough, double the size. size=10
  		System.out.println(buffer.size()); 	//return 10;
  		System.out.println(buffer.get(6)); 	//return "bcdefa"
  		System.out.println(buffer.size()); 	//return 10;
  		 buffer.put("aaa");// aaaaa
  		System.out.println(buffer.get(10));//throw exception
  		System.out.println(buffer.get(5));//return "aaaaa"
  	}
}
