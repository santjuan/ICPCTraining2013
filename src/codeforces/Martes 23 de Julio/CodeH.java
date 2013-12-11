import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class CodeH 
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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[][] colors = sc.nextIntMatrix(n, 2);
		TreeMap <Integer, Integer> coloresArriba = new TreeMap <Integer, Integer> ();
		TreeMap <Integer, Integer> coloresAbajo = new TreeMap <Integer, Integer> ();
		TreeSet <Integer> todosColores = new TreeSet <Integer> ();
		for(int[] v : colors)
		{
			for(int v1 : v)
				todosColores.add(v1);
			if(coloresArriba.containsKey(v[0]))
				coloresArriba.put(v[0], coloresArriba.get(v[0]) + 1);
			else
				coloresArriba.put(v[0], 1);
			if(v[0] != v[1])
			{
				if(coloresAbajo.containsKey(v[1]))
					coloresAbajo.put(v[1], coloresAbajo.get(v[1]) + 1);
				else
					coloresAbajo.put(v[1], 1);
			}
		}
		int best = Integer.MAX_VALUE;
		int half = (n >>> 1) + (n & 1);
		for(Integer c : todosColores)
		{
			int arriba = coloresArriba.containsKey(c) ? coloresArriba.get(c) : 0;
			int abajo = coloresAbajo.containsKey(c) ? coloresAbajo.get(c) : 0;
			if(arriba >= half)
				best = 0;
			else
			{
				int extra = half - arriba;
				if(extra <= abajo)
					best = Math.min(best, extra);
			}
		}
		System.out.println(best == Integer.MAX_VALUE ? -1 : best);
	}

}
