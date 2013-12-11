import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LiveD 
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
	
	static int[][] regalosAristas;
	static long[] costoRegalos;
	static boolean[][] conexion;
	static int nAmigos;
	static int nRegalos;
	
	static long[] darMejor(boolean[] amigosUtilizados, boolean[] regalosUtilizados)
	{
		int nNodos = 0;
		int source = nNodos++;
		int sink = nNodos++;
		int[] amigos = new int[nAmigos];
		for(int i = 0; i < nAmigos; i++)
			if(!amigosUtilizados[i])
				amigos[i] = nNodos++;
		int[] regalos = new int[nRegalos];
		for(int i = 0; i < nRegalos; i++)
			if(!regalosUtilizados[i])
				regalos[i] = nNodos++;
		MinCostMaxFlow m = new MinCostMaxFlow(nNodos);
		for(int i = 0; i < nRegalos; i++)
		{
			if(regalosUtilizados[i])
				continue;
			m.AddEdge(regalos[i], sink, 1, costoRegalos[i]);
			for(int a : regalosAristas[i])
			{
				if(amigosUtilizados[a])
					continue;
				m.AddEdge(amigos[a], regalos[i], 1, 0);
			}
		}
		for(int i = 0; i < nAmigos; i++)
			if(!amigosUtilizados[i])
				m.AddEdge(source, amigos[i], 1, 0);
		return m.GetMaxFlow(source, sink);
	}
	
	
	static String generarLexicografica(boolean[] amigosUtilizados, boolean[] regalosUtilizados, int amigoActual, long costoOptimo, String acum)
	{
		if(amigoActual == nAmigos)
			return acum;
		amigosUtilizados[amigoActual] = true;
		for(int i = 0; i < nRegalos; i++)
		{
			if(conexion[amigoActual][i] && !regalosUtilizados[i])
			{
				long cEsperado = costoOptimo - costoRegalos[i];
				long fEsperado = nAmigos - amigoActual - 1;
				regalosUtilizados[i] = true;
				long[] ans = darMejor(amigosUtilizados, regalosUtilizados);
				if(ans[0] == fEsperado && ans[1] == cEsperado)
					return generarLexicografica(amigosUtilizados, regalosUtilizados, amigoActual + 1, cEsperado, acum + " " + i);
				regalosUtilizados[i] = false;
			}
		}
		return null;
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int tc = sc.nextInt();
		for(int caso = 0; caso < tc; caso++)
		{
			nAmigos = sc.nextInt();
			nRegalos = sc.nextInt();
			costoRegalos = new long[nRegalos];
			regalosAristas = new int[nRegalos][];
			conexion = new boolean[nAmigos][nRegalos];
			for(int i = 0; i < nRegalos; i++)
			{
				costoRegalos[i] = sc.nextLong();
				int f = sc.nextInt();
				regalosAristas[i] = sc.nextIntArray(f);
				for(int j : regalosAristas[i])
					conexion[j][i] = true;
			}
			boolean[] amigosUtilizados = new boolean[nAmigos];
			boolean[] regalosUtilizados = new boolean[nRegalos];
			long[] mejor = darMejor(amigosUtilizados, regalosUtilizados);
			if(mejor[0] != nAmigos)
				System.out.println("No solution.");
			else
				System.out.println(generarLexicografica(amigosUtilizados, regalosUtilizados, 0, mejor[1], mejor[1] + ""));
		}
	}
}