import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.StringTokenizer;


public class H
{
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st = new StringTokenizer("");
		
		Scanner()
		{
//			br = new BufferedReader(new InputStreamReader(System.in));
			try 
			{
				br = new BufferedReader(new FileReader("roman.in"));
			}
			catch (FileNotFoundException e) 
			{
				throw(new RuntimeException());
			}
		}
		
		public String nextLine()
		{
			try
			{
				return br.readLine();
			}
			catch(Exception e)
			{
				throw(new RuntimeException());
			}
		}
		
		public String next()
		{
			while(!st.hasMoreTokens())
			{
				String l = nextLine();
				if(l == null)
					return null;
				st = new StringTokenizer(l);
			}
			return st.nextToken();
		}
		
		public int nextInt()
		{
			return Integer.parseInt(next());
		}
	}
	
	static int convertirRomano(int numero)
	{
		int cuenta = 0;
		while(numero >= 1000)
		{
			cuenta++;
			numero -= 1000;
		}
		switch(numero / 100)
		{
			case 1: cuenta++; break;
			case 2: cuenta += 2; break;
			case 3: cuenta += 3; break;
			case 4: cuenta += 2; break;
			case 5: cuenta++; break;
			case 6: cuenta += 2; break;
			case 7: cuenta += 3; break;
			case 8: cuenta += 4; break;
			case 9: cuenta += 2; break;
		}
		numero %= 100;
		switch(numero / 10)
		{
			case 1: cuenta++; break;
			case 2: cuenta += 2; break;
			case 3: cuenta += 3; break;
			case 4: cuenta += 2; break;
			case 5: cuenta++; break;
			case 6: cuenta += 2; break;
			case 7: cuenta += 3; break;
			case 8: cuenta += 4; break;
			case 9: cuenta += 2; break;
		}
		numero %= 10;
		switch(numero)
		{
			case 1: cuenta++; break;
			case 2: cuenta += 2; break;
			case 3: cuenta += 3; break;
			case 4: cuenta += 2; break;
			case 5: cuenta++; break;
			case 6: cuenta += 2; break;
			case 7: cuenta += 3; break;
			case 8: cuenta += 4; break;
			case 9: cuenta += 2; break;
		}
		return cuenta;
	}
	
	static int convertir(String num)
	{
		if(num.endsWith("BC"))
		{
			int nR = Integer.parseInt(num.substring(0, num.length() - 2));
			return 753 - nR + 1;
		}
		else
		{
			int nR = Integer.parseInt(num.substring(0, num.length() - 2));
			return 754 + nR - 1;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{

		// PrintStream out = System.out;
		@SuppressWarnings("resource")
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("roman.out")));
		Scanner sc = new Scanner();
		String[] res = sc.next().split("-");
		int a = convertir(res[0]);
		int b = convertir(res[1]);
		int mayor = 0;
		for(int i = a; i <= b; i++)
			mayor = Math.max(mayor, convertirRomano(i));
		out.println(mayor);
		out.flush();
	}
}