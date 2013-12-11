import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeI 
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
				res[i] = nextDouble();
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
	
	
	static int[] tour;
	static double[] tourP;
	static double[][][] dp;
	
	static double dp(int n, int space, int wonC)
	{
		if(n == tour.length) return space >= 0 && wonC == 0 ? 1 : 0;
		if(dp[n][space + tour.length][wonC] != -1) return dp[n][space + tour.length][wonC];
		if(tour[n] == -1)
			return dp[n][space + tour.length][wonC] = tourP[n] * dp(n + 1, space - 1, Math.max(0, wonC - 1)) + (1 - tourP[n]) * dp(n + 1, space, wonC);
		else
			return dp[n][space + tour.length][wonC] = tourP[n] * dp(n + 1, Math.min(tour.length, space + tour[n]), Math.max(0, wonC - 1)) + (1 - tourP[n]) * dp(n + 1, space, wonC);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int l = sc.nextInt();
		int k = sc.nextInt();
		k = Math.min(k, n);
		tourP = sc.nextDoubleArray(n);
		for(int i = 0; i < tourP.length; i++) tourP[i] /= 100.0;
		tour = sc.nextIntArray(n);
		dp = new double[n][tour.length + tour.length + 2][l + 1];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < dp[0].length; j++)
				for(int m = 0; m <= l; m++)
					dp[i][j][m] = -1;
		System.out.println(dp(0, k, l));
	}

}
