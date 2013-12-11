import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveF
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

	static class BipartiteMatching
	{
		static ArrayList <Integer> [] neighbors;
		int nodes;

		@SuppressWarnings("unchecked")
		public BipartiteMatching(int nodes)
		{
			this.nodes = nodes;
			neighbors = new ArrayList[nodes];
			for(int i = 0; i < nodes; i++)
				neighbors[i] = new ArrayList <Integer> ();
		}

		public void AddEdge(int a, int b)
		{
			neighbors[a].add(b);
		}

		boolean FindMatch(int i, int[] mr, int[] mc, boolean[] seen) 
		{
			for(int jj = 0; jj < neighbors[i].size(); jj++)
			{
				int j = neighbors[i].get(jj);
				if (!seen[j]) {
					seen[j] = true;
					if (mc[j] < 0 || FindMatch(mc[j], mr, mc, seen)) {
						mr[i] = j;
						mc[j] = i;
						return true;
					}
				}
			}
			return false;
		}

		int bipartiteMatching() 
		{
			int[] mr = new int[nodes];
			Arrays.fill(mr, -1);
			int[] mc = new int[nodes];
			Arrays.fill(mc, -1);
			int ct = 0;
			boolean[] seen = new boolean[nodes];
			for (int i = 0; i < nodes; i++) 
			{
				Arrays.fill(seen, false);
				if(FindMatch(i, mr, mc, seen))
					ct++;
			}
			return ct;
		}
	}
	
	
	static String normalizar(String b)
	{
		while(b.length() > 1 && b.charAt(0) == '0')
			b = b.substring(1);
		return b;
	}
	
	static class Ride
	{
		int start;
		int cost;
		int startI;
		int startJ;
		int endI;
		int endJ;
		
		Ride(String startS, int a, int b, int c, int d)
		{
			cost = Math.abs(a - c) + Math.abs(b - d);
			start = Integer.parseInt(normalizar(startS.split(":")[0])) * 60 + Integer.parseInt(normalizar(startS.split(":")[1]));
			startI = a;
			startJ = b;
			endI = c;
			endJ = d;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 1; caso <= t; caso++)
		{
			ArrayList <Ride> rides = new ArrayList <Ride> ();
			int M = sc.nextInt();
			for(int i = 0; i < M; i++)
			{
				String start = sc.next();
				int a = sc.nextInt();
				int b = sc.nextInt();
				int c = sc.nextInt();
				int d = sc.nextInt();
				rides.add(new Ride(start, a, b, c, d));
			}
			BipartiteMatching bm = new BipartiteMatching(rides.size());
			for(int i = 0;  i < rides.size(); i++)
			{
				Ride r1 = rides.get(i);
				for(int j = 0; j < rides.size(); j++)
				{
					if(j == i)
						continue;
					Ride r2 = rides.get(j);
					if(r1.start + r1.cost + Math.abs(r1.endI - r2.startI) + Math.abs(r1.endJ - r2.startJ) < r2.start)
						bm.AddEdge(i, j);
				}
			}
			System.out.println(rides.size() - bm.bipartiteMatching());
		}
	}

}
