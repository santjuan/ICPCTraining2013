import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class UtpD 
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
		
		public Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		public int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
		
		public char[][] nextGrid(int r) 
		{
			char[][] grid = new char[r][];
			for(int i = 0; i < r; i++)
				grid[i] = next().toCharArray();
			return grid;
		}
		
		public static <T> T fill(T arreglo, int val)
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
		
		<T> T[] nextObjectArray(Class <T> clazz, int size)
		{
			@SuppressWarnings("unchecked")
			T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
			for(int c = 0; c < 3; c++)
			{
				Constructor <T> constructor;
				try 
				{
					if(c == 0)
						constructor = clazz.getDeclaredConstructor(Scanner.class, Integer.TYPE);
					else if(c == 1)
						constructor = clazz.getDeclaredConstructor(Scanner.class);
					else
						constructor = clazz.getDeclaredConstructor();
				} 
				catch(Exception e)
				{
					continue;
				}
				try
				{
					for(int i = 0; i < result.length; i++)
					{
						if(c == 0)
							result[i] = constructor.newInstance(this, i);
						else if(c == 1)
							result[i] = constructor.newInstance(this);
						else
							result[i] = constructor.newInstance();	
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
				return result;
			}
			throw new RuntimeException("Constructor not found");
		}
		
		public void printLine(int... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(long... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(double... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(int prec, double... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.printf("%." + prec + "f", vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.printf(" %." + prec + "f", vals[i]);
				System.out.println();
			}
		}
		
		public Collection <Integer> wrap(int[] as)
		{
			ArrayList <Integer> ans = new ArrayList <Integer> ();
			for(int i : as)
				ans.add(i);
			return ans;
		}
		
		public int[] unwrap(Collection <Integer> collection)
		{
			int[] vals = new int[collection.size()];
			int index = 0;
			for(int i : collection) vals[index++] = i;
			return vals;
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
		
		
		Object dijkstra(int desde, int hasta, boolean todos)
		{
			long[] resultado = new long[tam];
			Arrays.fill(resultado, INFINITO);
			PriorityQueue <Arista> pq = new PriorityQueue <Arista> ();
			resultado[desde] = 0;
			pq.add(new Arista(nodos[desde], 0));
			while(!pq.isEmpty())
			{
				Arista actual = pq.poll();
				if(actual.costo != resultado[actual.o.id])
					continue;
				if(!todos && actual.o.id == hasta)
					return actual.costo;
				for(Arista a : actual.o.aristas)
				{
					long nuevoCosto = actual.costo + a.costo;
					if(nuevoCosto < resultado[a.o.id])
					{
						resultado[a.o.id] = nuevoCosto;
						pq.add(new Arista(a.o, nuevoCosto));
					}
				}
			}
			return todos ? resultado : resultado[hasta];
		}
		
		long[][] floydWarshall(boolean intentarDijkstra)
		{
			if(intentarDijkstra)
			{
				long[][] resultado = new long[tam][];
				for(int i = 0; i < tam; i++)
					resultado[i] = (long[]) dijkstra(i, 0, true);
				return resultado;
			}
			else
			{
				long[][] adjacencia = matrizAdjacencia();
				if(INFINITO + INFINITO < INFINITO)
				{
					for(int k = 0; k < tam; k++)
						for(int i = 0; i < tam; i++)
							for(int j = 0; j < tam; j++)
							{
								if(adjacencia[i][k] < INFINITO && adjacencia[k][j] < INFINITO)
								{
									long next = adjacencia[i][k] + adjacencia[k][j];
									if(next < adjacencia[i][j])
										adjacencia[i][j] = next;
								}
							}
				}
				else
				{
					for(int k = 0; k < tam; k++)
						for(int i = 0; i < tam; i++)
							for(int j = 0; j < tam; j++)
							{
								long next = adjacencia[i][k] + adjacencia[k][j];
								if(next < adjacencia[i][j])
									adjacencia[i][j] = next;
							}
				}
				return adjacencia;
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 1; caso <= t; caso++)
		{
			int n = sc.nextInt() + 2;
			int[][] distances = sc.nextIntMatrix(n, 2);
			ShortestPaths paths = new ShortestPaths(n);
			for(int i = 0; i < n; i++)
			{
				for(int j = i + 1; j < n; j++)
				{
					int dist = Math.abs(distances[i][0] - distances[j][0]) + Math.abs(distances[i][1] - distances[j][1]);
					if(dist <= 1000)
					{
						paths.agregarArista(i, j, 1);
						paths.agregarArista(j, i, 1);
					}
					
				}
			}
			System.out.println(paths.floydWarshall(false)[0][n - 1] < ShortestPaths.INFINITO ? "happy" : "sad");
		}		
	}
}