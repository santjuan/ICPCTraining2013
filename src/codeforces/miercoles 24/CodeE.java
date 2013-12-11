import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
			Arrays.fill(M, Long.MAX_VALUE);
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
		long query(int node, int b, int e, int i, int j)
		{
			long p1, p2;
			
			//if the current interval doesn't intersect 
			//the query interval return -1
			if (i > e || j < b)
				return -1;
			
			//if the current interval is completely included in 
			//the query interval return the value of this node
			if (b >= i && e <= j)
				return M[node];

			//compute the value from
			//left and right part of the interval
			p1 = query(2 * node, b, (b + e) / 2, i, j);
			p2 = query(2 * node + 1, (b + e) / 2 + 1, e, i, j);
			if(p1 == -1)
				return p2;
			if(p2 == -1)
				return p1;
			//join them to generate result
			long tmp = Math.min(p1, p2);
			return tmp;
		}
		
		int rmq(int a, int b)
		{
			return (int) query(1, 0, n - 1, a, b);
		}
	}
	
	static SegmentTree tree;
	static int[] vals;
	static int n;
	static ArrayList <int[]> result = new ArrayList <int[]> ();
	
	static int binarySearch(int a, int b, int l, int acum)
	{
		if(a == b)
			return a;
		if(a + 1 == b)
			return tree.rmq(l, b) > acum ? b : a;
		int mid = (a + b) >>> 1;
		int midV = tree.rmq(l, mid);
		if(midV > acum)
			return binarySearch(mid, b, l, acum);
		else
			return binarySearch(a, mid - 1, l, acum);
	}
	
	static void solve(int l, int r, int acum)
	{
		if(l > r)
			return;
		if(vals[l] == acum)
		{
			solve(l + 1, r, acum);
			return;
		}
		int rightPoint = binarySearch(l, r, l, acum);
		int rmq = tree.rmq(l, rightPoint);
		for(int i = 0; i < rmq - acum; i++)
			result.add(new int[]{l, rightPoint});
		if(rightPoint != l)
			solve(l, rightPoint, rmq);
		solve(rightPoint + 1, r, acum);
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		vals = sc.nextIntArray(n);
		tree = new SegmentTree(n);
		for(int i = 0; i < vals.length; i++)
			tree.update(1, 0, n - 1, i, vals[i]);
		long test = tree.rmq(0, n - 1);
		solve(0, n - 1, 0);
		System.out.println(result.size());
		for(int[] v : result)
			System.out.println((v[0] + 1) + " " + (v[1] + 1));
	}
}
