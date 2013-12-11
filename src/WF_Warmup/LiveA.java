import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveA 
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
	
	static long contarTodos(int k)
	{
		return BigInteger.valueOf(3).pow(k).longValue();
	}

	
	static long contarPrimeros(int a, int k)
	{
		if(k == 0)
			return 1;
		int lado = 1 << k;
		int mitad = lado >> 1;
		long ans = 0;
		if(a > mitad)
		{
			ans += contarTodos(k - 1) << 1;
			ans += contarPrimeros(a - mitad, k - 1);
		}
		else
			ans += contarPrimeros(a, k - 1) << 1;
		return ans;
	}
	
	static long contarUltimos(int a, int k)
	{
		if(k == 0)
			return 1;
		int lado = 1 << k;
		int mitad = lado >> 1;
		long ans = 0;
		if(a > mitad)
		{
			ans += contarUltimos(a - mitad, k - 1) << 1;
			ans += contarTodos(k - 1);
		}
		else
			ans += contarUltimos(a, k - 1);
		return ans;
	}
	
	static long contar(int a, int b, int k)
	{
		int lado = 1 << k;
		int mitad = lado >> 1;
		if(k == 0)
			return 1;
		if(a >= mitad)
			return contar(a % mitad, b % mitad, k - 1);
		else if(b < mitad)
			return contar(a, b, k - 1) << 1;
		else
			return (contarUltimos(mitad - a, k - 1) << 1) + contarPrimeros(b - mitad + 1, k - 1);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 1; caso <= t; caso++)
		{
			int k = sc.nextInt();
			System.out.println("Case " + caso + ": " + contar(sc.nextInt() - 1, sc.nextInt() - 1, k));
		}
	}

}
