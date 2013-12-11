import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveB 
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

		public BigInteger nextBigInteger()
		{
			return new BigInteger(next());
		}
	}
	
	static StringBuilder sb1, sb2;
	
	static StringBuilder solve(boolean first)
	{
		boolean last = first ? (sb1.charAt(0) == '1' ? false : true) : sb1.charAt(0) == '1';
		StringBuilder ans = new StringBuilder();
		ans.append(first ? '1' : '0');
		if(sb1.length() == 1) 
		{
			if(last == (sb2.charAt(0) == '1')) return ans;
			else return null;
		}
		boolean current = first ? (sb1.charAt(1) == '1' ? false : true) : sb1.charAt(1) == '1';
		for(int i = 1; i < sb1.length(); i++)
		{
			if(last == (sb2.charAt(i - 1) == '1'))
			{
				ans.append('0');
				last = current;
				current = i + 1 < sb1.length() ? (sb1.charAt(i + 1) == '1') : false;
			}
			else
			{
				ans.append('1');
				last = !current;
				current = !(i + 1 < sb1.length() ? (sb1.charAt(i + 1) == '1') : false);
			}
		}
		if(last != (sb2.charAt(sb1.length() - 1) == '1'))
			return null;
		else
			return ans;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		boolean primero = true;
		int caso = 1;
		while(true)
		{
			BigInteger a = sc.nextBigInteger();
			BigInteger b = sc.nextBigInteger();
			if(a.equals(BigInteger.ZERO) && b.equals(BigInteger.ZERO)) return;
			if(!primero) System.out.println();
			primero = false;
			System.out.print("Case Number " + caso++ + ": ");
			sb1 = new StringBuilder(a.toString(2)).reverse();
			sb2 = new StringBuilder(b.toString(2)).reverse();
			while(sb1.length() > sb2.length()) sb2.append('0');
			while(sb1.length() < sb2.length()) sb1.append('0');
			sb1.reverse(); sb2.reverse();	
			StringBuilder sol1 = solve(false);
			StringBuilder sol2 = solve(true);
			if(sol1 == null && sol2 == null)
				System.out.println("impossible");
			else if(sol2 == null)
				System.out.println(toBigInt(sol1));
			else if(sol1 == null)
				System.out.println(toBigInt(sol2));
			else
			{
				BigInteger sa = toBigInt(sol1);
				BigInteger sb = toBigInt(sol2);
				if(sa.bitCount() > sb.bitCount())
					System.out.println(sb);
				else if(sa.bitCount() < sb.bitCount())
					System.out.println(sa);
				else
					System.out.println(sa.min(sb));
			}
		}
	}

	private static BigInteger toBigInt(StringBuilder sol1) 
	{
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < sol1.length(); i++)
			if(s.length() == 0 && sol1.charAt(i) == '0')
				continue;
			else
				s.append(sol1.charAt(i));
		if(s.length() == 0)
			s.append('0');
		return new BigInteger(s.toString(), 2);
	}
}