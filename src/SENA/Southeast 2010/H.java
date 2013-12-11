import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class H 
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
	
	public static void main(String[] args)
	{
		final int[] f = new int[1002];
		final int[] d = new int[1002];
		final int[][] dp = new int[1001][20001];
		Scanner sc = new Scanner();
		while(true)
		{
			final int n = sc.nextInt();
			final int k = sc.nextInt();
			final int l = sc.nextInt();
			if(n == 0 && k == 0 && l == 0)
				return;
			int sumaF = 0;
			for(int i = 1; i <= n; i++)
			{
				f[i] = sc.nextInt();
				sumaF += f[i];
				d[i] = sc.nextInt();
			}
			dp[0][0] = 0;
			for(int i = 1; i <= sumaF; i++)
				dp[0][i] = 1000000;
			for(int current = 1; current <= n; current++)
			{
				int fCurrent = f[current];
				int dCurrent = d[current];
				for(int fun = 0; fun <= sumaF; fun++)
				{
					int p1 = fun < fCurrent ? 1000000 : dp[current - 1][fun - fCurrent];
					p1 += dCurrent;
					int p2 = dp[current - 1][fun];
					if(p2 < k)
						p2 = 0;
					else if(p2 < 1000000)
						p2 -= k;
					int minimo = Math.min(p1, p2);
					dp[current][fun] = minimo > l ? 1000000 : minimo;
				}
			}
			int mejor = -1;
			for(int i = 0; i <= sumaF; i++)
			{
				int ans = dp[n][i];
				if(ans <= l)
					mejor = i;
			}
			System.out.println(mejor);
		}
	}
}