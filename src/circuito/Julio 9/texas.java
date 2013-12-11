import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class texas
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
	
	static class TraductorIds
	{
		int idActual;
		Map <Object, Integer> mapa;
		
		TraductorIds(boolean hash)
		{
			if(hash)
				mapa = new HashMap <Object, Integer> ();
			else
				mapa = new TreeMap <Object, Integer> ();
		}
		
		Integer darId(Object valor)
		{
			Integer actual = mapa.get(valor);
			if(actual == null)
			{
				actual = idActual++;
				mapa.put(valor, actual);
			}
			return actual;
		}
	}
	
	static class TraductorNodos
	{
		int idActual;
		Map <Object, Nodo> mapa;
		
		TraductorNodos(boolean hash)
		{
			if(hash)
				mapa = new HashMap <Object, Nodo> ();
			else
				mapa = new TreeMap <Object, Nodo> ();
		}
		
		Object darNodo(Object valor)
		{
			Nodo actual = mapa.get(valor);
			if(actual == null)
			{
				actual = new Nodo(idActual++);
				mapa.put(valor, actual);
			}
			return actual;
		}
	}
	
	static class Nodo
	{
		int id;
		
		Nodo(int i)
		{
			id = i;
		}
	}
	
	static class ShortestPaths
	{
		Nodo[] nodos;
		int[][] distancia;
		int tam;
		static long INFINITO = (Long.MAX_VALUE >>> 1) - 1;
		
		ShortestPaths(int t)
		{
			tam = t;
			nodos = new Nodo[tam];
			distancia = new int[t][t];
			for(int i = 0; i < tam; i++)
				nodos[i] = new Nodo(i);
		}
		
		
		
		
		Object dijkstra(int desde, int hasta, boolean todos)
		{
			long[] resultado = new long[tam];
			int[] padre = new int[tam];
			Arrays.fill(resultado, INFINITO);
			PriorityQueue <Arista> pq = new PriorityQueue <Arista> ();
			resultado[desde] = 0;
			padre[desde] = -1;
			pq.add(new Arista(nodos[desde], 0));
			while(!pq.isEmpty())
			{
				Arista actual = pq.poll();
				if(actual.costo != resultado[actual.o.id])
					continue;
				if(!todos && actual.o.id == hasta)
					return actual.costo;
				for(int i = 0; i < distancia.length; i++)
				{
					long nuevoCosto = actual.costo + distancia[actual.o.id][i];
					if(nuevoCosto < resultado[i])
					{
						resultado[i] = nuevoCosto;
						padre[i] = actual.o.id;
						pq.add(new Arista(nodos[i], nuevoCosto));
					}
				}
			}
			return todos ? padre : resultado[hasta];
		}
		
		
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		boolean primero = true;
		while(true)
		{
			try
			{
				int n = sc.nextInt();
				if(n == -1) return;
				if(!primero)
					System.out.println();
				primero = false;
				TraductorIds ids = new TraductorIds(true);
				ShortestPaths shortest = new ShortestPaths(n + 2);
				int[][] xs = new int[n + 2][2];
				String[] idsN = new String[n + 2];
				for(int i = 0; i < n; i++)
				{
					xs[i][0] = sc.nextInt();
					xs[i][1] = sc.nextInt();
					idsN[i] = xs[i][0] + " " + xs[i][1];
				}
				xs[n][0] = sc.nextInt();
				xs[n][1] = sc.nextInt();
				xs[n + 1][0] = sc.nextInt();
				xs[n + 1][1] =  sc.nextInt();
				idsN[n] = xs[n][0] + " " + xs[n][1];
				idsN[n + 1] = xs[n + 1][0] + " " + xs[n + 1][1];
				for(int i = 0; i < idsN.length; i++)
				{
					for(int j = 0; j < idsN.length; j++)
					{
						int dist = xs[i][0] - xs[j][0];
						dist *= dist;
						int d2 = xs[i][1] - xs[j][1];
						d2 *= d2;
						dist += d2;
						shortest.distancia[i][j] = dist;
					}
				}
				int[] padres = (int[]) shortest.dijkstra(n, n + 1, true);
				int actual = n + 1;
				LinkedList <Integer> vals = new LinkedList <Integer> ();
				while(padres[actual] != -1)
				{
					if(actual != n + 1)
						vals.add(actual);
					actual = padres[actual];
				}
				Collections.reverse(vals);
				StringBuilder sb = new StringBuilder();
				boolean p = true;
				for(int s : vals)
				{
					if(!p)
						sb.append('\n');
					p = false;
					sb.append(s);
				}
				if(sb.length() == 0)
					sb.append('-');
				System.out.println(sb.toString());
				System.out.flush();
			}
			catch(Exception e)
			{
				return;
			}
		}
	}

}
