import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeA
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
	
	public static class Fenwick
	{
		long[] fenwickTree;
		int tam;
		
		public Fenwick(int t)
		{
			fenwickTree = new long[t];
			tam = t;
		}
		
		long query(int a, int b) 
		{
		    if (a == 0)
		    {
		        long sum = 0;
		        for (; b >= 0; b = (b & (b + 1)) - 1)
		          sum += fenwickTree[b];
		        return sum;
		    } 
		    else 
		    {
		        return (query(0, b) - query(0, a - 1));
		    }
		}
	
		void increase(int k, long inc)
		{
		    for (; k < tam; k |= k + 1)
		        fenwickTree[k] += inc;
		}
		
		void increaseRange(int a, int b, long val)
		{
			increase(a, val);
			increase(b + 1, -val);
		}
		
		long queryRangePoint(int a)
		{
			return query(0, a);
		}
	}


	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		Fenwick f = new Fenwick(n + 10);
		Fenwick q = new Fenwick(m + 10);
		int[][] queries = new int[m + 1][3];
		for(int i = 1; i <= n; i++)
			f.increaseRange(i, i, sc.nextLong());
		for(int i = 1; i <= m; i++)
		{
			queries[i][0] = sc.nextInt();
			queries[i][1] = sc.nextInt();
			queries[i][2] = sc.nextInt();
		}
		for(int i = 1; i <= k; i++)
			q.increaseRange(sc.nextInt(), sc.nextInt(), 1L);
		for(int i = 1; i <= m; i++)
		{
			long count = q.query(0, i);
			f.increaseRange(queries[i][0], queries[i][1], queries[i][2] * count);
		}
		for(int i = 1; i <= n; i++)
			System.out.print(f.query(0, i) + " ");
		System.out.println();
	}

}
