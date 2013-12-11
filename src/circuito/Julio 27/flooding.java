import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class flooding 
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

	static class FastArrayDeque
	{
		final int[] queue;
		int head, tail;
		
		FastArrayDeque(int size)
		{
			queue = new int[size];
			clear();
		}
		
		
		void add(int val)
		{
			queue[++tail] = val;
		}
		
		int poll()
		{
			return queue[head++];
		}
		
		void clear()
		{
			head = 0; tail = -1;
		}
		
		boolean isEmpty()
		{
			return head > tail;
		}
	}
	
	static class Edge 
	{
		int to, index, cap;

		Edge(int toi, int capi, int indexi)
		{
			to = toi;
			cap = capi;
			index = indexi;
		}
	}
	
	static class GrafoEdmonds
	{
		ArrayList <Edge> [] G;
		
		@SuppressWarnings("unchecked")
		GrafoEdmonds(int N)
		{
			G = new ArrayList[N];
			for(int i = 0; i < N; i++)
				G[i] = new ArrayList <Edge> ();
		}
		
		void AddEdge(int from, int to, int cap) 
		{
			if(from == -1 || to == -1) return;
			int cambio = from == to ? 1 : 0;
			G[from].add(new Edge(to, cap, G[to].size() + cambio));
			G[to].add(new Edge(from, 0, G[from].size() - 1));
		}
		
		
		int maxFlow(int s, int t)
		{
			int[][] E = new int[G.length][];
			int[][] C = new int[G.length][];
			int[][] back = new int[G.length][];
			for(int i = 0; i < G.length; i++)
			{
				E[i] = new int[G[i].size()];
				C[i] = new int[G[i].size()];
				back[i] = new int[G[i].size()];
				int indice = 0;
				for(Edge e : G[i])
				{
					E[i][indice] = e.to;
					C[i][indice] = e.cap;
					back[i][indice] = e.index;
					indice++;
				}
			}
			return edmondsKarp(E, C, back, s, t);
		}
		
	    int edmondsKarp(int[][] E, int[][] C, int[][] back, int s, int t)
	    {
	        int n = C.length;
	        // Residual capacity from u to v is C[u][v] - F[u][v]
	        int[][] F = new int[n][];
	        for(int i = 0; i < E.length; i++)
	        	F[i] = new int[E[i].length];
	        FastArrayDeque Q = new FastArrayDeque(E.length);
	        int[] P = new int[n]; // Parent table
	        int[] pIndex = new int[n];
	        int[] M = new int[n]; // Capacity of path to node
	        while (true) {
	            Arrays.fill(P, -1);
	            P[s] = s;
	            Arrays.fill(M, 0);
	            M[s] = Integer.MAX_VALUE;
	            Q.clear();
	            // BFS queue
	            Q.add(s);
	            LOOP:
	            while (!Q.isEmpty()) {
	                int u = Q.poll();
	                int indice = -1;
	                for (int v : E[u]) {
	                	indice++;
	                    // There is available capacity,
	                    // and v is not seen before in search
	                    if (C[u][indice] - F[u][indice] > 0 && P[v] == -1) {
	                        P[v] = u;
	                        pIndex[v] = indice;
	                        M[v] = Math.min(M[u], C[u][indice] - F[u][indice]);
	                        if (v != t)
	                            Q.add(v);
	                        else {
	                            // Backtrack search, and write flow
	                            while (P[v] != v) {
	                                u = P[v];
	                                indice = pIndex[v];
	                                F[u][indice] += M[t];
	                                F[v][back[u][indice]] -= M[t];
	                                v = u;
	                            }
	                            break LOOP;
	                        }
	                    }
	                }
	            }
	            if (P[t] == -1) { // We did not find a path to t
	                int sum = 0;
	                for (int x : F[s])
	                    sum += x;
	                return sum;
	            }
	        }
	    }
	}
	
	static int[][] diffs = new int[][]{{0, 0}, {1, 0}, {-1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			int k = sc.nextInt();
			int h = sc.nextInt();
			if(n == 0 && k == 0 && h == 0) return;
			int[][] field = sc.nextIntMatrix(n, n);
			int[][] cows = sc.nextIntMatrix(k, 2);
			int[] hours = sc.nextIntArray(h);
			int[][][] idsEntrada = new int[n][n][h + 1];
			int[][][] idsSalida = new int[n][n][h + 1];
			int idActual = 0;
			int source = idActual++;
			int sink = idActual++;
			for(int i = 0; i <= h; i++)
			{
				int altura = i == 0 ? -1 : hours[i - 1];
				for(int j = 0; j < n; j++)
					for(int l = 0; l < n; l++)
					{
						if(field[j][l] <= altura)
							idsEntrada[j][l][i] = idsSalida[j][l][i] = -1;
						else
						{
							idsEntrada[j][l][i] = idActual++;
							idsSalida[j][l][i] = idActual++;
						}
					}
			}
			GrafoEdmonds maxFlow = new GrafoEdmonds(idActual);
			for(int i = 0; i <= h; i++)
			{
				for(int j = 0; j < n; j++)
					for(int l = 0; l < n; l++)
					{
						if(i == h)
							maxFlow.AddEdge(idsSalida[j][l][i], sink, 1);
						else
						{
							for(int[] d : diffs)
							{
								int iN = j + d[0];
								int jN = l + d[1];
								if(iN >= 0 && iN < n && jN >= 0 && jN < n)
									maxFlow.AddEdge(idsSalida[j][l][i], idsEntrada[iN][jN][i + 1], 1);
							}
						}
						maxFlow.AddEdge(idsEntrada[j][l][i], idsSalida[j][l][i], 1);
					}
			}
			for(int[] c : cows)
				maxFlow.AddEdge(source, idsEntrada[c[0]][c[1]][0], 1);
			System.out.println(maxFlow.maxFlow(source, sink));
		}
	}

}
