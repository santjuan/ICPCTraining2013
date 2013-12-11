import java.util.ArrayList;


public class Vals 
{
	public static void main(String[] args)
	{
		ArrayList <String> arista = new ArrayList <String> ();
		for(char a = 'A' + 1; a <= 'Z'; a++)
			arista.add(((char) (a - 1)) + " " + a);
		arista.add("Z a");
		for(char a = 'a' + 1; a <= 'z'; a++)
			arista.add(((char) (a - 1)) + " " + a);
		System.out.println(arista.size());
		for(String s : arista)
			System.out.println(s);
		System.out.println("1000 A z");
	}

}
