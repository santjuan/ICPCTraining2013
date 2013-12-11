import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;


public class I 
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "ichess";
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
		int a = sc.nextInt();
		int b = sc.nextInt();
		int[] vals = new int[]{a, b};
		Arrays.sort(vals);
		int best = 0;
		for(int l = 1; true; l++)
		{
			int l2 = l * l;
			if((l & 1) == 1)
			{
				int aC = l2 / 2;
				int bC = aC + 1;
				if(vals[0] < aC || vals[1] < bC)
					break;
			}
			else
			{
				int aC = l2 / 2;
				if(vals[0] < aC || vals[1] < aC)
					break;
			}
			best = l;
		}
		sc.println(best == 0 ? "Impossible" : best + "");
		sc.out.flush();
	}

}
