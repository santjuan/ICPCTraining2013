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
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		char[] a = sc.next().toCharArray();
		char[] b = sc.next().toCharArray();
		int cuantos11 = 0;
		int cuantos10 = 0;
		int cuantos01 = 0;
		int cuantos00 = 0;
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] == '1' && b[i] == '1')
				cuantos11++;
			if(a[i] == '1' && b[i] == '0')
				cuantos10++;
			if(a[i] == '0' && b[i] == '1')
				cuantos01++;
			if(a[i] == '0' && b[i] == '0')
				cuantos00++;
		}
		int unosA = 0;
		int unosB = 0;
		boolean primero = true;
		while(cuantos11 != 0 || cuantos10 != 0 || cuantos01 != 0 || cuantos00 != 0)
		{
			if(primero)
			{
				if(cuantos11 > 0)
				{
					cuantos11--;
					unosA++;
				}
				else if(cuantos10 > 0)
				{
					cuantos10--;
					unosA++;
				}
				else if(cuantos01 > 0)
					cuantos01--;
				else
					cuantos00--;
			}
			else
			{
				if(cuantos11 > 0)
				{
					cuantos11--;
					unosB++;
				}
				else if(cuantos01 > 0)
				{
					cuantos01--;
					unosB++;
				}
				else if(cuantos10 > 0)
					cuantos10--;
				else
					cuantos00--;
			}
			primero = !primero;
		}
		if(unosA == unosB)
			System.out.println("Draw");
		else if(unosA > unosB)
			System.out.println("First");
		else
			System.out.println("Second");
	}

}
