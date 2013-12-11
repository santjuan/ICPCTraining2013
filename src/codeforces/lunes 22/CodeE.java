import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
	}
	
	static long gcd(long a, long b)
	{
		if(b == 0)
			return a;
		return gcd(b, a % b);
	}

	static final int limite = 1000000000;
	
	static ArrayList <Long> luckys = new ArrayList <Long> ();
	
	static void generarLuckys(String acum)
	{
		if(acum.length() == 0 || Long.parseLong(acum) <= limite)
		{
			if(acum.length() != 0)
				luckys.add(Long.parseLong(acum));
			generarLuckys(acum + '4');
			generarLuckys(acum + '7');
		}
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		sc.nextInt();
		int k = sc.nextInt();
		char[] s = sc.nextLine().toCharArray();
		LinkedList <Integer> indices = new LinkedList <Integer> ();
		for(int i = 0; i < s.length - 1; i++)
		{
			if(s[i] == '4' && s[i + 1] == '7')
				indices.add(i);
		}
		int cuenta = 0;
		while(!indices.isEmpty() && cuenta < k)
		{
			if(cuenta == 1000000 && ((k & 1) == 0))
				break;
			if(cuenta == 1000001 && ((k & 1) == 1))
				break;
			int actual = indices.pollFirst();
			if(actual < s.length - 1 && s[actual] == '4' && s[actual + 1] == '7')
			{
				if((actual & 1) == 0)
					s[actual] = s[actual + 1] = '4';
				else
					s[actual] = s[actual + 1] = '7';
				if(actual + 2 < s.length && s[actual + 1] == '4' && s[actual + 2] == '7')
					indices.addFirst(actual + 1);
				if(actual - 1 >= 0 && s[actual - 1] == '4' && s[actual] == '7')
					indices.addFirst(actual - 1);
			}
			cuenta++;
		}
		System.out.println(new String(s));
	}
}