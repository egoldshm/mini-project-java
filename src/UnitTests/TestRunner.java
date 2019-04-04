/**
 * 
 */
package UnitTests;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author egoldshm
 *
 */
public class TestRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		tryClass(CameraTest.class);
		tryClass(Point3DTest.class);
		tryClass(VectorTest.class);

	}
	
	public static void tryClass(Class<?> c)
	{
	      Result result = JUnitCore.runClasses(c);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	}

}
