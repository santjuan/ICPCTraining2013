import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LiveE 
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
	
	static class Rational implements Comparable <Rational> 
	{
	    static Rational zero = new Rational(0, 1);
	    
	    private long num;
	    private long den;

	    public Rational(long numerator, long denominator) {
	        long g = gcd(numerator, denominator);
	        num = numerator   / g;
	        den = denominator / g;
	        if (den < 0) { den = -den; num = -num; }
	    }

	    public long numerator()   { return num; }
	    public long denominator() { return den; }

	    public String toString() { 
	        if (den == 1) return num + "";
	        else          return num + "/" + den;
	    }

	    public int compareTo(Rational b) {
	        Rational a = this;
	        long lhs = a.num * b.den;
	        long rhs = a.den * b.num;
	        if (lhs < rhs) return -1;
	        if (lhs > rhs) return +1;
	        return 0;
	    }

	    public boolean equals(Object y) {
	        Rational b = (Rational) y;
	        return compareTo(b) == 0;
	    }

	    public int hashCode() {
	        return this.toString().hashCode();
	    }

	    private long gcd(long m, long n) {
	        if (m < 0) m = -m;
	        if (n < 0) n = -n;
	        if (0 == n) return m;
	        else return gcd(n, m % n);
	    }

	    public long lcm(long m, long n) {
	        if (m < 0) m = -m;
	        if (n < 0) n = -n;
	        return m * (n / gcd(m, n)); 
	    }

	    public Rational times(Rational b) {
	        Rational a = this;
	        Rational c = new Rational(a.num, b.den);
	        Rational d = new Rational(b.num, a.den);
	        return new Rational(c.num * d.num, c.den * d.den);
	    }
	    
	    public Rational plus(Rational b) {
	        Rational a = this;
	        if (a.compareTo(zero) == 0) return b;
	        if (b.compareTo(zero) == 0) return a;
	        long f = gcd(a.num, b.num);
	        long g = gcd(a.den, b.den);
	        
	        Rational s = 
	        new Rational((a.num / f) * (b.den / g) + (b.num / f) * (a.den / g),
	        lcm(a.den, b.den));
	        
	        s.num *= f;
	        return s;
	    }

	    public Rational negate() {
	        return new Rational(-num, den);
	    }

	    public Rational minus(Rational b) {
	        Rational a = this;
	        return a.plus(b.negate());
	    }

	    public Rational reciprocal() { return new Rational(den, num);  }

	    public Rational divides(Rational b) {
	        Rational a = this;
	        return a.times(b.reciprocal());
	    }
	    
	    public Rational abs() {
	        if(num < 0)
	            return negate();
	        else
	            return this;
	    }
	}
	
	static Rational[][] precalcular()
	{
		Rational[][] ans = new Rational[401][402];
		ans[0][1] = new Rational(1, 1);
		for(int i = 1; i < 401; i++)
		{
			for(int j = 2; j <= (i + 1); j++)
				ans[i][j] = new Rational(i, j).times(ans[i - 1][j - 1]);
			ans[i][1] = new Rational(1, 1);
			for(int j = 2; j <= (i + 1); j++)
				ans[i][1] = ans[i][1].minus(ans[i][j]);
		}
		return ans;
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		Rational[][] ans = precalcular();
		int t = sc.nextInt();
		for(int i = 0; i < t; i++)
		{
			int id = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.println(id + " " + ans[a][b]);
		}
	}

}
