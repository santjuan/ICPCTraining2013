import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CodeB_1 
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
	}
	
	static boolean divides(char[] n, char[] d, int length)
	{
		if(length > n.length) return false;
		int posN = 0;
		int posD = 0;
		while(posN < n.length)
		{
			if(n[posN] != d[posD]) return false; 
			posN++;
			posD++;
			if(posD == length)
				posD = 0;
		}
		return true;
	}
	
	private static void generarDivisores(ArrayList<Integer> factores, ArrayList<Integer> numeroVeces, int i, ArrayList<Integer> respuesta, int acum)
	{
		if(i == factores.size())
		{
			respuesta.add(acum);
			return;
		}
		for(int j = 0; j <= numeroVeces.get(i); j++)
		{
			generarDivisores(factores, numeroVeces, i + 1, respuesta, acum);
			acum *= factores.get(i);
		}
	}
	
	public static ArrayList <Integer> generarDivisores(int n)
	{
		ArrayList <Integer> factores = new ArrayList <Integer> ();
		ArrayList <Integer> numeroVeces = new ArrayList <Integer> ();
		for(int i = 2; i <= n; i++)
		{
			if(n % i == 0)
			{
				int cuenta = 0;
				while(n % i == 0)
				{
					n /= i;
					cuenta++;
				}
				factores.add(i);
				numeroVeces.add(cuenta);
			}
		}
		ArrayList <Integer> respuesta = new ArrayList <Integer> ();
		generarDivisores(factores, numeroVeces, 0, respuesta, 1);
		return respuesta;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		char[] a = sc.next().toCharArray();
		char[] b = sc.next().toCharArray();
		int cuenta = 0;
		for(int i : generarDivisores(a.length))
		{
			if(divides(a, a, i) && divides(b, a, i))
				cuenta++;
		}
		System.out.println(cuenta);
	}

}
