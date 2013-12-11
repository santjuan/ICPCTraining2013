import java.util.Scanner;
import java.util.TreeSet;


public class CodeB 
{
	
	static TreeSet <Long> todos = new TreeSet <Long> ();
	
	static void generate(String current, int cuatros, int sietes)
	{
		if(current.length() == 12)
			return;
		if(current.length() != 0 && cuatros == sietes)
			todos.add(Long.parseLong(current));
		generate(current + '4', cuatros + 1, sietes);
		generate(current + '7', cuatros, sietes + 1);
	}
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		generate("", 0, 0);
		long n = sc.nextLong();
		System.out.println(todos.ceiling(n));
	}

}
