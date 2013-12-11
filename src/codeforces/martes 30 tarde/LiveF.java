import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveF
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
	
	static int n;
	static Long[][][] dp;
	
	static long dp(int mascara, int anterior, boolean mayor)
	{
		if(dp[mascara][anterior + 1][mayor ? 1 : 0] != null)
			return dp[mascara][anterior + 1][mayor ? 1 : 0];
		if(Integer.bitCount(mascara) == n)
			return dp[mascara][anterior + 1][mayor ? 1 : 0] = 1L;
		if(mayor)
		{
			long res = 0;
			for(int i = anterior + 1; i < n; i++)
			{
				if((mascara & (1 << i)) == 0)
					res += dp(mascara ^ (1 << i), i, !mayor);
			}
			return dp[mascara][anterior + 1][mayor ? 1 : 0] = res;
		}
		else
		{
			long res = 0;
			for(int i = 0; i < anterior; i++)
			{
				if((mascara & (1 << i)) == 0)
					res += dp(mascara ^ (1 << i), i, !mayor);
			}
			return dp[mascara][anterior + 1][mayor ? 1 : 0] = res;
		}
	}
	
	static long[] dpPre = new long[]{0, 1, 2, 4, 10, 32, 122, 544, 2770, 15872, 101042, 707584, 5405530, 44736512, 398721962, 3807514624L, 38783024290L, 419730685952L, 4809759350882L, 58177770225664L, 740742376475050L};
	
	static void precalcular()
	{
		for(int i = 1; i <= 19; i++)
		{
			System.out.println("llego " + i);
			n = i;
			dp = new Long[1 << n][n + 3][2];
			dpPre[i] = dp(0, -1, true) + dp(0, n, false);
		}
		dpPre[1] = 1;
	}
	
	

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		//precalcular();
		int t = sc.nextInt();
		for(int i = 0; i < t; i++)
		{
			int id = sc.nextInt();
			int val = sc.nextInt();
			System.out.println(id + " " + dpPre[val]);
		}
	}
}
