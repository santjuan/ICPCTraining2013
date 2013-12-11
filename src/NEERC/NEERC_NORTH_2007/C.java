import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;


public class C 
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "crosses";
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
	
//	Boolean[] dp = new Boolean[2001];
//	
//	public static boolean dp(int n)
//	{
//		if(n <= 0)
//			return false;
//		boolean primera = (!dp(n - 2)) || (!dp(n - 3));
//		for(int i = 1; i < n - 1; i++)
//		{
//			boolean a = dp()
//		}
//	}
//	
	static Integer[] grundy = new Integer[10010];
	
	static int grundy(int n)
	{
		if(n <= 0)
			return 0;
		boolean[] mex = new boolean[2001];
		if(grundy[n] != null)
			return grundy[n];
		for(int i = 0; i <= 2000; i++)
			mex[i] = false;
		mex[grundy(n - 3)] = true;
		for(int i = 1; i < (n - 1) / 2 + 1; i++)
			mex[grundy(i - 2) ^ grundy(n - i - 3)] = true;
		int menor = -1;
		while(mex[++menor]);
		grundy[n] = menor;
		return grundy[n];
	}

	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		sc.println(grundy(sc.nextInt()) == 0 ? "2" : "1");
		sc.out.flush();
	}
}