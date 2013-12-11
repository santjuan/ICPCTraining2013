import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeD 
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
	
	static long gcd(long a, long b)
	{
		if(b == 0)
			return a;
		return gcd(b, a % b);
	}

	static final int limite = 1000000000;
	
	static ArrayList <Long> luckys = new ArrayList <Long> ();
	
	static void generarLuckys(String acum)
	{
		if(acum.length() == 0 || Long.parseLong(acum) <= limite)
		{
			if(acum.length() != 0)
				luckys.add(Long.parseLong(acum));
			generarLuckys(acum + '4');
			generarLuckys(acum + '7');
		}
	}

	static int n;
	static boolean[] valores;
	static Integer[][][] dp;
	
	static int dp(int i, boolean anterior, boolean anteriorAnterior)
	{
		if(dp[i][anterior ? 1 : 0][anteriorAnterior ? 1 : 0] != null)
			return dp[i][anterior ? 1 : 0][anteriorAnterior ? 1 : 0];
		if(i == n)
			return dp[i][anterior ? 1 : 0][anteriorAnterior ? 1 : 0] = anterior == anteriorAnterior ? 1 : 0;
		int mejor = 1000000;
		for(int j = 0; j < 4; j++)
		{
			boolean nuevoAnterior = ((j >> 1) & 1) == 1;
			boolean nuevoEste = (j & 1) == 1;
			if((nuevoAnterior != anteriorAnterior) && (valores[i] == anterior))
				mejor = Math.min(mejor, dp(i + 1, nuevoEste, nuevoAnterior) + 1);
		}
		if(anterior != anteriorAnterior)
			mejor = Math.min(mejor, dp(i + 1, valores[i], anterior));
		return dp[i][anterior ? 1 : 0][anteriorAnterior ? 1 : 0] = mejor;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		valores = new boolean[n];
		int actual = 0;
		for(char c : sc.next().toCharArray())
			valores[actual++] = c == '1';
		dp = new Integer[n + 1][2][2];
		int mejor = dp(0, false, false);
		mejor = Math.min(mejor, dp(0, false, true));
		mejor = Math.min(mejor, dp(0, true, false));
		mejor = Math.min(mejor, dp(0, true, true));
		System.out.println(mejor);
	}
}