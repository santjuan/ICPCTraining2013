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
	}
	
	
	static long[][][] dp;
	
	static long dp(int tam, int anterior, boolean normal)
	{
		if(tam == 0)
			return dp[tam][anterior][normal ? 1 : 0] = 1L;
		long ans = 0;
		int anteriorW = fences[anterior][normal ? 1 : 0];
		for(int i = 0; i < fences.length; i++)
		{
			if(i == anterior)
				continue;
			int l = fences[i][0];
			int w = fences[i][1];
			if(l == anteriorW && tam - l >= 0)
				ans += dp[tam - l][i][1];
			else if(w == anteriorW && tam - w >= 0)
				ans += dp[tam - w][i][0];
		}
		return dp[tam][anterior][normal ? 1 : 0] = ans %= 1000000007;
	}
	
	static int[][] fences;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int le = sc.nextInt();
		fences = new int[n][2];
		for(int i = 0; i < n; i++)
		{
			fences[i][0] = sc.nextInt();
			fences[i][1] = sc.nextInt();
		}
		long ans = 0;
		dp = new long[le][n][2];
		for(int i = 0; i < le; i++)
			for(int j = 0; j < n; j++)
				for(int k = 0; k < 2; k++)
					dp(i, j, k == 1 ? true : false);
		for(int i = 0; i < fences.length; i++)
		{
			int l = fences[i][0];
			int w = fences[i][1];
			boolean cuadrado = l == w;
			if(cuadrado && le - l >= 0)
				ans += dp[le - l][i][1];
			else
			{
				if(le - l >= 0)
					ans += dp[le - l][i][1];
				if(le - w >= 0)
					ans += dp[le - w][i][0];
			}
		}
		ans %= 1000000007;
		System.out.println(ans);
	}

}
