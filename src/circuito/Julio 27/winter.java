import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class winter
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

	static class DisjointSet 
	{
		int[] p, rank;

		public DisjointSet(int size) 
		{
			rank = new int[size];
			p = new int[size];
			clear();
		}
		
		public void make_set(int x) 
		{
			p[x] = x;
			rank[x] = 0;
		}
		
		public void clear()
		{
			for (int i = 0; i < p.length; i++)
				make_set(i);
		}

		public void merge(int x, int y) 
		{
			link(find_set(x), find_set(y));
		}

		public void link(int x, int y) 
		{
			if (rank[x] > rank[y])
				p[y] = x;
			else {
				p[x] = y;
				if (rank[x] == rank[y])
					rank[y] += 1;
			}
		}

		public int find_set(int x) 
		{
			if (x != p[x])
				p[x] = find_set(p[x]);
			return p[x];
		}

		public int countSets(int n) 
		{
			HashSet <Integer> sets = new HashSet <Integer> ();
			for(int i = 0; i < n; i++)
				sets.add(find_set(i));
			return sets.size();
		}
	}
	
	static class Road implements Comparable <Road>
	{
		int a;
		int b;
		int w;
		int id;
		
		Road(Scanner sc, int i)
		{
			a = sc.nextInt() - 1;
			b = sc.nextInt() - 1;
			w = sc.nextInt();
			id = i;
		}

		@Override
		public int compareTo(Road o) 
		{
			if(w == o.w)
				return id - o.id;
			return o.w - w;
		}
	}
	
	static class Query implements Comparable <Query>
	{
		char tipo;
		int a;
		int b;
		int w;
		Boolean respuesta;
		
		Query(Scanner sc)
		{
			tipo = sc.next().charAt(0);
			a = sc.nextInt();
			b = sc.nextInt();
			a--;
			if(tipo == 'S')
			{
				w = sc.nextInt();
				b--;
			}
		}

		@Override
		public int compareTo(Query o)
		{
			return o.w - w;
		}
	}
	
	static class Roads
	{
		Road[] roads;
		TreeSet <Road> roadsMap;
		DisjointSet ds;
		
		Roads(Scanner sc, int n, int m)
		{
			roads = new Road[m];
			for(int i = 0; i < m; i++)
				roads[i] = new Road(sc, i);
			roadsMap = new TreeSet <Road> ();
			for(Road r : roads)
				roadsMap.add(r);
			ds = new DisjointSet(n);
		}
		
		void change(int r, int wN)
		{
			roadsMap.remove(roads[r]);
			roads[r].w = wN;
			roadsMap.add(roads[r]);
		}
		
		void processQueries(ArrayList <Query> queries)
		{
			Collections.sort(queries);
			Iterator <Query> queriesIt = queries.iterator();
			Iterator <Road> roadIterator = roadsMap.iterator();
			ds.clear();
			Road actual = null;
			while(queriesIt.hasNext())
			{
				Query q = queriesIt.next();
				while(true)
				{
					if(actual == null)
					{
						if(!roadIterator.hasNext())
							break;
						actual = roadIterator.next();
					}
					if(actual.w < q.w) break;
					ds.merge(actual.a, actual.b);
					actual = null;
				}
				q.respuesta = ds.find_set(q.a) == ds.find_set(q.b);
			}
		}
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n == 0 && m == 0) 
			{
				System.out.flush();
				return;
			}
			Roads roads = new Roads(sc, n, m);
			int qs = sc.nextInt();
			LinkedList <Query> queries = new LinkedList<Query> ();
			Query[] queriesR = new Query[qs];
			for(int i = 0; i < qs; i++) 
			{
				queriesR[i] = new Query(sc);
				queries.add(queriesR[i]);
			
			}
			ArrayList <Query> queriesActuales = new ArrayList <Query> ();
			while(!queries.isEmpty())
			{
				Query q = queries.poll();
				if(q.tipo == 'S')
				{
					queriesActuales.clear();
					queriesActuales.add(q);
					while(!queries.isEmpty())
					{
						if(queries.peek().tipo != 'S') break;
						queriesActuales.add(queries.poll());
					}
					roads.processQueries(queriesActuales);
				}
				else
					roads.change(q.a, q.b);
			}
			for(Query q : queriesR)
			{
				if(q.tipo == 'S')
				{
					sb.append(q.respuesta ? '1' : '0');
					sb.append('\n');
				}
			}
			System.out.print(sb.toString());
			sb.setLength(0);
		}
	}
}