import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;


public class CodeA 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File("t-set.txt"));
		System.setOut(new PrintStream("t-setc.txt"));
		while(sc.hasNextLine())
		{
			LinkedList <Double> vals = new LinkedList <Double> ();
			@SuppressWarnings("resource")
			Scanner sc1 = new Scanner(sc.nextLine());
			while(sc1.hasNextDouble())
				vals.add(sc1.nextDouble());
			double last = vals.pollLast();
			double beforeLast = vals.pollLast();
			vals.add(last);
			vals.add(beforeLast);
			String sal = "";
			for(double d : vals)
				sal += " " + d;
			System.out.println(sal.trim());
		}
		System.out.flush();
		System.out.close();
	}

}
