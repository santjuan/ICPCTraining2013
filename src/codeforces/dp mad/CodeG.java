import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	}
	
	static int[] cuenta;
	
	static int darCuenta(int i, int j)
	{
		if(i == 0)
			return cuenta[j];
		return cuenta[j] - cuenta[i - 1];
	}
	
	static int k;
	
	static int binariaIzquierda(int a, int b, int pos)
	{
		if(a == b)
			return a;
		if(a + 1 == b)
			return darCuenta(a, pos) == k ? a : b;
		int mid = (a + b) >>> 1;
		if(darCuenta(mid, pos) <= k)
			return binariaIzquierda(a, mid, pos);
		else
			return binariaIzquierda(mid, b, pos);
	}
	
	static int binariaDerecha(int a, int b, int pos)
	{
		if(a == b)
			return a;
		if(a + 1 == b)
			return darCuenta(b, pos) == k ? b : a;
		int mid = (a + b) >>> 1;
		if(darCuenta(mid, pos) >= k)
			return binariaDerecha(mid, b, pos);
		else
			return binariaDerecha(a, mid, pos);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		k = sc.nextInt();
		char[] s = sc.next().toCharArray();
		cuenta = new int[s.length];
		cuenta[0] = s[0] == '1' ? 1 : 0;
		for(int i = 1; i < cuenta.length; i++)
			cuenta[i] = (s[i] == '1' ? 1 : 0) + cuenta[i - 1];
		long total = 0;
		for(int i = 0; i < cuenta.length; i++)
			if(cuenta[i] >= k)
			{
				int a = binariaDerecha(0, i, i);
				if(darCuenta(a, i) != k)
					continue;
				total += a - binariaIzquierda(0, i, i) + 1;
			}
		System.out.println(total);
	}
}