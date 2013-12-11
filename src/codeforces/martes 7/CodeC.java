import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	
	static class Arista
	{
		long costo;
		Nodo otro;

		public Arista(Nodo o, long c) 
		{
			otro = o;
			costo = c;
		}
	}
	
	static long maxCost = 0;
	
	static class Nodo
	{
		ArrayList <Arista> aristas = new ArrayList <Arista> ();
		
		void dfs(long currentCost, Nodo padre)
		{
			maxCost = Math.max(maxCost, currentCost);
			for(Arista a : aristas)
			{
				if(a.otro == padre)
					continue;
				a.otro.dfs(currentCost + a.costo, this);
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		Nodo[] nodos = new Nodo[n];
		for(int i = 0; i < n; i++)
			nodos[i] = new Nodo();
		long total = 0;
		for(int i = 0; i < n - 1; i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			long w = sc.nextLong();
			nodos[a].aristas.add(new Arista(nodos[b], w));
			nodos[b].aristas.add(new Arista(nodos[a], w));
			total += w + w;
		}
		nodos[0].dfs(0, null);
		System.out.println(total - maxCost);
	}

}
