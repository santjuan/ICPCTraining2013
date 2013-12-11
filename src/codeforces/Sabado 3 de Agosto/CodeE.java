import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	
	static class Rational implements Comparable <Rational> 
	{
	    static Rational zero = new Rational(0, 1);
	    static Rational one = new Rational(1, 1);
	    
	    private int num;
	    private int den;

	    public Rational(int numerator, int denominator) {
	        int g = gcd(numerator, denominator);
	        num = numerator   / g;
	        den = denominator / g;
	        if (den < 0) { den = -den; num = -num; }
	    }

	    public int numerator()   { return num; }
	    public int denominator() { return den; }

	    public String toString() { 
	        if (den == 1) return num + "";
	        else          return num + "/" + den;
	    }

	    public int compareTo(Rational b) {
	        Rational a = this;
	        int lhs = a.num * b.den;
	        int rhs = a.den * b.num;
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

	    private int gcd(int m, int n) {
	        if (m < 0) m = -m;
	        if (n < 0) n = -n;
	        if (0 == n) return m;
	        else return gcd(n, m % n);
	    }

	    public int lcm(int m, int n) {
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
	        int f = gcd(a.num, b.num);
	        int g = gcd(a.den, b.den);
	        
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
	
	static Rational[][][][][] dp = new Rational[12][12][12][12][12];
	
	static Rational dp(int turno, int salido, int n, int m, int k)
	{
		if(dp[turno][salido][n][m][k] != null) return dp[turno][salido][n][m][k];
		if(turno == m) return salido == k ? Rational.one : Rational.zero;
		int noHanSalido = n - salido;
		Rational ans = Rational.zero;
		if(noHanSalido > 0)
			ans = ans.plus(new Rational(noHanSalido, n).times(dp(turno + 1, salido + 1, n, m, k)));
		ans = ans.plus(new Rational(salido, n).times(dp(turno + 1, salido, n, m, k)));
		return dp[turno][salido][n][m][k] = ans;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		for(int i = 0; i < n; i++)
			System.out.println(dp(0, 0, sc.nextInt(), sc.nextInt(), sc.nextInt()));
	}
}