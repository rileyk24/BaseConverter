
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
/**
 * converts a file containing strings that are from base 2 up to 16 and converts them into base 10 ints
 * @author riley kim
 * @version 11.30.22
 */
public class BaseConverter {
    private final String DIGITS = "0123456789ABCDEF";
    /**
     * Convert a String num in fromBase to base-10 int.
     * @param num the original number
     * @param fromBase the original from base
     * @return a base-10 int of num base fromBase
     */

    public int strToInt(String num, String fromBase) {
        // "FF" "16"
        int value = 0, exp = 0;
        for (int i = num.length() - 1; i >= 0  ; i--){ //do this ;run until this is false; what happens to i when the loop is completed once //
            //System.out.println(decimalBase);
            //System.out.println(exp);

            value += DIGITS.indexOf(num.charAt(i))*Math.pow(Integer.parseInt(String.valueOf(fromBase)), exp);
            exp++;

        }
        // Do i need a try here?
        /* satrt a right most dgiti of num
        run a loop for i ddigits of num -> bkwds
            pull out the char at index i
            find index of char in digits \value += index of char *math.pow(from base, exp)
            exp += 1
            for(int i = num.length()-1; i>= 0 , i--)
            value += 2*math.pow(interger.parseInt(decimalBase), exp)
         */

        return value;
    }

    /**
     * @param num the original number entered into the program
     * @param toBase the base that num is converted to
     * @return toNum, the converted number or 0 if toNum is an empty string
     */
    public String intToStr(int num, int toBase) {
        String toNum = "";
        while (num>0){
            toNum = "" + (DIGITS.charAt(num % toBase))+toNum;
            num /= toBase;
        }
        return (toNum.equals("")) ? "0" : toNum;
    }

    /**
     * Takes an inputted number and converts it into a specific base, and prints it into a designated file while also printing to a terminal
     */

    public void inputConvertPrintWrite()    {
        Scanner in = null;
        PrintWriter out = null;
        try{
            in = new Scanner(new File("datafiles/values20.dat"));
            out = new PrintWriter(new File("datafiles/converted.dat"));
            String[] line; //declaration of an array/list ->
            String output;
            while(in.hasNext()){
                line = in.nextLine().split("\t");
                // write to the file
                //write the original # - string
                //tab
                //write to the original base - string
                //write to the converted number
                //write tot he base string
                if(Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 16)
                    System.out.println("Invalid input base " + line[1]);
                else if(Integer.parseInt(line[2]) < 2 || Integer.parseInt(line[2]) > 16)
                    System.out.println("Invalid output base " + line[2]);
                else{
                    output = intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2])) + "\t" + line[2];
                    System.out.println(line[0] + " base " + line[1] + " = " + output + " base ");
                    out.println(line[0] + "\t" + line[1] + "\t" + output);
                }

                /*
                System.out.println(line[0]); //num
                System.out.println(line[1]); //fromBase
                System.out.println(line[2]); ////toBase
                */

                for(String item : line)
                    System.out.print(item + "\t");
                System.out.println();

            }

            if(out != null)
                out.close();
            if (in != null)
                in.close();

        }
        catch(Exception e){
            System.out.println("something bad happened. details here: "+ e.toString());
        }
    }

    /**`
     * Main method of class BaseConverter
     * @param args Command-line arguments, if needed
     */
    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();
        bc.inputConvertPrintWrite();

    }
}

// notes/qs: when to use new string
// char at vs index of
//split method: splits around a pecific character
