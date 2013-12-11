import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	static final BigInteger two = BigInteger.valueOf(2);
	static final BigInteger limite = BigInteger.TEN.pow(18);

	static BigInteger f(BigInteger num, BigInteger mult)
	{
		BigInteger numR = num.multiply(mult);
		BigInteger ans = BigInteger.ZERO;
		while(numR.mod(two).equals(BigInteger.ZERO))
		{
			numR = numR.shiftRight(1);
			ans = ans.add(numR);
		}
		ans = ans.add(numR.multiply(numR.subtract(BigInteger.ONE)).shiftRight(1));
		return ans;
	}

	static BigInteger binarySearch(BigInteger a, BigInteger b, BigInteger n, BigInteger mult)
	{
		if(a.compareTo(b) > 0)
		{
			return null;
		}
		if(a.equals(b))
			return f(a.shiftLeft(1).add(BigInteger.ONE), mult).equals(n) ? a.shiftLeft(1).add(BigInteger.ONE) : null;
		BigInteger mid = (a.add(b)).shiftRight(1);
		BigInteger fMid = f(mid.shiftLeft(1).add(BigInteger.ONE), mult);
		if(fMid.equals(n))
		{
			return mid.shiftLeft(1).add(BigInteger.ONE);
		}
		if(fMid.compareTo(n) < 0) return binarySearch(mid.add(BigInteger.ONE), b, n, mult);
		else return binarySearch(a, mid.subtract(BigInteger.ONE), n, mult);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		long nE = sc.nextLong();
		BigInteger n = BigInteger.valueOf(nE);
		ArrayList <BigInteger> vals = new ArrayList <BigInteger> ();
		BigInteger mult = BigInteger.ONE;
		while(mult.compareTo(limite) <= 0)
		{
			BigInteger pos = binarySearch(BigInteger.ZERO, limite, n, mult);
			if(pos != null)
				vals.add(pos.multiply(mult));
			mult = mult.shiftLeft(1);
		}
		Collections.sort(vals);
		for(BigInteger b : vals)
			System.out.println(b);
		if(vals.isEmpty())
			System.out.println(-1);
	}
}