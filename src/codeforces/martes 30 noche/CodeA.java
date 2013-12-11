import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeA 
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
	
	static int[] cuenta;
	static int[] empiezan;
	static int agregados = 0;
	static StringBuilder sb = new StringBuilder();
	
	static void agregar(int k, int n, int m, int inicio)
	{
		agregados += k;
		for(int i = 0; i < k; i++)
			sb.append(inicio + 1).append(' ');
		int actual = inicio;
		while(actual < cuenta.length)
		{
			empiezan[actual] += k;
			for(int i = 0; i < n && actual < cuenta.length; i++)
				cuenta[actual++] += k;
			for(int i = 0; i < m && actual < cuenta.length; i++)
				actual++;
		}
	}
	
	static String solucionar(int n, int m, int k)
	{
		cuenta = new int[20000];
		empiezan = new int[20000];
		for(int i = 0; i < 20000; i++)
		{
			if(cuenta[i] == 0)
			{
				if(i - 1 >= 0)
					agregar(1, n, m, i - 1);
			}
			if(cuenta[i] < k)
				agregar(k - cuenta[i], n, m, i);
			if(i - 1 >= 0 && cuenta[i] == empiezan[i])
				agregar(1, n, m, i - 1);
		}
		return sb.toString();
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		String ans = solucionar(sc.nextInt(), sc.nextInt(), sc.nextInt());
		System.out.println(agregados);
		System.out.println(ans.trim());
	}

}
