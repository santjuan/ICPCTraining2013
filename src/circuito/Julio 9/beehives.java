import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class beehives
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
	
	static class Nodo
	{
		ArrayList <Nodo> aristas = new ArrayList <Nodo> ();
		int id;
		
		int findBestCycle(Nodo[] todos)
		{
			int[] visited = new int[todos.length];
			Nodo[] padre = new Nodo[todos.length];
			Arrays.fill(visited, Integer.MAX_VALUE);
			visited[id] = 0;
			ArrayDeque <Nodo> cola = new ArrayDeque <Nodo> ();
			cola.add(this);
			int min = Integer.MAX_VALUE;
			while(!cola.isEmpty())
			{
				Nodo n = cola.pollFirst();
				for(Nodo o : n.aristas)
				{
					if(o == padre[n.id]) continue;
					if(visited[o.id] != Integer.MAX_VALUE)
					{
						int size = visited[n.id] + visited[o.id] + 1;
						if(size <= 2) continue;
						min = Math.min(min, size);
						if(size > min) return min;
						continue;
					}
					visited[o.id] = visited[n.id] + 1;
					padre[o.id] = n;
					cola.add(o);
				}
			}
			return min;
		}
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int T = sc.nextInt();
		for(int caso = 1; caso <= T; caso++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			Nodo[] nodos = new Nodo[n];
			for(int i = 0; i < nodos.length; i++)
			{
				nodos[i] = new Nodo();
				nodos[i].id = i;
			}
			for(int i = 0; i < m; i++)
			{
				int u = sc.nextInt();
				int v = sc.nextInt();
				nodos[u].aristas.add(nodos[v]);
				nodos[v].aristas.add(nodos[u]);
			}
			int best = Integer.MAX_VALUE;
			for(Nodo no : nodos) best = Math.min(best, no.findBestCycle(nodos));
			System.out.println(best == Integer.MAX_VALUE ? ("Case " + caso + ": impossible") : ("Case " + caso + ": " + best));
		}
	}

}
