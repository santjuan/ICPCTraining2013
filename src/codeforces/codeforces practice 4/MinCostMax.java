import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class MinCostMax
{
	
	
	static class Scanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");

		public String nextLine() {
			try {
				return br.readLine();
			} catch (Exception e) {
				throw (new RuntimeException());
			}
		}

		public String next() {
			while (!st.hasMoreTokens()) {
				String l = nextLine();
				if (l == null)
					return null;
				st = new StringTokenizer(l);
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public int[] nextIntArray(int n) {
			int[] res = new int[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextInt();
			return res;
		}

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}

		public void sortIntArray(int[] array) {
			Integer[] vals = new Integer[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public void sortLongArray(long[] array) {
			Long[] vals = new Long[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public void sortDoubleArray(double[] array) {
			Double[] vals = new Double[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
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
		long[][] cap, flow, cost;
		boolean[] found;
		long[] dist, pi, width;
		Pair[] dad;

		MinCostMaxFlow(int N)
		{
			this.N = N;
			cap = new long[N][N];
			flow = new long[N][N];
			cost = new long[N][N];
			dad = new Pair[N];
			for(int i = 0; i < N; i++)
				dad[i] = new Pair();
			found = new boolean[N];
			dist = new long[N];
			pi = new long[N];
			width = new long[N];
		}

		void AddEdge(int from, int to, long cap, long cost) {
			this.cap[from][to] = cap;
			this.cost[from][to] = cost;
		}

		void Relax(int s, int k, long cap, long cost, int dir) {
			long val = dist[s] + pi[s] - pi[k] + cost;
			if (cap != 0 && val < dist[k]) {
				dist[k] = val;
				dad[k].first = s;
				dad[k].second = dir;
				width[k] = Math.min(cap, width[s]);
			}
		}

		long Dijkstra(int s, int t) {
			Arrays.fill(found, false);
			Arrays.fill(dist, INF);
			Arrays.fill(width, 0);
			dist[s] = 0;
			width[s] = INF;
			while (s != -1) {
				int best = -1;
				found[s] = true;
				for (int k = 0; k < N; k++) {
					if (found[k]) continue;
					Relax(s, k, cap[s][k] - flow[s][k], cost[s][k], 1);
					Relax(s, k, flow[k][s], -cost[k][s], -1);
					if (best == -1 || dist[k] < dist[best]) best = k;
				}
				s = best;
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
						flow[dad[x].first][x] += amt;
						totcost += amt * cost[dad[x].first][x];
					} else {
						flow[x][dad[x].first] -= amt;
						totcost -= amt * cost[x][dad[x].first];
					}
				}
			}
			return new long[]{totflow, totcost};
		}
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		String objetivo = sc.next();
		int n = sc.nextInt();
		String[] strings = new String[n];
		int[] limites = new int[n];
		for(int i = 0; i < n; i++)
		{
			strings[i] = sc.next();
			limites[i] = sc.nextInt();
		}
		int actual = 0;
		int source = actual++;
		int sink = actual++;
		int[] nodos = new int[n];
		for(int i = 0; i < nodos.length; i++)
			nodos[i] = actual++;
		int[] nodosLetras = new int[26];
		for(int i = 0; i < nodosLetras.length; i++)
			nodosLetras[i] = actual++;
		MinCostMaxFlow grafo = new MinCostMaxFlow(actual);
		for(int i = 0; i < strings.length; i++)
		{
			grafo.AddEdge(source, nodos[i], limites[i], 0);
			for(int j = 0; j < 26; j++)
			{
				int cuenta = 0;
				for(char c : strings[i].toCharArray())
					if(c == j + 'a')
						cuenta++;
				grafo.AddEdge(nodos[i], nodosLetras[j], cuenta, (i + 1));
			}
		}
		for(int i = 0; i < strings.length; i++)
		{
			for(int j = 0; j < 26; j++)
			{
				int cuenta = 0;
				for(char c : objetivo.toCharArray())
					if(c == j + 'a')
						cuenta++;
				grafo.AddEdge(nodosLetras[j], sink, cuenta, 0);
			}
		}
		long[] mFlow = grafo.GetMaxFlow(source, sink);
		if(mFlow[0] == objetivo.length())
			System.out.println(mFlow[1]);
		else
			System.out.println(-1);
			
	}
}