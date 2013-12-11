import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeF 
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
		
		public int[] nextIntArray(int n)
		{
			int[] res = new int[n];
			for(int i = 0; i < res.length; i++)
				res[i] = nextInt();
			return res;
		}
		
		public long[] nextLongArray(int n)
		{
			long[] res = new long[n];
			for(int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}
		
		public double[] nextDoubleArray(int n)
		{
			double[] res = new double[n];
			for(int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}
		public void sortIntArray(int[] array)
		{
			Integer[] vals = new Integer[array.length];
			for(int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for(int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}
		
		public void sortLongArray(long[] array)
		{
			Long[] vals = new Long[array.length];
			for(int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for(int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}
		
		public void sortDoubleArray(double[] array)
		{
			Double[] vals = new Double[array.length];
			for(int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for(int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public String[] nextStringArray(int n) 
		{	
			String[] vals = new String[n];
			for(int i = 0; i < n; i++)
				vals[i] = next();
			return vals;
		}
		
		Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
	}
	
	static Integer[][][] dp;
	static int[][][] choice;
	static int[][] board;
	static int INF = -1;
	static int n, m;
	static int k1;
	
	static int dp(int a, int b, int mod)
	{
		if(a == -1)
			return mod == 0 ? 0 : INF;
		if(dp[a][b][mod] != null)
			return dp[a][b][mod];
		int modS = mod + board[a][b];
		modS %= k1;
		int best = INF;
		if(b + 1 < m)
		{
			int p = dp(a - 1, b + 1, modS);
			if(p != INF)
			{
				p += board[a][b];
				if(p > best)
				{
					best = p;
					choice[a][b][mod] = 1;
				}
			}
		}
		if(b - 1 >= 0)
		{
			int p = dp(a - 1, b - 1, modS);
			if(p != INF)
			{
				p += board[a][b];
				if(p > best)
				{
					best = p;
					choice[a][b][mod] = -1;
				}
			}
		}
		return dp[a][b][mod] = best;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		m = sc.nextInt();
		int k = sc.nextInt();
		char[][] bT = new char[n][];
		for(int i = 0; i < n; i++)
			bT[i] = sc.next().toCharArray();
		board = new int[n][m];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				board[i][j] = bT[i][j] - '0';
		k1 = k + 1;
		dp = new Integer[n + 1][m + 1][k1 + 1];
		choice = new int[n + 1][m + 1][k1 + 1];
		int best = INF;
		int c = -1;
		for(int i = 0; i < m; i++)
		{
			int p = dp(n - 1, i, 0);
			if(p > best)
			{
				best = p;
				c = i;
			}
		}
		if(best == INF)
			System.out.println("-1");
		else
		{
			System.out.println(best);
			System.out.println(c + 1);
			int b = c;
			int a = n - 1;
			int mod = 0;
			while(a != 0)
			{
				int r = choice[a][b][mod];
				mod += board[a][b];
				if(r == 1)
					System.out.print("R");
				else
					System.out.print("L");	
				mod %= k1;
				b += r;
				a--;
			}
			System.out.println();
		}
	}

}
