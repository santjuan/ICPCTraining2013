import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.PriorityQueue;
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
	
	static void floyd(long[][] grafoInicial, int n)
	{
		for(int k = 0; k < n; k++)
		{
			BitSet b = new BitSet(n);
			for(int i = 0; i < n; i++)
				if(grafoInicial[k][i] < INFINITO)
					b.set(i);
			for(int i = 0; i < n; i++)
			{
				if(grafoInicial[i][k] >= INFINITO)
					continue;
				for(int j = b.nextSetBit(0); j != -1; j = b.nextSetBit(j + 1))
				{
					long cost = grafoInicial[i][k] + grafoInicial[k][j];
					if(cost < grafoInicial[i][j])
					grafoInicial[i][j] = cost;
				}
			}
		}
	}
	
	static long INFINITO = 1000000000L * 1001L;
	
	static class Arista implements Comparable <Arista>
	{
		int otro;
		long costo;
		
		public Arista(int o, long c) 
		{
			otro = o;
			costo = c;
		}

		@Override
		public int compareTo(Arista o) 
		{
			return Long.valueOf(costo).compareTo(o.costo);
		}
	}
	
	
	static long[] dijkstra(int inicial, long[][] costos, int n, BitSet[] aristas)
	{
		long[] cost = new long[n];
		Arrays.fill(cost, INFINITO);
		cost[inicial] = 0;
		PriorityQueue <Arista> pq = new PriorityQueue <Arista> ();
		pq.add(new Arista(inicial, 0));
		while(!pq.isEmpty())
		{
			Arista actual = pq.poll();
			if(cost[actual.otro] != actual.costo)
				continue;
			for(int i = aristas == null ? 0 : aristas[actual.otro].nextSetBit(0); aristas == null ? i < n : i != -1;)
			{
				long siguienteCosto = actual.costo + costos[actual.otro][i];
				if(siguienteCosto < cost[i])
				{
					cost[i] = siguienteCosto;
					pq.add(new Arista(i, cost[i]));
				}
				if(aristas == null) 
					i++;
				else
					i = aristas[actual.otro].nextSetBit(i + 1);
			}
		}
		return cost;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		long[][] grafoInicial = new long[n][n];
		for(long[] a : grafoInicial)
			Arrays.fill(a, INFINITO);
		for(int i = 0; i < n; i++)
			grafoInicial[i][i] = 0;
		int m = sc.nextInt();
		int start = sc.nextInt() - 1;
		int end = sc.nextInt() - 1;
		for(int i = 0; i < m; i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int cost = sc.nextInt();
			grafoInicial[a][b] = Math.min(grafoInicial[a][b], cost);
			grafoInicial[b][a] = Math.min(grafoInicial[b][a], cost);
		}
		BitSet[] puestos = new BitSet[n];
		for(int i = 0; i < n; i++)
		{
			puestos[i] = new BitSet(n);
			for(int j = 0; j < n; j++)
				if(grafoInicial[i][j] != INFINITO)
					puestos[i].set(j);
		}
		long[][] otroGrafo = new long[n][];
		for(int i = 0; i < n; i++)
			otroGrafo[i] = dijkstra(i, grafoInicial, n, puestos);
		grafoInicial = otroGrafo;
		long[] distances = new long[n];
		long[] driveCost = new long[n];
		for(int i = 0; i < n; i++)
		{
			distances[i] = sc.nextLong();
			driveCost[i] = sc.nextLong();
		}
		long[][] grafo = new long[n][n];
		for(long[] a : grafo)
			Arrays.fill(a, INFINITO);
		for(int i = 0; i < n; i++)
			grafo[i][i] = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
			{
				if(i == j)
					continue;
				if(grafoInicial[i][j] <= distances[i])
					grafo[i][j] = driveCost[i];
			}
		long costo = dijkstra(start, grafo, n, null)[end];
		System.out.println(costo >= INFINITO ? -1 : costo);
	}
}