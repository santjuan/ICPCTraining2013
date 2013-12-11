import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class CodeB 
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
		
		public String[] nextStringArray(int n)
		{
			String[] res = new String[n];
			for(int i = 0; i < res.length; i++)
				res[i] = next();
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
	
	static ArrayList <Integer> ones = new ArrayList <Integer> ();
	static ArrayList <Integer> twos = new ArrayList <Integer> ();
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		for(int i = 0; i < n; i++)
		{
			int ti = sc.nextInt();
			if(ti == 1)
				ones.add(sc.nextInt());
			else
				twos.add(sc.nextInt());
		}
		Collections.sort(ones, Collections.reverseOrder());
		Collections.sort(twos, Collections.reverseOrder());
		int best = Integer.MAX_VALUE;
		for(int i = 0; i <= ones.size(); i++)
			for(int j = 0; j <= twos.size(); j++)
				best = Math.min(best, intentar(i, j));
		System.out.println(best);
	}

	private static int intentar(int i, int j)
	{
		int size = i + 2 * j;
		int left = 0;
		for(int a = i; a < ones.size(); a++)
			left += ones.get(a);
		for(int a = j; a < twos.size(); a++)
			left += twos.get(a);
		if(left <= size)
			return size;
		else
			return Integer.MAX_VALUE;
	}
}