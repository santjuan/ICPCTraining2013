import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		
		static <T> T fill(T arreglo, int val)
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
	}

	static int anterior(int i, int n)
	{
		if(i == 0) return n - 1;
		return i - 1;
	}
	
	static int siguiente(int i, int n)
	{
		return (i + 1) % n;
	}
	
	static boolean esPosibleSwap(int i, int j, int[] vals) 
	{
		if(siguiente(i, vals.length) == j)
			return vals[i] != vals[siguiente(j, vals.length)] && vals[j] != vals[anterior(i, vals.length)];
		if(vals[i] != vals[anterior(j, vals.length)] && vals[i] != vals[siguiente(j, vals.length)] && vals[j] != vals[anterior(i, vals.length)] && vals[j] != vals[siguiente(i, vals.length)])
			return true;
		else return false;
	}
	
	static boolean swap(int i, int j, int[] vals)
	{
		if(!esPosibleSwap(i, j, vals)) return false;
		int tmp = vals[i];
		vals[i] = vals[j];
		vals[j] = tmp;
		return true;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		if(m == 1)
		{
			System.out.println(-1);
			return;
		}
		int[] posibles = sc.nextIntArray(m);
		int[] vals = new int[n];
		int anterior = -1;
		boolean paila = false;
		for(int i = 0; i < vals.length; i++)
		{
			int maximo = -1;
			int cual = -1;
			for(int j = 0; j < posibles.length; j++)
			{
				if(posibles[j] > maximo && posibles[j] > 0 && anterior != j)
				{
					maximo = posibles[j];
					cual = j;
				}
			}
			if(maximo == -1)
			{
				paila = true;
				break;
			}
			vals[i] = cual;
			posibles[cual]--;
			anterior = cual;
		}
		if(vals[0] == vals[vals.length - 1])
		{
			int cual = -1;
			for(int j = 0; j < posibles.length; j++)
			{
				if(posibles[j] > 0 && j != vals[vals.length - 2] && j != vals[0])
				{
					cual = j;
					break;
				}
			}
			if(cual == -1)
			{
				int cuenta = 0;
				while(cual == -1 && cuenta < 100)
				{
					if(isOk(vals))
					{
						cual = 0;
						break;
					}
					for(int i = 0; i < vals.length - 1; i++)
					{
						if(swap(i, vals.length - 1, vals))
							break;
					}
					if(cual != -1) break;
					outer:
					for(int i = 0; i < vals.length - 1; i++)
					{
						for(int j = i + 1; j < vals.length - 1; j++)
						{
							if(swap(i, j, vals))
								break outer;
						}
					}
					cuenta++;
				}
				if(cual == -1) paila = true;
			}
			else
				vals[vals.length - 1] = cual;
		}
		if(paila)
		{
			System.out.println(-1);
			return;
		}
		boolean empezo = false;
		for(int i = 0; i < n; i++)
		{
			if(i != 0) System.out.print(" ");
			System.out.print(vals[i] + 1);
		}
		System.out.println();
	}

	private static boolean isOk(int[] vals) 
	{
		for(int i = 0; i < vals.length; i++)
		{
			if(vals[i] == vals[anterior(i, vals.length)]) return false;
			if(vals[i] == vals[siguiente(i, vals.length)]) return false;
		}
		return true;
	}
}