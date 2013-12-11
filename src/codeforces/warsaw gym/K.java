import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;


public class K 
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "table";
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

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		if((n & 1) == 0)
			sc.out.println("IMPOSSIBLE");
		else
		{
			int[] vals = new int[n];
			vals[0] = n;
			boolean empezo = false;
			while(true)
			{
				boolean termino = true;
				for(int i = 0; i < n && termino; i++)
					termino &= vals[i] == 1;
				if(termino)
				{
					sc.out.println("");
					break;
				}
				int cual = 0;
				int cuantosCual = 0;
				for(int i = 0; i < n; i++)
				{
					if(vals[i] > cuantosCual)
					{
						cuantosCual = vals[i];
						cual = i;
					}
				}
				int anterior = cual - 1;
				if(anterior == -1)
					anterior = n - 1;
				int siguiente = (cual + 1) % n;
				vals[cual] -= 2;
				vals[anterior]++;
				vals[siguiente]++;
				if(empezo)
					sc.print(" ");
				empezo = true;
				sc.print((cual + 1) + "");
			}
		}
		sc.out.flush();
	}
}

