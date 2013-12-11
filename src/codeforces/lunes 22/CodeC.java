import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class CodeC 
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

	
	public static void main(String[] args)
	{
		generarLuckys("");
		luckys.add(4444444444L);
		Collections.sort(luckys);
		Scanner sc = new Scanner();
		int l = sc.nextInt();
		int r = sc.nextInt();
		long acum = 0;
		for(long i : luckys)
		{
			if(i >= r)
			{
				acum += (r - l + 1) * i;
				break;
			}
			if(i >= l)
			{
				acum += (i - l + 1) * i;
				l = (int) (i + 1);
			}
		}
		System.out.println(acum);
	}
}