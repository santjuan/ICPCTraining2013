import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class goldrush 
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
		
		public Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		public int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
		
		public char[][] nextGrid(int r) 
		{
			char[][] grid = new char[r][];
			for(int i = 0; i < r; i++)
				grid[i] = next().toCharArray();
			return grid;
		}
		
		public static <T> T fill(T arreglo, int val)
		{
			if(arreglo instanceof Object[])
			{
				Object[] a = (Object[]) arreglo;
				for(Object x : a)
					fill(x, val);
			}
			else if(arreglo instanceof int[])
				Arrays.fill((int[]) arreglo, val);
			else if(arreglo instanceof double[])
				Arrays.fill((double[]) arreglo, val);
			else if(arreglo instanceof long[])
				Arrays.fill((long[]) arreglo, val);
			return arreglo;
		}
		
		<T> T[] nextObjectArray(Class <T> clazz, int size)
		{
			@SuppressWarnings("unchecked")
			T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
			for(int c = 0; c < 3; c++)
			{
				Constructor <T> constructor;
				try 
				{
					if(c == 0)
						constructor = clazz.getDeclaredConstructor(Scanner.class, Integer.TYPE);
					else if(c == 1)
						constructor = clazz.getDeclaredConstructor(Scanner.class);
					else
						constructor = clazz.getDeclaredConstructor();
				} 
				catch(Exception e)
				{
					continue;
				}
				try
				{
					for(int i = 0; i < result.length; i++)
					{
						if(c == 0)
							result[i] = constructor.newInstance(this, i);
						else if(c == 1)
							result[i] = constructor.newInstance(this);
						else
							result[i] = constructor.newInstance();	
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
				return result;
			}
			throw new RuntimeException("Constructor not found");
		}
		
		public void printLine(int... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(long... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(double... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(int prec, double... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.printf("%." + prec + "f", vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.printf(" %." + prec + "f", vals[i]);
				System.out.println();
			}
		}
	}
	
	
	static long mulMod(long a, long b, long n) {
		if(a == 0 || b <= (1L << 62) / a) return (a * b) % n;
		long result = 0;	
		if(a < b) { long tmp = a; a = b; b = tmp; }
		for(; b != 0; b >>= 1) {
			if((b & 1) != 0) {
				result += a; if(result >= n) result -= n;
			}
			a <<= 1; if(a >= n) a -= n;
		}
		return result;
	}
	
	static long powMod(long a, long exp, long n) {
		long result = 1;
		for(; exp != 0; exp >>= 1) {
			if((exp & 1) != 0) {
				result = mulMod(result, a, n);
			}
			a = mulMod(a, a, n);
		}
		return result;
	}
	
//	static int cuenta(int a, int b, int k)
//	{
//		if(a == k || b == k) return 1;
//		return cuenta(a + 1, b, k) + cuenta(a, b + 1, k);
//	}
	
	static long MOD = 1000003;
	
	static long[] factorial = new long[(int) MOD];
	
	static
	{
		factorial[0] = 1;
		for(int i = 1; i < factorial.length; i++)
			factorial[i] = (factorial[i - 1] * i) % MOD;
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
		if (d > 1) { throw new RuntimeException(); }
		if (a > 0) return a;
		return n + a;
	}
	static long normalBinomial(int n, int k)
	{
		if(k == 0) return 1;
		if(n == k) return 1;
		if(n < k) return 0;
		long ans = factorial[n];
		ans *= inverse(factorial[k], MOD);
		ans %= MOD;
		ans *= inverse(factorial[n - k], MOD);
		ans %= MOD;
		return ans;
	}
	
	static long[] decompose(long n, int minSize)
	{
		ArrayList <Long> result = new ArrayList <Long> ();
		while(n != 0)
		{
			result.add(n % MOD);
			n /= MOD;
		}
		long[] ans = new long[Math.max(result.size(), minSize)];
		for(int i = 0; i < result.size(); i++)
			ans[i] = result.get(i);
		return ans;
	}
	
	// http://en.wikipedia.org/wiki/Lucas'_theorem
	static long lucas(long m, long n)
	{
		long[] mD = decompose(m, 0);
		long[] nD = decompose(n, mD.length);
		long ans = 1;
		for(int i = 0; i < mD.length; i++)
		{
			ans *= normalBinomial((int) mD[i], (int) nD[i]);
			ans %= MOD;
		}
		return ans;
	}
	
	static long solve(long n, long k, boolean impar)
	{
		if(n <= 1) return 1;
		if((n & 1) == 1)
		{
			n--;
			long uno = lucas(2 * k, k) % MOD;
			if(!impar)
				uno *= 2;
			uno %= MOD;
			long ans = powMod(uno, n / 2, MOD) % MOD;
			return (ans * solve(n / 2 + 1, k, impar)) % MOD;
		}
		else
		{
			long uno = lucas(2 * k, k) % MOD;
			if(!impar)
				uno *= 2;
			uno %= MOD;
			long ans = powMod(uno, n / 2, MOD) % MOD;
			return (ans * solve(n / 2, k, impar)) % MOD;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int i = 0; i < t; i++)
		{
			long n = sc.nextLong();
			long k = sc.nextLong();
			System.out.println(solve(n, (k / 2) + (k % 2), (k % 2) == 1));
		}
	}
}