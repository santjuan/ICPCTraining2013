import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
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
	
	static class Arista implements Comparable <Arista>
	{
		Nodo o;
		long costo;
		
		
		Arista(Nodo n, long c)
		{
			o = n;
			costo = c;
		}
		
		@Override
		public int compareTo(Arista o) 
		{
			return costo < o.costo ? -1 : costo > o.costo ? 1 : 0;
		}
	}
	
	static class Nodo
	{
		int id;
		ArrayList <Arista> aristas = new ArrayList <Arista> ();
		
		Nodo(int i)
		{
			id = i;
		}
	}
	
	static class ShortestPaths
	{
		Nodo[] nodos;
		int tam;
		static long INFINITO = (Long.MAX_VALUE >>> 1) - 1;
		
		ShortestPaths(int t)
		{
			tam = t;
			nodos = new Nodo[tam];
			for(int i = 0; i < tam; i++)
				nodos[i] = new Nodo(i);
		}
		
		void agregarArista(int desde, int hasta, long costo)
		{
			nodos[desde].aristas.add(new Arista(nodos[hasta], costo));
			nodos[hasta].aristas.add(new Arista(nodos[desde], costo));
		}
		
		long[][] matrizAdjacencia()
		{
			long[][] resultado = new long[tam][tam];
			for(long[] l : resultado)
				Arrays.fill(l, INFINITO);
			for(int i = 0; i < tam; i++)
			{
				resultado[i][i] = 0;
				for(Arista a : nodos[i].aristas)
				{
					if(a.costo < resultado[i][a.o.id])
						resultado[i][a.o.id] = a.costo;
				}
			}
			return resultado;
		}
	
		Object[] dijkstra(int desde)
		{
			long[] resultado = new long[tam];
			Arrays.fill(resultado, INFINITO);
			int[] padre = new int[tam];
			padre[desde] = -1;
			PriorityQueue <Arista> pq = new PriorityQueue <Arista> ();
			resultado[desde] = 0;
			pq.add(new Arista(nodos[desde], 0));
			while(!pq.isEmpty())
			{
				Arista actual = pq.poll();
				if(actual.costo != resultado[actual.o.id])
					continue;
				for(Arista a : actual.o.aristas)
				{
					long nuevoCosto = actual.costo + a.costo;
					if(nuevoCosto < resultado[a.o.id])
					{
						resultado[a.o.id] = nuevoCosto;
						padre[a.o.id] = actual.o.id;
						pq.add(new Arista(a.o, nuevoCosto));
					}
				}
			}
			return new Object[]{resultado, padre};
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int s = sc.nextInt() - 1;
		ShortestPaths shortest = new ShortestPaths(n);
		for(int i = 0; i < m; i++)
			shortest.agregarArista(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextLong());
		int l = sc.nextInt();
		int cuenta = 0;
		Object[] ans = shortest.dijkstra(s);
		long[] resultado = (long[]) ans[0];
		int[] padre = (int[]) ans[1];
		for(int i = 0; i < n; i++)
		{
			if(resultado[i] == l)
				cuenta++;
			if(resultado[i] < l)
				for(Arista a : shortest.nodos[i].aristas)
				{
					long total = resultado[i] + a.costo;
					if(total > l)
					{
						long donde = (l - resultado[i]);
						long dondeAlla = a.costo - donde;
						long costoAlla = resultado[a.o.id] + dondeAlla;
						if(costoAlla < l) continue;
						if(costoAlla == l && i > a.o.id) continue;
						cuenta++;
					}
				}
		}
		System.out.println(cuenta);
	}
}