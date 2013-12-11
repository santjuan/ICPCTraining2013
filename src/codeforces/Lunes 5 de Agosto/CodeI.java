import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

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

	static int[] solve(int[] vals)
	{
		TreeMap <Integer, int[]> subiendo = new TreeMap <Integer, int[]> (); 
		TreeMap <Integer, int[]> bajando = new TreeMap <Integer, int[]> (); 
		TreeMap <Integer, Integer> numeros = new TreeMap <Integer, Integer> ();
		for(int i = vals.length - 1; i >= 0; i--)
		{
			int n = vals[i];
			int[] k = subiendo.lowerEntry(n) == null ? null : subiendo.lowerEntry(n).getValue();
			if(k != null)
				return new int[]{i, k[0], k[1]};
			k = bajando.higherEntry(n) == null ? null : bajando.higherEntry(n).getValue();
			if(k != null)
				return new int[]{i, k[0], k[1]};
			Integer p = numeros.higherKey(n);
			if(p != null)
				subiendo.put(n, new int[]{i, numeros.get(p)});
			p = numeros.lowerKey(n);
			if(p != null)
				bajando.put(n, new int[]{i, numeros.get(p)});
			numeros.put(n, i);
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[] k = solve(sc.nextIntArray(n));
		if(k == null) System.out.println(0);
		else
		{
			System.out.println(3);
			System.out.println((k[0] + 1) + " " + (k[1] + 1) + " " + (k[2] + 1));	
		}
	}
}