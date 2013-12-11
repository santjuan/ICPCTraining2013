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
	
	static int find_lis(int[] a)
	{
		ArrayList <Integer> b = new ArrayList <Integer> ();
		int[] p = new int[a.length];
		int u, v;
		if (a.length == 0) return 0;
		b.add(0);
		for (int i = 1; i < a.length; i++) {
			if (a[b.get(b.size() - 1)] < a[i]) {
				p[i] = b.get(b.size() - 1);
				b.add(i);
				continue;
			}

			for (u = 0, v = b.size()-1; u < v;) {
				int c = (u + v) / 2;
				if (a[b.get(c)] < a[i]) u=c+1; else v=c;
			}

			if (a[i] < a[b.get(u)]) {
				if (u > 0) p[i] = b.get(u-1);
				b.set(u, i);
			}
		}
		for (u = b.size(), v = b.get(b.size() - 1); u-- != 0; v = p[v]) b.set(u, v);
		return b.size();
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[] vals = new int[n];
		int[] reales = new int[n];
		for(int i = 0; i < n; i++)
			reales[sc.nextInt() - 1] = i;
		for(int i = 0; i < n; i++)
			vals[reales[sc.nextInt() - 1]] = -i;
		System.out.println(find_lis(vals));
	}

}