import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		int[][] stairs = new int[m][3];
		for(int i = 0; i < m; i++)
		{
			int a = sc.nextInt();
			int b = sc.nextInt();
			int h = sc.nextInt();
			stairs[i][0] = a;
			stairs[i][1] = b;
			stairs[i][2] = h;
		}
		int[] queries = sc.nextIntArray(k);
		long cuenta = 0;
		for(int i : queries)
		{
			for(int[] s : stairs)
			{
				if(s[0] <= i && i <= s[1])
					cuenta += s[2] + i - s[0];
			}
		}
		System.out.println(cuenta);
	}

}
