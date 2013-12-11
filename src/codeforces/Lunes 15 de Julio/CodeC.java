import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	static long mod = 1000000007;
	
	static long bigmod(long b, long p, long m){
		 long mask = 1;
		 long pow2 = b % m;
		 long r = 1;
		 while (mask != 0){
		   if ((p & mask) != 0) r = (r * pow2) % m;
		   pow2 = (pow2 * pow2) % m;
		   mask <<= 1;
		 }
		 return r % m;
	}

	static long[] gcd(long p, long q)
	{
		if (q == 0)
			return new long[] { p, 1, 0 };
		long[] vals = gcd(q, p % q);
		long d = vals[0];
		long a = vals[2];
		long b = vals[1] - (p / q) * vals[2];
		return new long[] { d, a, b };
	}

	static long inverse(long k, long n) 
	{
		long[] vals = gcd(k, n);
		long d = vals[0];
		long a = vals[1];
		if (a > 0) return a;
		return n + a;
	}
	static long getSolution(int mult, int k)
	{
		long ans = 1;
		long base = bigmod(2, mult, mod);
		ans *= bigmod(base, k + 1, mod);
		ans %= mod;
		ans--;
		ans += mod;
		ans %= mod;
		ans *= inverse(((base - 1) + mod) % mod, mod);
		ans %= mod;
		return ans;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		String a = sc.next();
		int k = sc.nextInt();
		int mult = a.length();
		long res = 0;
		for(int i = 0; i < a.length(); i++)
		{
			if(a.charAt(i) == '0' || a.charAt(i) == '5')
			{
				long ans = bigmod(2, i, mod);
				ans %= mod;
				ans *= getSolution(mult, k - 1);
				ans %= mod;
				res += ans;
				res %= mod;
			}
		}
		System.out.println(res);
	}
}