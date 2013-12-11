import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MartesF 
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
		
		public Collection <Integer> wrap(int[] as)
		{
			ArrayList <Integer> ans = new ArrayList <Integer> ();
			for(int i : as)
				ans.add(i);
			return ans;
		}
		
		public int[] unwrap(Collection <Integer> collection)
		{
			int[] vals = new int[collection.size()];
			int index = 0;
			for(int i : collection) vals[index++] = i;
			return vals;
		}
	}
	
	static long mulMod(long a, long b, long n) 
	{
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

	static long gcd(long u, long v) 
	{
		while(u != 0){v %= u; long tmp = u; u = v; v = tmp;} return v;
	}
	
	static long f(long x, long c, long n)
	{
		long result = mulMod(x, x, n);
		result += c; if(result >= n) result -= n;
		return result;
	}
	
	static long pollardsRho(long n) 
	{
		for (int count = 0, c = 1;;) {
			long x = 2, y = 2, pot = 1, lam = 1, d;
			do {
				++count;
				if(pot == lam) {x = y; pot <<= 1; lam = 0;}
	      y = f(y,c,n);
	      lam++;
	      d = gcd(x >= y ? x - y : y - x, n);
			} while (d == 1);
	    if (d != n) return d; else c++;
		}
	}
	
	static long powMod(long a, long exp, long n) 
	{
		long result = 1;
		for(; exp != 0; exp >>= 1) {
			if((exp & 1) != 0) {
				result = mulMod(result, a, n);
			}
			a = mulMod(a, a, n);
		}
		return result;
	}
	
	static boolean primeTestMillerRabin(long n, long a)
	{
		long d = n-1, x;
		int s = 0, r;
		while ((d & 1) == 0 && d != 0){ s++; d >>= 1;}
		while (a >= n) a >>= 1;
		x = powMod(a, d, n);
		if (x != 1 && x != n-1) {
			r = 1;
			while (r <= s-1 && x != n-1) {
				x = mulMod(x, x, n);
				if (x == 1) return false; else r++;
			}
			if (x != n-1) return false;
		}
		return true;
	}
	
	static boolean isPrime(long n) 
	{
		if (n <= 1) return false;
		if (n <= 3) return true;
	  if ((n & 1) == 0) return false;
	  return primeTestMillerRabin(n, 2) && primeTestMillerRabin(n, 3) 
	  && primeTestMillerRabin(n, 5) && primeTestMillerRabin(n, 7) 
	  && (n < 3215031751L || (primeTestMillerRabin(n, 11) 
	  && (n < 2152302898747L || (primeTestMillerRabin(n, 13) 
	  && (n < 3474749660383L || (primeTestMillerRabin(n, 17) 
	  && (n < 341550071728321L || (primeTestMillerRabin(n, 23)))))))));
	}
	
	static void factorizar(long n, Map <Long, Integer> todos)
	{
		if(n == 1)
			return;
		if(isPrime(n))
		{
			if(todos.containsKey(n))
				todos.put(n, todos.get(n) + 1);
			else
				todos.put(n, 1);
		}
		else
		{
			long fact = pollardsRho(n);
			factorizar(fact, todos);
			factorizar(n / fact, todos);
		}
	}
	
	static class FactorNumber
	{
		TreeMap <Long, Integer> factors = new TreeMap <Long, Integer> ();
		
		FactorNumber(long number)
		{
			factorizar(number, factors);
		}
		
		
		long pow(long a, int b)
		{
			if(b == 0) return 1;
			if(b == 1) return a;
			long acum = pow(a, b / 2);
			acum *= acum;
			if((b & 1) == 1)
				acum *= a;
			return acum;
		}
		
		long getCurrent()
		{
			long acum = 1;
			for(Map.Entry<Long, Integer> e : factors.entrySet())
				acum *= pow(e.getKey(), e.getValue());
			return acum;
		}
	}
	
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		FactorNumber number = new FactorNumber(n);
		long total = 0;
		while(true)
		{
			long current = number.getCurrent();
			total += current;
			if(current == 1) break;
			long first = number.factors.firstKey();
			int exponent = number.factors.get(first);
			if(exponent == 1) number.factors.remove(first);
			else number.factors.put(first, exponent - 1);
		}
		sc.printLine(total);
	}
}