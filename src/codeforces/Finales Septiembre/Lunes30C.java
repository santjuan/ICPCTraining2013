import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;


public class Lunes30C 
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
	
	static class BigRational implements Comparable <BigRational> 
	{
	    static BigRational zero = new BigRational(BigInteger.valueOf(0), BigInteger.valueOf(1));
	    
	    private BigInteger num;
	    private BigInteger den;

	    public BigRational(BigInteger numerator, BigInteger denominator) {
	        BigInteger g = numerator.gcd(denominator);
	        num = numerator.divide(g);
	        den = denominator.divide(g);
	        if (den.signum() == -1) { den = den.negate(); num = num.negate(); }
	    }

	    public BigInteger numerator()   { return num; }
	    public BigInteger denominator() { return den; }

	    public String toString() { 
	        if (den.equals(BigInteger.ONE)) return num + "";
	        else          return num + "/" + den;
	    }

	    public int compareTo(BigRational b) {
	        BigRational a = this;
	        BigInteger lhs = a.num.multiply(b.den);
	        BigInteger rhs = a.den.multiply(b.num);
	        if (lhs.compareTo(rhs) < 0) return -1;
	        if (lhs.compareTo(rhs) > 0) return +1;
	        return 0;
	    }

	    public boolean equals(Object y) {
	        BigRational b = (BigRational) y;
	        return compareTo(b) == 0;
	    }

	    public int hashCode() {
	        return this.toString().hashCode();
	    }

	    public BigInteger lcm(BigInteger m, BigInteger n) {
	        return m.abs().multiply(n.abs()).divide(m.gcd(n)); 
	    }

	    public BigRational times(BigRational b) {
	        BigRational a = this;
	        BigRational c = new BigRational(a.num, b.den);
	        BigRational d = new BigRational(b.num, a.den);
	        return new BigRational(c.num.multiply(d.num), c.den.multiply(d.den));
	    }
	    
	    public BigRational plus(BigRational b)
	    {
	        BigRational a = this;
	        if (a.compareTo(zero) == 0) return b;
	        if (b.compareTo(zero) == 0) return a;
	        BigInteger f = a.num.gcd(b.num);
	        BigInteger g = a.den.gcd(b.den);
	        
	        BigRational s = 
	        new BigRational((a.num.divide(f)).multiply(b.den.divide(g)).add((b.num.divide(f)).multiply((a.den.divide(g)))),
	        lcm(a.den, b.den));
	        s.num = s.num.multiply(f);
	        return s;
	    }

	    public BigRational negate() {
	        return new BigRational(num.negate(), den);
	    }

	    public BigRational minus(BigRational b) {
	        BigRational a = this;
	        return a.plus(b.negate());
	    }

	    public BigRational reciprocal() { return new BigRational(den, num);  }

	    public BigRational divides(BigRational b) {
	        BigRational a = this;
	        return a.times(b.reciprocal());
	    }
	    
	    public BigRational abs() {
	        if(num.signum() < 0)
	            return negate();
	        else
	            return this;
	    }
	}
	
	static BigRational calculate(long[] vals, int current)
	{
		if(current == vals.length - 1)
			return new BigRational(BigInteger.valueOf(vals[current]), BigInteger.ONE);
		else
			return new BigRational(BigInteger.valueOf(vals[current]), BigInteger.ONE).plus(calculate(vals, current + 1).reciprocal());
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		BigRational a = new BigRational(BigInteger.valueOf(sc.nextLong()), BigInteger.valueOf(sc.nextLong()));
		int n = sc.nextInt();
		long[] vals = sc.nextLongArray(n);
		BigRational b = calculate(vals, 0);
		System.out.println(a.equals(b) ? "YES" : "NO");
	}
}