import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class CodeH 
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

	static class Coordenada
	{
		TreeMap <Integer, Integer> valores = new TreeMap <Integer, Integer> ();
		int cuenta;
		int x;
		
		Coordenada(int xx)
		{
			x = xx;
		}
		
		void agregar(int y)
		{
			cuenta++;
			if(valores.get(y) == null)
				valores.put(y, 0);
			valores.put(y, valores.get(y) + 1);
		}
		
		long contar(long mod)
		{
			long total = 1;
			int doces = 0;
			for(int i : valores.values())
				if(i == 2)
					doces++;
			for(int i = 1; i <= cuenta; i++)
			{
				long este = i;
				while((este & 1) == 0 && doces > 0)
				{
					doces--;
					este >>>= 1;
				}
				total *= este;
				total %= mod;
			}
			return total;
		}
	}
	
	static TreeMap <Integer, Coordenada> coordenadas = new TreeMap <Integer, Coordenada> ();
	
	static Coordenada darCoordenada(int x)
	{
		if(!coordenadas.containsKey(x))
			coordenadas.put(x, new Coordenada(x));
		return coordenadas.get(x);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		for(int i = 0; i < n; i++)
			darCoordenada(sc.nextInt()).agregar(i);
		for(int i = 0; i < n; i++)
			darCoordenada(sc.nextInt()).agregar(i);
		long total = 1;
		long mod = sc.nextLong();
		for(Map.Entry <Integer, Coordenada> e : coordenadas.entrySet())
		{
			total *= e.getValue().contar(mod);
			total %= mod;
		}
		System.out.println(total);
	}
}