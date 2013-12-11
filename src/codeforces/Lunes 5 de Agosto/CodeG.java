import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class CodeG 
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

	static int n, m;
	static int[] tipos;
	static short[][] dp1;
	
	static int sumaR(int tipo, int hasta)
	{
		if(hasta >= n) return 0;
		if(dp1[tipo][hasta] != -1) return dp1[tipo][hasta];
		return dp1[tipo][hasta] = (short) ((tipos[hasta] == tipo ? 1 : 0) + sumaR(tipo, hasta + 1));
	}
	
	static short[][] dp;
	
	static int dp(int tipo, int actual)
	{
		if(actual == n)
			return 0;
		else
		{
			if(dp[tipo][actual] != -1) return dp[tipo][actual];
			if(tipos[actual] == tipo) return dp(tipo, actual + 1);
			int total = Integer.MAX_VALUE;
			int posible = sumaR(tipo, actual);
			if(tipo != m - 1)
				posible += dp(tipo + 1, actual);
			total = posible;
			total = Math.min(total, (tipos[actual] > tipo ? 1 : 0) + dp(tipo, actual + 1));
			return dp[tipo][actual] = (short) total;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		m = sc.nextInt();
		double[][] entrada = new double[n][2];
		for(int i = 0; i < n; i++)
		{
			entrada[i][0] = sc.nextDouble() - 1;
			entrada[i][1] = sc.nextDouble();
		}
		Arrays.sort(entrada, new Comparator <double[]> () 
		{
			@Override
			public int compare(double[] a, double[] b) 
			{
				return Double.valueOf(a[1]).compareTo(b[1]);
			}
		});
		tipos = new int[n];
		for(int i = 0; i < n; i++) tipos[i] = (int) entrada[i][0];
		dp = new short[m][n];
		for(short[] i : dp) Arrays.fill(i, (short) -1);
		dp1 = new short[m][n];
		for(short[] i : dp1) Arrays.fill(i, (short) -1);
		System.out.println(dp(0, 0));
	}
}