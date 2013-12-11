import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class worms 
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

	static class SegmentTree
	{
		long[] M;
		boolean max;
		int size;
		
		public SegmentTree(int size2, boolean max2)
		{
			size = size2;
			M = new long[size * 4 + 4];
			max = max2;
			if(!max)
				Arrays.fill(M, Long.MAX_VALUE);
		}
		
		void update(int pos, long value)
		{
			update(1, 0, size - 1, pos, value);
		}
		
		long query(int a, int b)
		{
			return query(1, 0, size - 1, a, b);
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
			M[node] = max ? Math.max(M[2 * node], M[2 * node + 1]) : Math.min(M[2 * node], M[2 * node + 1]);
		}
		
		//it's initially called query(1, 0, size - 1, i, j)
		long query(int node, int b, int e, int i, int j)
		{
			long p1, p2;
			
			//if the current interval doesn't intersect 
			//the query interval return -1
			if (i > e || j < b)
				return max ? Long.MIN_VALUE : Long.MAX_VALUE;
			
			//if the current interval is completely included in 
			//the query interval return the value of this node
			if (b >= i && e <= j)
				return M[node];

			//compute the value from
			//left and right part of the interval
			p1 = query(2 * node, b, (b + e) / 2, i, j);
			p2 = query(2 * node + 1, (b + e) / 2 + 1, e, i, j);
			//join them to generate result
			long tmp = max ? Math.max(p1, p2) : Math.min(p1, p2);
			return tmp;
		}
	}
	
	
	static TreeMap <Integer, Integer> allCans;
	static SegmentTree rights;
	static SegmentTree lefts;
	static boolean sortNormal;
	
	static class Can implements Comparable <Can>
	{
		int x;
		int r;
		int left;
		int right;
		int myPos;
		
		Can(int xx, int rr)
		{
			x = xx;
			r = rr;
		}
		
		void startLeftRight()
		{
			left = allCans.ceilingEntry(x - r).getValue();
			right = allCans.floorEntry(x + r).getValue();
			rights.update(myPos, right);
			lefts.update(myPos, left);
		}
		
		boolean updateLeft()
		{
			int val = (int) lefts.query(left, right);
			if(val < left)
			{
				left = val;
				lefts.update(myPos, left);
				return true;
			}
			else
				return false;
		}
		
		boolean updateRight()
		{
			int val = (int) rights.query(left, right);
			if(val > right)
			{
				right = val;
				rights.update(myPos, right);
				return true;
			}
			else
				return false;
		}
		
		boolean update()
		{
			return updateLeft() | updateRight();
		}

		@Override
		public int compareTo(Can o) 
		{
			if(sortNormal)
				return x - o.x;
			else
				return o.r - r;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
			{
				System.out.flush();
				return;
			}
			Can[] cans = new Can[n];
			for(int i = 0; i < n; i++)
				cans[i] = new Can(sc.nextInt(), sc.nextInt());
			Can[] start = cans.clone();
			sortNormal = true;
			Arrays.sort(cans);
			allCans = new TreeMap <Integer, Integer> ();
			rights = new SegmentTree(n, true);
			lefts = new SegmentTree(n, false);
			for(int i = 0; i < n; i++)
			{
				allCans.put(cans[i].x, i);
				cans[i].myPos = i;
			}
			for(int i = 0; i < n; i++)
				cans[i].startLeftRight();
			for(int i = 0; i < n; i++)
				cans[i].updateLeft();
			for(int i = n - 1; i >= 0; i--)
				cans[i].updateRight();
			sortNormal = false;
			Arrays.sort(cans);
			for(int i = 0; i < n; i++)
			{
				int cuenta = 0;
				boolean pudo = true;
				while(pudo)
				{
					if(cuenta == 2) throw new RuntimeException();
					pudo = cans[i].update();
					cuenta++;
				}
			}
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < n; i++)
			{
				sb.append(start[i].right - start[i].left + 1);
				sb.append(' ');
			}
			System.out.println(sb.toString().trim());
		}
	}
}