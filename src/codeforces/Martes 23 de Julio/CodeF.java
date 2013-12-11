import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeF 
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
	
	static int[] compute_prefix_function(char[] p) {
	    int[] pi = new int[p.length]; pi[0] = -1; int k = -1;
	    for (int i = 1; i < p.length; i++) {
	        while (k >= 0 && p[k + 1] != p[i]) k = pi[k];
	        if (p[k + 1] == p[i]) k++; pi[i] = k;
	    }
	    return pi;
	}

	static boolean KMP_Matcher(String pattern, String text) {
	    char[] p = pattern.toCharArray(); char[] t = text.toCharArray();
	    int[] pi = compute_prefix_function(p); int q = -1;
	    int cuenta = 0;
	    for (int i = 0; i < text.length(); i++) {
	        while (q >= 0 && p[q + 1] != t[i]) q = pi[q];
	        if (p[q + 1] == t[i]) q++;
	        if (q == p.length - 1) {
	            cuenta++;
	            q = pi[q];
	        }
	    }
	    return cuenta >= 2;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		String entrada = sc.next();
		int mejor = 0;
		for(int i = 0; i < entrada.length(); i++)
		{
			for(int j = i + 1; j <= entrada.length(); j++)
			{
				String sub = entrada.substring(i, j);
				if(KMP_Matcher(sub, entrada))
					mejor = Math.max(j - i, mejor);
			}
		}
		System.out.println(mejor);
	}
}