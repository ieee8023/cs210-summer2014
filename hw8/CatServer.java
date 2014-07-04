import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;

/**
 * CatServer 2014
 * This code contains a flaw. The hash value has 
 * many collisions and is easy to brute force.
 * 
 * debug is left on as a "mistake" by the developer.
 * 
 * @author Joseph Paul Cohen
 *
 */
public class CatServer {

	static boolean debug = true;
	
	public static void main(String[] args) throws Exception {

		if (debug){
			System.out.println("CATDEBUG: Debug mode is on!");
			System.out.println("CATDEBUG: Remember to turn if off or you will leak the password hash!");
		}
		
		// read password from file
		String password = file2String("secret");
		
		
		System.out.println("=================================");
		System.out.println("Welcome to Cat Server 2014");
		System.out.println("Cats are password protected");
		System.out.println("=================================");
		System.out.println("Please enter the password:");
		
		Scanner nernext = new Scanner(System.in);
		String scan = nernext.nextLine();
		nernext.close();
		
		if (checkPassword(scan, password))
			System.out.println(file2String("cat.txt"));
	}

	
	private static boolean checkPassword(String input, String password) throws Exception{
		
		// to prevent network brute forcing
		for (int i = 0; i < 10; i++){
			System.out.print(".");
			Thread.sleep(100);
		}
		System.out.println(".");
		
		System.out.println("CATDEBUG: Input CS210Sha1   : " + Arrays.toString(getCS210Sha1(input)).replace(" ", ""));
		System.out.println("CATDEBUG: Password CS210Sha1: " + Arrays.toString(getCS210Sha1(password)).replace(" ", ""));
		
		// check if hashes match
		if (Arrays.equals(getCS210Sha1(input),getCS210Sha1(password))){
			
			System.out.println("CATDEBUG: Password match");			
			return true;
		}else{
			
			System.out.println("CATDEBUG: Password mismatch");
			return false;
		}
	}
	
	private static byte[] getCS210Sha1(String str) throws Exception{
		
		MessageDigest m = MessageDigest.getInstance("SHA-1");
		
		// reduce complexity to only three bytes
		byte[] cs210sha1 = Arrays.copyOf(m.digest(str.getBytes("UTF-8")), 3);
		
		return cs210sha1;
	}
	
	private static String file2String(String filename) throws Exception{
		
		File file = new File(filename);
	    FileInputStream fis = new FileInputStream(file);
	    byte[] data = new byte[(int)file.length()];
	    fis.read(data);
	    fis.close();
		return new String(data);
	}
	
}

