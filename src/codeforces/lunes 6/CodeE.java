import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeE 
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
	
	static class SegmentTree
	{
		long[] M;
		
		public SegmentTree(int size)
		{
			M = new long[size * 4 + 4];
			Arrays.fill(M, Integer.MAX_VALUE);
		}

		//it's initially called update(1, 0, size - 1, pos, value)
		void update(int node, int b, int e, int pos, long value)
		{
			//if the current interval doesn't intersect 
			//the updated position return -1
			if (pos > e || pos < b)
				return;
			//if the current interval is the updated position
			//then update it
			if (b == e)
			{
				M[node] = value;
				return;
			}
			update(2 * node, b, (b + e) / 2, pos, value);
			update(2 * node + 1, (b + e) / 2 + 1, e, pos, value);
			//update current value after updating childs
			M[node] = Math.min(M[2 * node], M[2 * node + 1]);
		}
		
		//it's initially called query(1, 0, size - 1, i, j)
		int query(int node, int b, int e, int i, int j, int val)
		{
			if (i > e || j < b)
				return -1;
			if(M[node] >= val)
				return -1;
			if(b == e)
				return b;
			int der = query(2 * node + 1, (b + e) / 2 + 1, e, i, j, val);
			if(der != -1)
				return der;
			else
				return query(2 * node, b, (b + e) / 2, i, j, val);
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		SegmentTree segment = new SegmentTree(n);
		int[] vals = sc.nextIntArray(n);
		for(int i = 0; i < n; i++)
			segment.update(1, 0, n - 1, i, vals[i]);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++)
		{
			int res = segment.query(1, 0, n - 1, i, n - 1, vals[i]);
			if(res == -1)
				sb.append(res).append(" ");
			else
				sb.append((res - i) - 1).append(" ");
		}
		System.out.println(sb.toString().trim());
	}

}
