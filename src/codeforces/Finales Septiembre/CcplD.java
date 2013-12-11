import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class CcplD 
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
		
		public Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		public int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
		
		public char[][] nextGrid(int r) 
		{
			char[][] grid = new char[r][];
			for(int i = 0; i < r; i++)
				grid[i] = next().toCharArray();
			return grid;
		}
		
		public static <T> T fill(T arreglo, int val)
		{
			if(arreglo instanceof Object[])
			{
				Object[] a = (Object[]) arreglo;
				for(Object x : a)
					fill(x, val);
			}
			else if(arreglo instanceof int[])
				Arrays.fill((int[]) arreglo, val);
			else if(arreglo instanceof double[])
				Arrays.fill((double[]) arreglo, val);
			else if(arreglo instanceof long[])
				Arrays.fill((long[]) arreglo, val);
			return arreglo;
		}
		
		<T> T[] nextObjectArray(Class <T> clazz, int size)
		{
			@SuppressWarnings("unchecked")
			T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
			for(int c = 0; c < 3; c++)
			{
				Constructor <T> constructor;
				try 
				{
					if(c == 0)
						constructor = clazz.getDeclaredConstructor(Scanner.class, Integer.TYPE);
					else if(c == 1)
						constructor = clazz.getDeclaredConstructor(Scanner.class);
					else
						constructor = clazz.getDeclaredConstructor();
				} 
				catch(Exception e)
				{
					continue;
				}
				try
				{
					for(int i = 0; i < result.length; i++)
					{
						if(c == 0)
							result[i] = constructor.newInstance(this, i);
						else if(c == 1)
							result[i] = constructor.newInstance(this);
						else
							result[i] = constructor.newInstance();	
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
				return result;
			}
			throw new RuntimeException("Constructor not found");
		}
		
		public void printLine(int... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(long... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(double... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(int prec, double... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.printf("%." + prec + "f", vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.printf(" %." + prec + "f", vals[i]);
				System.out.println();
			}
		}
		
		public Collection <Integer> wrap(int[] as)
		{
			ArrayList <Integer> ans = new ArrayList <Integer> ();
			for(int i : as)
				ans.add(i);
			return ans;
		}
		
		public int[] unwrap(Collection <Integer> collection)
		{
			int[] vals = new int[collection.size()];
			int index = 0;
			for(int i : collection) vals[index++] = i;
			return vals;
		}
	}
	
	static class Edge
	{
		int cost;
		Node other;
		Edge pair;
		int solvedCost;
		boolean isPoisoned;
	}
	
	static class Node
	{
		ArrayList <Edge> edges = new ArrayList <Edge> ();
		ArrayList <Edge> backEdges = new ArrayList <Edge> ();
		boolean canMark;
		boolean visited;
		boolean inProblems;
		int solvedEdges;
		int bestTime;
	}
	
	static Node[] allNodes;
	
	static int isValid()
	{
		for(Node n : allNodes) 
		{
			n.visited = false;
			n.solvedEdges = 0;
			n.bestTime = Integer.MAX_VALUE;
			n.inProblems = false;
			for(Edge e : n.edges)
				e.isPoisoned = false;
		}
		allNodes[allNodes.length - 1].bestTime = 0;
		int visited = 0;
		while(visited < allNodes.length)
		{
			boolean cambio = false;
			for(Node n : allNodes)
			{
				if(!n.visited && n.solvedEdges == n.edges.size() && n.bestTime != Integer.MAX_VALUE)
				{
					n.visited = true;
					boolean different = false;
					for(Edge e : n.edges)
					{
						if(e.solvedCost != n.bestTime)
							different = true;
						if(e.isPoisoned)
							different = true;
					}
					if(different && !n.canMark)
						n.inProblems = true;
					else if(different && n.canMark)
					{
						for(Edge e : n.edges)
							if(!e.isPoisoned && e.solvedCost == n.bestTime)
								different = false;
						if(different)
							n.inProblems = true;
					}
					for(Edge e : n.backEdges)
					{
						e.pair.solvedCost = n.bestTime + e.cost;
						e.other.bestTime = Math.min(e.other.bestTime, e.pair.solvedCost);
						e.other.solvedEdges++;
						e.pair.isPoisoned = n.inProblems;
					}
					cambio = true;
					visited++;
					break;
				}
			}
			if(!cambio) return -1;
		}
		if(allNodes[0].inProblems) return -1;
		else return allNodes[0].bestTime;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) return;
			TreeMap <String, Node> nodes = new TreeMap <String, Node> ();
			allNodes = new Node[n];
			for(int i = 0; i < n; i++)
			{
				String name = sc.next();
				if(!nodes.containsKey(name))
					nodes.put(name, new Node());
				Node current = nodes.get(name);
				int out = sc.nextInt();
				for(int j = 0; j < out; j++)
				{
					String otherS = sc.next();
					int cost = sc.nextInt();
					if(!nodes.containsKey(otherS))
						nodes.put(otherS, new Node());
					Node other = nodes.get(otherS);
					Edge a = new Edge();
					a.cost = cost;
					a.other = other;
					Edge b = new Edge();
					b.cost = cost;
					b.other = current;
					a.pair = b;
					b.pair = a;
					other.backEdges.add(b);
					current.edges.add(a);
				}
				allNodes[i] = current;
			}
			final int limit = 1 << n;
			int best = 7;
			int bestTime = Integer.MAX_VALUE;
			for(int i = 0; i < limit; i++)
			{
				for(int j = 0; j < n; j++)
					allNodes[j].canMark = (i & (1 << j)) != 0;
				if(Integer.bitCount(i) > best) continue;
				if(allNodes[n - 1].canMark) continue;
				int time = isValid();
				if(time != -1 && time < bestTime)
				{
					bestTime = time;
					best = Integer.bitCount(i);
				}
				if(time != -1 && time == bestTime)
				{
					bestTime = time;
					best = Math.min(best, Integer.bitCount(i));
				}
			}
			System.out.println(bestTime + " " + best);
		}
	}
}