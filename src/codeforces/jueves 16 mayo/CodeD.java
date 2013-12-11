import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CodeD
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
	}
	
	static class Edge
	{
		int to;
		int cap;
		int flow;
		int cost;
		
		public Edge(int t) 
		{
			to = t;
		}
	}
	
	static class Entry implements Comparable <Entry>
	{
		int node;
		int cost;
		
		public Entry(int n, int c)
		{
			node = n;
			cost = c;
		}

		@Override
		public int compareTo(Entry o)
		{
			return cost < o.cost ? -1 : (cost > o.cost ? 1 : 0);
		}
	}
	
	static class MinCostMaxFlow 
	{
		static final long INF = Long.MAX_VALUE / 4;
		
		static class Pair
		{
			int first;
			int second;
		}
		
		int N;
		boolean[] found;
		long[] dist, pi, width;
		Pair[] dad;
		ArrayList <Edge> [] adjacents;
		HashMap <Integer, Integer> [] adjacentsHash;

		@SuppressWarnings("unchecked")
		MinCostMaxFlow(int N)
		{
			this.N = N;
			dad = new Pair[N];
			for(int i = 0; i < N; i++)
				dad[i] = new Pair();
			found = new boolean[N];
			dist = new long[N];
			pi = new long[N];
			width = new long[N];
			adjacents = new ArrayList[N];
			adjacentsHash = new HashMap[N];
			for(int i = 0; i < N; i++)
			{
				adjacents[i] = new ArrayList <Edge> ();
				adjacentsHash[i] = new HashMap <Integer, Integer> ();
			}
		}

		
		Edge getEdge(int from, int to)
		{
			if(!adjacentsHash[from].containsKey(to))
			{
				adjacentsHash[from].put(to, adjacents[from].size());
				adjacents[from].add(new Edge(to));
			}
			return adjacents[from].get(adjacentsHash[from].get(to));
		}
		
		void AddEdge(int from, int to, int cap, int cost) {
			Edge edge = getEdge(from, to);
			edge.cap = cap;
			edge.cost = cost;
		}

		void Relax(int s, int k, long cap, long cost, int dir) {
			long val = dist[s] + pi[s] - pi[k] + cost;
			if (cap != 0 && val < dist[k]) {
				dist[k] = val;
				dad[k].first = s;
				dad[k].second = dir;
				width[k] = Math.min(cap, width[s]);
				pq.add(new Entry(k, (int) dist[k]));
			}
		}
		
		PriorityQueue <Entry> pq = new PriorityQueue <Entry> ();

		long Dijkstra(int s, int t) {
			Arrays.fill(found, false);
			Arrays.fill(dist, INF);
			Arrays.fill(width, 0);
			dist[s] = 0;
			width[s] = INF;
			pq.clear();
			pq.add(new Entry(s, 0));
			while (true) {
				while(!pq.isEmpty() && dist[pq.peek().node] != pq.peek().cost)
					pq.poll();
				if(pq.isEmpty())
					break;
				s = pq.poll().node;
				found[s] = true;
				for (Edge e : adjacents[s]) {
					int k = e.to;
					if (found[k]) continue;
					Edge inverse = getEdge(k, s);
					Relax(s, k, e.cap - e.flow, e.cost, 1);
					Relax(s, k, inverse.flow, -inverse.cost, -1);
				}
			}
			for (int k = 0; k < N; k++)
				pi[k] = Math.min(pi[k] + dist[k], INF);
			return width[t];
		}

		long[] GetMaxFlow(int s, int t) {
			long totflow = 0, totcost = 0;
			long amt;
			while ((amt = Dijkstra(s, t)) != 0) {
				totflow += amt;
				for (int x = t; x != s; x = dad[x].first) {
					if (dad[x].second == 1) {
						getEdge(dad[x].first, x).flow += (int) amt;
						totcost += amt * getEdge(dad[x].first, x).cost;
					} else {
						getEdge(x, dad[x].first).flow += (int) -amt;
						totcost -= amt * getEdge(x, dad[x].first).cost;
					}
				}
			}
			return new long[]{totflow, totcost};
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int k = sc.nextInt();
		TreeSet <Integer> importantes = new TreeSet <Integer> ();
		int[][] entrada = new int[n][3];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < 3; j++)
			{
				entrada[i][j] = sc.nextInt();
				importantes.add(entrada[i][0]);
			}
		importantes.add(2000000010);
		ArrayList <Integer> todosImportantes = new ArrayList <Integer> (importantes);
		MinCostMaxFlow grafo = new MinCostMaxFlow(todosImportantes.size() + 2 + n);
		int source = todosImportantes.size();
		int sink = todosImportantes.size() + 1;
		grafo.AddEdge(source, 0, k, 1000000);
		for(int i = 0; i < todosImportantes.size() - 1; i++)
			grafo.AddEdge(i, i + 1, k, 1000000);
		grafo.AddEdge(todosImportantes.size() - 1, sink, k, 1000000);
		for(int i = 0; i < n; i++)
		{
			int start = Collections.binarySearch(todosImportantes, entrada[i][0]);
			int end = Collections.binarySearch(todosImportantes, importantes.ceiling(entrada[i][0] + entrada[i][1]));
			int cost = -entrada[i][2];
			int realCost = 1000000;
			realCost *= end - start;
			realCost += cost;
			int nodoDummy = todosImportantes.size() + 2 + i;
			grafo.AddEdge(start, nodoDummy, 1, 0);
			grafo.AddEdge(nodoDummy, end, 1, realCost);
		}
		long[] ans = grafo.GetMaxFlow(source, sink);
		for(int i = 0; i < n; i++)
		{
			int nodoDummy = todosImportantes.size() + 2 + i;
			int end = Collections.binarySearch(todosImportantes, importantes.ceiling(entrada[i][0] + entrada[i][1]));
			boolean saturada = grafo.getEdge(nodoDummy, end).flow > 0;
			if(i != 0)
				System.out.print(" ");
			System.out.print(saturada ? 1 : 0);
		}
		System.out.println();
	}

}
