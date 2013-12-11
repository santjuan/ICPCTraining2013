import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CodeA 
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
	
	static class Edge 
	{
		int from, to, index;
		long cap, flow;

		Edge(int fromi, int toi, long capi, long flowi, int indexi)
		{
			from = fromi;
			to = toi;
			cap = capi;
			flow = flowi;
			index = indexi;
		}
	}

	static class PushRelabel 
	{
		int N;
		int ans;
		ArrayList <Edge> [] G;
		long[] excess;
		int[] dist, count;
		boolean[] active;
		Edge[][] adjacency;
		ArrayDeque <Integer> Q = new ArrayDeque <Integer> ();

		@SuppressWarnings("unchecked")
		PushRelabel(int N1)
		{
			N = N1;
			G = new ArrayList[N];
			for(int i = 0; i < N; i++)
				G[i] = new ArrayList <Edge> ();
			excess = new long[N];
			dist = new int[N];
			active = new boolean[N];
			count = new int[2 * N];
			adjacency = new Edge[N][N];
		}
		
		void clear(int n)
		{
			N = n;
			for(int i = 0; i < N; i++)
				G[i].clear();
			for(int i = 0; i < N; i++)
			{
				excess[i] = 0;
				dist[i] = 0;
				active[i] = false;
			}
			int dosN = 2 * N;
			for(int i = 0; i < dosN; i++)
				count[i] = 0;
		}

		void AddEdge(int from, int to, int cap) 
		{
			int cambio = from == to ? 1 : 0;
			Edge a = new Edge(from, to, cap, 0, G[to].size() + cambio);
			G[from].add(a);
			Edge b = new Edge(to, from, 0, 0, G[from].size() - 1);
			G[to].add(b);
			adjacency[from][to] = a;
			adjacency[to][from] = b;
		}

		void Enqueue(int v) 
		{ 
			if (!active[v] && excess[v] > 0)
			{ 
				active[v] = true;
				Q.add(v); 
			} 
		}

		void Push(Edge e) 
		{
			long amt = Math.min(excess[e.from], e.cap - e.flow);
			if(dist[e.from] <= dist[e.to] || amt == 0) 
				return;
			e.flow += amt;
			G[e.to].get(e.index).flow -= amt;
			excess[e.to] += amt; 
			excess[e.from] -= amt;
			Enqueue(e.to);
		}

		void Gap(int k) 
		{
			for(int v = 0; v < N; v++) 
			{
				if(dist[v] < k) 
					continue;
				count[dist[v]]--;
				dist[v] = Math.max(dist[v], N + 1);
				count[dist[v]]++;
				Enqueue(v);
			}
		}

		void Relabel(int v) 
		{
			count[dist[v]]--;
			dist[v] = 2 * N;
			for (Edge e : G[v]) 
				if (e.cap - e.flow > 0)
					dist[v] = Math.min(dist[v], dist[e.to] + 1);
			count[dist[v]]++;
			Enqueue(v);
		}

		void Discharge(int v) 
		{
			for(Edge e : G[v])
			{
				if(excess[v] <= 0)
					break;
				Push(e);
			}
			if(excess[v] > 0) 
			{
				if(count[dist[v]] == 1) 
					Gap(dist[v]); 
				else
					Relabel(v);
			}
		}

		long GetMaxFlow(int s, int t)
		{
			count[0] = N - 1;
			count[N] = 1;
			dist[s] = N;
			active[s] = active[t] = true;
			for (Edge e : G[s]) 
			{
				excess[s] += e.cap;
				Push(e);
			}
			while (!Q.isEmpty()) 
			{
				int v = Q.poll();
				active[v] = false;
				Discharge(v);
			}
			long totflow = 0;
			for (Edge e : G[s]) 
				totflow += e.flow;
			return totflow;
		}
	}
	
	static class Partido
	{
		int a;
		int b;
		
		Partido(int aa, int bb)
		{
			a = aa;
			b = bb;
		}
	}
	
	static int[][][] juegos;
	static Partido[] porJugar;
	static int[] puntos; 
	static int p;
	
	static PushRelabel generarGrafo(int limite)
	{
		int primerPartido = 0;
		int primerJugador = porJugar.length;
		int source = primerJugador + puntos.length;
		int sink = source + 1;
		PushRelabel pr = new PushRelabel(sink + 1);
		for(int i = 0; i < porJugar.length; i++)
		{
			pr.AddEdge(source, i + primerPartido, 1);
			pr.AddEdge(i + primerPartido, porJugar[i].a + primerJugador, 1);
			pr.AddEdge(i + primerPartido, porJugar[i].b + primerJugador, 1);
		}
		for(int i = 0; i < puntos.length; i++)
			if(i != p)
				pr.AddEdge(i + primerJugador, sink, limite - puntos[i]);
		pr.ans = limite;
		if(pr.GetMaxFlow(source, sink) == porJugar.length)
			return pr;
		else
			return null;
	}
	
	
	static PushRelabel busquedaBinaria(int a, int b)
	{
		if(a == b)
			return generarGrafo(a);
		int mid = (a + b) >>> 1;
		if(generarGrafo(mid) == null)

			return busquedaBinaria(mid + 1, b);
		else
			return busquedaBinaria(a, mid);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int casos = sc.nextInt();
		for(int caso = 0; caso < casos; caso++)
		{
			if(caso != 0)
				System.out.println();
			int m = sc.nextInt();
			p = sc.nextInt() - 1;
			juegos = new int[m][m][2]; 
			puntos = new int[m];
			ArrayList <Partido> porJugarA = new ArrayList <Partido> ();
			for(int i = 0; i < m; i++)
			{
				for(int j = 0; j < m; j++)
				{
					String e = sc.next();
					int val0 = e.charAt(0) == '-' ? -1 : e.charAt(0) == '1' ? 1 : 0;
					int val1 = e.charAt(1) == '-' ? -1 : e.charAt(1) == '1' ? 1 : 0;
					if(i == p && val0 == -1 && i != j)
						val0 = 1;
					if(i == p && val1 == -1 && i != j)
						val1 = 1;
					if(j == p && val0 == -1 && i != j)
						val0 = 0;
					if(j == p && val1 == -1 && i != j)
						val1 = 0;
					juegos[i][j][0] = val0;
					juegos[i][j][1] = val1;
					if(val0 != -1)
						puntos[i] += val0;
					if(val1 != -1)
						puntos[i] += val1;
					if(val0 == -1 && i < j)
						porJugarA.add(new Partido(i, j));
					if(val1 == -1 && i < j)
						porJugarA.add(new Partido(i, j));
				}
			}
			porJugar = porJugarA.toArray(new Partido[0]);
			int max = 0;
			for(int i = 0; i < puntos.length; i++)
				if(i == p)
					continue;
				else
					max = Math.max(max, puntos[i]);
			PushRelabel ans = null;
			if(max <= puntos[p])
				ans = busquedaBinaria(max, puntos[p]);
			if(ans == null)
				System.out.println("Player " + (p + 1) + " can't win!");
			else
			{
				System.out.println("Player " + (p + 1) + " can win with " + (puntos[p] - ans.ans) + " point(s).");
				System.out.println();
				int primerPartido = 0;
				int primerJugador = porJugar.length;
				int indice = 0;
				for(Partido x : porJugar)
				{
					boolean quien = ans.adjacency[indice + primerPartido][x.a + primerJugador].flow > 0;
					if(juegos[x.a][x.b][0] == -1)
					{
						juegos[x.a][x.b][0] = quien ? 1 : 0;
						juegos[x.b][x.a][0] = quien ? 0 : 1;
					}
					else
					{
						juegos[x.a][x.b][1] = quien ? 1 : 0;
						juegos[x.b][x.a][1] = quien ? 0 : 1;
					}
					indice++;
				}
				for(int i = 0; i < puntos.length; i++)
				{
					puntos[i] = 0;
					for(int j = 0; j < puntos.length; j++)
					{
						for(int k = 0; k < 2; k++)
						{
							if(juegos[i][j][k] != -1)
								puntos[i] += juegos[i][j][k];
							if(juegos[i][j][k] == -1)
								System.out.print('-');
							else
								System.out.print(juegos[i][j][k]);
						}
						System.out.print(' ');
					}
					System.out.println(": " + puntos[i]);
				}
			}
		}
	}

}
