import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class job 
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
	
	static class Edge
	{
		int to;
		int cap;
		int flow;
		int cost;
		Edge other;
		
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
	
	static Edge[][] allEdges = new Edge[300][300];
	
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
		Edge[][] adjacentAll;

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
			for(int i = 0; i < N; i++)
				adjacents[i] = new ArrayList <Edge> ();
			adjacentAll = allEdges;
		}

		Edge newEdge(int from, int to)
		{
			Edge nuevo = new Edge(to);
			adjacents[from].add(nuevo);
			adjacentAll[from][to] = nuevo;
			Edge par = new Edge(from);
			adjacents[to].add(par);
			nuevo.other = par;
			par.other = nuevo;
			adjacentAll[to][from] = par;
			return nuevo;
		}
		
		void AddEdge(int from, int to, int cap, int cost) {
			Edge edge = newEdge(from, to);
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
				if(found[s]) continue;
				found[s] = true;
				for (Edge e : adjacents[s]) {
					int k = e.to;
					if (found[k]) continue;
					Edge inverse = e.other;
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
						adjacentAll[dad[x].first][x].flow += (int) amt;
						totcost += amt * adjacentAll[dad[x].first][x].cost;
					} else {
						adjacentAll[x][dad[x].first].flow += (int) -amt;
						totcost -= amt * adjacentAll[x][dad[x].first].cost;
					}
				}
			}
			return new long[]{totflow, totcost};
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n == 0 && m == 0) return;
			int[] positions = new int[n];
			for(int i = 0; i < n; i++) positions[i] = sc.nextInt();
			int[][] students = new int[m][5];
			for(int i = 0; i < m; i++) for(int j = 0; j < 5; j++) students[i][j] = sc.nextInt();
			int[] idsJobs = new int[n];
			int idActual = 0;
			int source = idActual++;
			int sink = idActual++;
			for(int i = 0; i < n; i++)
				idsJobs[i] = idActual++;
			int[] idsStudents = new int[m];
			for(int i = 0; i < m; i++)
				idsStudents[i] = idActual++;
			MinCostMaxFlow mcmf = new MinCostMaxFlow(idActual);
			for(int i = 0; i < m; i++)
				mcmf.AddEdge(source, idsStudents[i], 1, 0);
			for(int i = 0; i < n; i++)
				mcmf.AddEdge(idsJobs[i], sink, positions[i], 0);
			for(int i = 0; i < m; i++)
			{
				int baseCost = students[i][0] == 1 ? 4 : students[i][0] == 2 ? 8 : 12;
				for(int j = 0; j < 4; j++)
				{
					int realCost = 12 - baseCost;
					mcmf.AddEdge(idsStudents[i], idsJobs[students[i][j + 1]], 1, realCost);
					baseCost--;
				}
			}
			int ans = (int) (12 * m - mcmf.GetMaxFlow(source, sink)[1]);
			System.out.println(ans);
		}
	}
}