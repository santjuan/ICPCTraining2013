import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
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

	static int[][] chains;
	static int[] ans;
	
	static int mejor(int w, int h, int from)
	{
		int max = 0;
		for(int i = from; i < chains.length; i++)
			if(chains[i][0] > w && chains[i][1] > h)
				max = Math.max(max, ans[i]);
		return max;
	}
	
	static int mejorId(int w, int h, int from)
	{
		int max = 0;
		int id = -1;
		for(int i = from; i < chains.length; i++)
			if(chains[i][0] > w && chains[i][1] > h)
			{
				if(ans[i] > max)
				{
					max = ans[i];
					id = i;
				}
			}
		return id;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();
		chains = new int[n][3];
		for(int i = 0; i < n; i++)
		{
			chains[i][0] = sc.nextInt();
			chains[i][1] = sc.nextInt();
			chains[i][2] = i;
		}
		Arrays.sort(chains, new Comparator <int[]> () {

			@Override
			public int compare(int[] a, int[] b) 
			{
				if(a[0] != b[0])
					return a[0] - b[0];
				return a[1] - b[1];
			}
		});
		ans = new int[n];
		for(int i = n - 1; i >= 0; i--)
			ans[i] = mejor(chains[i][0], chains[i][1], i + 1) + 1;
		int res = mejor(w, h, 0);
		System.out.println(res);
		boolean empezo = false;
		if(res != 0)
		{
			int actual = mejorId(w, h, 0);
			while(actual != -1)
			{
				if(empezo)
					System.out.print(" ");
				empezo = true;
				System.out.print(chains[actual][2] + 1);
				actual = mejorId(chains[actual][0], chains[actual][1], actual + 1);
			}
			System.out.println();
		}
	}
}