import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class B 
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "beer";
		BufferedReader br;
		StringTokenizer st = new StringTokenizer("");
		PrintStream out;		
		
		Scanner()
		{
			if(debug)
			{
				br = new BufferedReader(new InputStreamReader(System.in));
				out = System.out;
			}
			else
			{
				try 
				{
					br = new BufferedReader(new FileReader(name + ".in"));
					out = new PrintStream(new BufferedOutputStream(new FileOutputStream(name + ".out")), false);
				}
				catch (FileNotFoundException e) 
				{
					throw(new RuntimeException());
				}
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
		
		public void println(String s)
		{
			out.println(s);
		}
		
		public void print(String s)
		{
			out.print(s);
		}
	}
	
	private static void generarDivisores(ArrayList<Integer> factores, ArrayList<Integer> numeroVeces, int i, ArrayList<Integer> respuesta, int acum)
	{
		if(i == factores.size())
		{
			respuesta.add(acum);
			return;
		}
		for(int j = 0; j <= numeroVeces.get(i); j++)
		{
			generarDivisores(factores, numeroVeces, i + 1, respuesta, acum);
			acum *= factores.get(i);
		}
	}
	
	public static ArrayList <Integer> generarDivisores(int n)
	{
		ArrayList <Integer> factores = new ArrayList <Integer> ();
		ArrayList <Integer> numeroVeces = new ArrayList <Integer> ();
		for(int i = 2; i <= n; i++)
		{
			if(n % i == 0)
			{
				int cuenta = 0;
				while(n % i == 0)
				{
					n /= i;
					cuenta++;
				}
				factores.add(i);
				numeroVeces.add(cuenta);
			}
		}
		ArrayList <Integer> respuesta = new ArrayList <Integer> ();
		generarDivisores(factores, numeroVeces, 0, respuesta, 1);
		return respuesta;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		Integer[] divisores = generarDivisores(n).toArray(new Integer[0]);
		long mejor = Integer.MAX_VALUE;
		long aM = 0;
		long bM = 0;
		long cM = 0;
		for(int i = 0; i < divisores.length; i++)
			for(int j = 0; j < divisores.length; j++)
			{
				long a = divisores[i];
				long b = divisores[j];
				long c = n / (a * b);
				if(a * b * c == n)
				{
					if(2 * a * b + 2 * b * c + 2 * a * c < mejor)
					{
						mejor = 2 * a * b + 2 * b * c + 2 * a * c;
						aM = a;
						bM = b;
						cM = c;
					}
				}
			}
		sc.println(aM + " " + bM + " " + cM);
		sc.out.flush();
	}
}