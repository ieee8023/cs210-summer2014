/*************************************************************************
 *  Compilation:  javac StdIn.java
 *  Execution:    java StdIn
 *  
 *  Supports reading variables of type int, double, String, boolean,
 *  long, or float from standard input.
 * 
 *  % java StdIn
 *  Enter an integer: 3
 *  Your integer was 3
 * 
 *  Enter a double: 3.14
 *  Your double was 3.14
 *
 *  Enter a string: pi
 *  Your String was pi
 *
 *  Enter a boolean: true
 *  Your String was true
 *
 *************************************************************************/

 import java.io.IOException;

 public class StdIn {
    private static int c = ' ';
    private static final int EOF = -1;

    // can't create an instance of this class
    private StdIn() { }

    // is the current character whitespace?
    private static boolean isBlank() {
        return Character.isWhitespace((char) c);
    }

    // is it at end of the file already?
    private static boolean isEOF() { return c == EOF; }

    // return EOF if end of file or IO error
    private static void readC() {
        try { c = System.in.read(); }
        catch(IOException e) { c = EOF; }
    }
    
    // is there more input?
    public static boolean isEmpty() {
       while (!isEOF() && isBlank())
          readC();
       return isEOF();
    }

    // read a token - use StringBuffer for efficiency
    public static String readString() {
        StringBuffer s = new StringBuffer();

        // eat up whitespace
        while (!isEOF() && isBlank())
            readC();

        // now get the string
        while (!isEOF() && !isBlank()) {
            s.append((char) c);
            readC();
        }

        if (s.length() == 0) throw new RuntimeException("Tried to read from empty stdin");
        else return s.toString();
    }

    public static int    readInt()    { return Integer.parseInt(readString());   }
    public static double readDouble() { return Double.parseDouble(readString()); }
    public static float  readFloat()  { return Float.parseFloat(readString());   }
    public static double readLong()   { return Long.parseLong(readString());     }

    public static boolean readBoolean()   {
        String s = readString().toLowerCase();
        if (s.equals("true")  || s.equals("yes") || s.equals("1")) return true;
        if (s.equals("false") || s.equals("no")  || s.equals("0")) return false;
        throw new RuntimeException(s + " is not a valid boolean value");
    }

   // test client
   public static void main(String[] args) {

      // read in an integer and print it out
      System.out.print("Enter an integer: ");
      int x = StdIn.readInt();
      System.out.println("Your integer was " + x);
      System.out.println();

      // read in an double and print it out
      System.out.print("Enter a double: ");
      double y = StdIn.readDouble();
      System.out.println("Your double was " + y);
      System.out.println();

      // read in a String and print it out
      System.out.print("Enter a string: ");
      String z = StdIn.readString();
      System.out.println("Your string was " + z);
      System.out.println();

      // read in a boolean and print it out
      System.out.print("Enter a boolean: ");
      boolean b = StdIn.readBoolean();
      System.out.println("Your boolean was " + b);
      System.out.println();

   }

}
