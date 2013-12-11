import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class D 
{
	static class Scanner
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
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
		
		public long nextLong()
		{
			return Long.parseLong(next());
		}
		
		public double nextDouble()
		{
			return Double.parseDouble(next());
		}
	}
	
	static int simulate(int a, int b, int c, int d)
	{
		if(a == b && b == c && c == d)
			return 0;
		return 1 + simulate(Math.abs(a - b), Math.abs(b - c), Math.abs(c - d), Math.abs(d - a));
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			if(a == b && b == c && c == d && d == 0)
				return;
			System.out.println(simulate(a, b, c, d));
		}
	}

}
