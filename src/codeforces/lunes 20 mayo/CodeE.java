import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
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

		public String[] nextStringArray(int n) 
		{	
			String[] vals = new String[n];
			for(int i = 0; i < n; i++)
				vals[i] = next();
			return vals;
		}

		public BigInteger nextBigInteger() 
		{
			return new BigInteger(next());
		}
	}
	
	
	static String a;
	static String b;
	static StringBuilder resultX = new StringBuilder();
	static StringBuilder resultY = new StringBuilder();
	
	public static boolean dp(int bit, boolean carry)
	{
		if(bit == a.length())
			return carry ? false : true;
		if(b.charAt(bit) == '0')
		{
			if(carry)
			{
				if(a.charAt(bit) == '0')
				{
					if(dp(bit + 1, false))
					{
						resultX.append('1');
						resultY.append('1');
						return true;
					}
					else
						return false;
				}
				else
				{
					if(dp(bit + 1, true))
					{
						resultX.append('1');
						resultY.append('1');
						return true;
					}
					else
						return false;
				}
			}
			else
			{
				if(a.charAt(bit) == '0')
				{
					if(dp(bit + 1, false))
					{
						resultX.append('0');
						resultY.append('0');
						return true;
					}
					else
						return false;
				}
				else
				{
					if(dp(bit + 1, true))
					{
						resultX.append('0');
						resultY.append('0');
						return true;
					}
					else
						return false;
				}
			}
		}
		else
		{
			if(carry)
			{
				if(a.charAt(bit) == '0')
				{
					if(dp(bit + 1, true))
					{
						resultX.append('0');
						resultY.append('1');
						return true;
					}
					else
						return false;
				}
				else
					return false;
			}
			else
			{
				if(a.charAt(bit) == '0')
					return false;
				else
				{
					if(dp(bit + 1, false))
					{
						resultX.append('0');
						resultY.append('1');
						return true;
					}
					else
						return false;
				}
			}
		}
	}
	
	static BigInteger getBI(StringBuilder sb)
	{
		sb.reverse();
		String r = sb.toString();
		while(r.length() > 1 && r.charAt(0) == '0')
			r = r.substring(1);
		return new BigInteger(r, 2);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		a = sc.nextBigInteger().toString(2);
		b = sc.nextBigInteger().toString(2);
		while(a.length() < b.length())
			a = "0" + a;
		while(b.length() < a.length())
			b = "0" + b;
		if(dp(0, false))
			System.out.println(getBI(resultX) + " " + getBI(resultY));
		else
			System.out.println("-1");
	}

}
