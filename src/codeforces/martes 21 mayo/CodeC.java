import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class CodeC 
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
		Edge[][] adjacencia;
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
			adjacencia = new Edge[N][N];
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
			adjacencia[from][to] = G[from].get(G[from].size() - 1);
			G[to].add(new Edge(to, from, 0, 0, G[from].size() - 1));
			adjacencia[to][from] = G[to].get(G[to].size() - 1);
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
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int caso = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				return;
			int m = sc.nextInt();
			int[][] micos = new int[n][3];
			TreeSet <Integer> significativos = new TreeSet <Integer> ();
			for(int i = 0; i < n; i++)
				for(int j = 0; j < 3; j++)
					micos[i][j] = sc.nextInt();
			for(int i = 0; i < n; i++)
			{	
				significativos.add(micos[i][1]);
				significativos.add(micos[i][2]);
			}
			significativos.add(50001);
			ArrayList <Integer> todos = new ArrayList <Integer> (significativos);
			final int[] idsMicos = new int[n];
			final int[] idsInvervalos = new int[todos.size()];
			int tam = 0;
			int source = tam++;
			int sink = tam++;
			for(int i = 0; i < n; i++)
				idsMicos[i] = tam++;
			for(int i = 0; i < todos.size() - 1; i++)
				idsInvervalos[i] = tam++;
			final PushRelabel maxFlow = new PushRelabel(tam);
			long total = 0;
			for(int i = 0; i < n; i++)
			{
				maxFlow.AddEdge(source, idsMicos[i], micos[i][0]);
				total += micos[i][0];
				for(int j = 0; j < todos.size() - 1; j++)
				{
					int a = todos.get(j);
					int b = todos.get(j + 1);
					if(a >= micos[i][1] && b <= micos[i][2])
						maxFlow.AddEdge(idsMicos[i], idsInvervalos[j], b - a);
				}
			}
			for(int j = 0; j < todos.size() - 1; j++)
				maxFlow.AddEdge(idsInvervalos[j], sink, (todos.get(j + 1) - todos.get(j)) * m);
			long mF = maxFlow.GetMaxFlow(source, sink);
			if(mF == total)
			{
				System.out.println("Case " + caso++ + ": Yes");
				TreeSet <Integer> [] segundosMicos = new TreeSet[n];
				for(int i = 0; i < n; i++)
					segundosMicos[i] = new TreeSet <Integer> ();
				for(int j = 0; j < todos.size() - 1; j++)
				{
					int a = todos.get(j);
					int b = todos.get(j + 1);
					if(a == b)
						continue;
					ArrayList <Integer> micosVivos = new ArrayList <Integer> ();
					for(int i = 0; i < n; i++)
					{
						Edge e = maxFlow.adjacencia[idsMicos[i]][idsInvervalos[j]];
						if(e == null || e.flow <= 0)
							continue;
						micosVivos.add(i);
					}
					final int intervaloActual = j;
					for(int k = a; k < b; k++)
					{
						int faltantes = m;
						Collections.sort(micosVivos, new Comparator <Integer> () {

							@Override
							public int compare(Integer i, Integer j) 
							{
								Edge eI = maxFlow.adjacencia[idsMicos[i]][idsInvervalos[intervaloActual]];
								Edge eJ = maxFlow.adjacencia[idsMicos[j]][idsInvervalos[intervaloActual]];
								return (int) (eJ.flow - eI.flow);
							}
						});
						for(int i : micosVivos)
						{
							if(faltantes <= 0)
								break;
							Edge e = maxFlow.adjacencia[idsMicos[i]][idsInvervalos[j]];
							if(e == null || e.flow <= 0)
								continue;
							e.flow--;
							segundosMicos[i].add(k);
							faltantes--;
						}
					}
				}
				for(int i = 0; i < n; i++)
				{
					if(segundosMicos[i].size() != micos[i][0])
						throw new RuntimeException();
					if(micos[i][0] == 0)
					{
						System.out.println("0");
						continue;
					}
					int primero = segundosMicos[i].pollFirst();
					int actual = primero;
					ArrayList <String> res = new ArrayList <String> ();
					while(!segundosMicos[i].isEmpty())
					{
						if(segundosMicos[i].first().intValue() == actual + 1)
						{
							actual++;
							segundosMicos[i].pollFirst();
						}
						else
						{
							res.add("(" + primero + "," + (actual + 1) + ")");
							primero = actual = segundosMicos[i].pollFirst();
						}
					}
					res.add("(" + primero + "," + (actual + 1) + ")");
					System.out.print(res.size());
					StringBuilder sb = new StringBuilder();
					for(String s : res)
						sb.append(' ').append(s);
					System.out.println(sb.toString());
				}
			}
			else
				System.out.println("Case " + caso++ + ": No");
		}
	}

}
