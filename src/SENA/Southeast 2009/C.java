import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class C 
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
		ArrayList <Edge> [] G;
		long[] excess;
		int[] dist, count;
		boolean[] active;
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
			G[from].add(new Edge(from, to, cap, 0, G[to].size() + cambio));
			G[to].add(new Edge(to, from, 0, 0, G[from].size() - 1));
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
	
	static int leerNumero(String s) 
	{
		while(s.length() != 1 && s.charAt(0) == '0')
			s = s.substring(1);
		return Integer.parseInt(s);
	}
	
	static int leerHora(String h)
	{
		String[] pedazos = h.split(":");
		int hora = leerNumero(pedazos[0]);
		int minutos = leerNumero(pedazos[1]);
		return hora * 60 + minutos;
	}
	
	static class Guarda
	{
		int tiempo;
		int[] periodosValidos;
		
		Guarda(Scanner sc)
		{
			int k = sc.nextInt();
			tiempo = sc.nextInt();
			final ArrayList <Integer> periodosValidosA = new ArrayList <Integer> ();
			final boolean[] periodos = new boolean[1440];	
			for(int i = 0; i < k; i++)
			{
				int tiempoA = leerHora(sc.next());
				int tiempoB = leerHora(sc.next());
				if(tiempoB <= tiempoA)
				{
					for(int j = tiempoA; j < 1440; j++)
						periodos[j] = true;
					for(int j = 0; j <= tiempoB - 1; j++)
						periodos[j] = true;
				}
				else
					for(int j = tiempoA; j <= tiempoB - 1; j++)
						periodos[j] = true;
			}
			for(int j = 0; j < 48; j++)
			{
				int tiempoA = (j * 30);
				int tiempoB = ((j + 1) * 30);
				boolean ok = true;
				for(int val = tiempoA; val < tiempoB; val++)
				{
					if(!periodos[val])
					{
						ok = false;
						break;
					}
				}
				if(ok)
					periodosValidosA.add(j);
			}
			periodosValidos = new int[periodosValidosA.size()];
			for(int i = 0; i < periodosValidos.length; i++)
				periodosValidos[i] = periodosValidosA.get(i);
		}
	}
	
	static Guarda[] guardas;
	
	static boolean esPosible(int n)
	{
		PushRelabel p = new PushRelabel(guardas.length + 2 + 48);
		for(int i = 0; i < guardas.length; i++)
		{
			p.AddEdge(0, 1 + i, guardas[i].tiempo / 30);
			for(int j : guardas[i].periodosValidos)
				p.AddEdge(1 + i, guardas.length + 1 + j, 1);
		}
		for(int i = 0; i < 48; i++)
			p.AddEdge(guardas.length + 1 + i, guardas.length + 1 + 48, n);
		return p.GetMaxFlow(0, guardas.length + 1 + 48) == n * 48;
	}
	
	static int busquedaBinaria(int a, int b)
	{
		if(a == b)
			return a;
		if(a + 1 == b)
			return esPosible(b) ? b : a;
		int mid = (a + b) >>> 1;
		if(esPosible(mid))
			return busquedaBinaria(mid, b);
		else
			return busquedaBinaria(a, mid - 1);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				return;
			guardas = new Guarda[n];
			for(int i = 0; i < n; i++)
				guardas[i] = new Guarda(sc);
			System.out.println(busquedaBinaria(0, n));
		}
	}
}
