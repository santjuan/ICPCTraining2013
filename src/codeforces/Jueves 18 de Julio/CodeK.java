import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeK 
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
	
	static class Tree
	{
		int[] ones;
		int[] zeroes;
		boolean[] pending;
		
		Tree(int[] initials, int bitId)
		{
			int size = 1 << ((int) (Math.ceil(Math.log(initials.length) / Math.log(2)) + 1));
			ones = new int[size];
			zeroes = new int[size];
			pending = new boolean[size];
			init(1, 0, initials.length - 1, initials, bitId);
		}
		
		void init(int id, int i, int j, int[] initial, int bitId)
		{
			pending[id] = false;
			if(i == j)
			{
				if(((initial[i] >> bitId) & 1) == 1)
				{
					ones[id] = 1;
					zeroes[id] = 0;
				}
				else
				{
					ones[id] = 0;
					zeroes[id] = 1;
				}
			}
			else
			{
				int mid = (i + j) >>> 1;
				int left = id << 1;
				int right = left + 1;
				init(left, i, mid, initial, bitId);
				init(right, mid + 1, j, initial, bitId);
				ones[id] = ones[left] + ones[right];
				zeroes[id] = zeroes[left] + zeroes[right];
			}
		}
		
		void update(int id, int a, int b, int i, int j)
		{
			propagateAndUpdate(id, a, b);
			if (i > b || j < a)
				return;
			if (a >= i && b <= j)
				pending[id] = !pending[id];
			else
			{
				int mid = (a + b) >>> 1;
				int left = id << 1;
				int right = left + 1;
				update(left, a, mid, i, j);
				update(right, mid + 1, b, i, j);
				propagateAndUpdate(id, a, b);
			}
		}
		
		int query(int id, int a, int b, int i, int j)
		{
			propagateAndUpdate(id, a, b);
			if (i > b || j < a)
				return 0;
			if (a >= i && b <= j)
				return getRealOnes(id);
			else
			{
				int mid = (a + b) >>> 1;
				int left = id << 1;
				int right = left + 1;
				return query(left, a, mid, i, j) + query(right, mid + 1, b, i, j);
			}
		}
		
		int getRealZeroes(int id)
		{
			return pending[id] ? ones[id] : zeroes[id];
		}
		
		int getRealOnes(int id)
		{
			return pending[id] ? zeroes[id] : ones[id];
		}
		
		
		void propagateAndUpdate(int id, int a, int b)
		{
			if(!pending[id])
			{
				if(a == b) return;
				int left = id << 1;
				int right = left + 1;
				ones[id] = getRealOnes(left) + getRealOnes(right);
				zeroes[id] = getRealZeroes(left) + getRealZeroes(right);
				return;
			}
			if(a == b)
			{
				int tmp = ones[id];
				ones[id] = zeroes[id];
				zeroes[id] = tmp;
			}
			else
			{
				int left = id << 1;
				int right = left + 1;
				pending[left] = !pending[left];
				pending[right] = !pending[right];
				ones[id] = getRealOnes(left) + getRealOnes(right);
				zeroes[id] = getRealZeroes(left) + getRealZeroes(right);
			}
			pending[id] = false;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[] initial = sc.nextIntArray(n);
		Tree[] trees = new Tree[21];
		for(int i = 0; i <= 20; i++) trees[i] = new Tree(initial, i);
		int m = sc.nextInt();
		for(int i = 0; i < m; i++)
		{
			if(sc.nextInt() == 1)
			{
				long ans = 0;
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				for(int j = 0; j <= 20; j++)
					ans += trees[j].query(1, 0, initial.length - 1, a, b) * (1L << j);
				System.out.println(ans);
			}
			else
			{
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				int x = sc.nextInt();
				for(int j = 0; j <= 20; j++)
					if((x & (1 << j)) != 0)
						trees[j].update(1, 0, initial.length - 1, a, b);
			}
		}
	}

}
