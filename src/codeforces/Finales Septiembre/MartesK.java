import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class MartesK 
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
		Node a;
		Node b;
		int cost;
		
		public Edge(Node aa, Node bb, int c)
		{
			a = aa;
			b = bb;
			cost = c;
		}
	}
	
	static class Entry implements Comparable <Entry>
	{
		Node node;
		int value;
		
		Entry(Node n)
		{
			node = n;
			value = n.currentDelta;
		}

		@Override
		public int compareTo(Entry other) 
		{
			if(value == other.value)
				return node.id - other.node.id;
			return value - other.value;
		}
	}
	
	
	static class Node
	{
		int id;
		boolean zero;
		int currentDelta;
		
		public Node(Scanner sc, int i)
		{
			id = i;
			zero = sc.nextInt() == 0;
			currentDelta = sc.nextInt();
		}
	}
	
	static TreeSet <Entry> unopenedZeroNodes = new TreeSet <Entry> ();
	static TreeSet <Entry> unopenedOneNodes = new TreeSet <Entry> ();
	static TreeSet <Entry> openedZeroNodes = new TreeSet <Entry> ();
	static TreeSet <Entry> openedOneNodes = new TreeSet <Entry> ();
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		Node[] allNodes = sc.nextObjectArray(Node.class, n);
		for(Node node : allNodes)
			if(node.zero)
				unopenedZeroNodes.add(new Entry(node));
			else
				unopenedOneNodes.add(new Entry(node));
		if(unopenedZeroNodes.last().value >= unopenedOneNodes.last().value)
			openedZeroNodes.add(unopenedZeroNodes.pollLast());
		else
			openedOneNodes.add(unopenedOneNodes.pollLast());
		ArrayList <Edge> allEdges = new ArrayList <Edge> ();
		while(unopenedOneNodes.size() > 0 || unopenedZeroNodes.size() > 0)
		{
			boolean openZero = true;
			if(unopenedZeroNodes.isEmpty() || openedOneNodes.isEmpty())
				openZero = false;
			else
			{
				if(unopenedOneNodes.isEmpty() || openedZeroNodes.isEmpty())
					openZero = true;
				else
				{
					if(openedOneNodes.last().value >= openedZeroNodes.last().value)
						openZero = true;
					else
						openZero = false;
				}
			}
			if(openZero)
			{
				if(unopenedZeroNodes.first().value <= openedOneNodes.last().value)
				{
					Node unopenedZero = unopenedZeroNodes.pollFirst().node;
					Node openedOne = openedOneNodes.pollLast().node;
					Edge e = new Edge(unopenedZero, openedOne, unopenedZero.currentDelta);
					allEdges.add(e);
					unopenedZero.currentDelta -= e.cost;
					openedOne.currentDelta -= e.cost;
					openedZeroNodes.add(new Entry(unopenedZero));
					openedOneNodes.add(new Entry(openedOne));
				}
				else
				{
					Node unopenedZero = unopenedZeroNodes.pollLast().node;
					Node openedOne = openedOneNodes.pollLast().node;
					Edge e = new Edge(unopenedZero, openedOne, Math.min(unopenedZero.currentDelta, openedOne.currentDelta));
					allEdges.add(e);
					unopenedZero.currentDelta -= e.cost;
					openedOne.currentDelta -= e.cost;
					openedZeroNodes.add(new Entry(unopenedZero));
					openedOneNodes.add(new Entry(openedOne));
				}
			}
			else
			{
				if(unopenedOneNodes.first().value <= openedZeroNodes.last().value)
				{
					Node unopenedOne = unopenedOneNodes.pollFirst().node;
					Node openedZero = openedZeroNodes.pollLast().node;
					Edge e = new Edge(unopenedOne, openedZero, unopenedOne.currentDelta);
					allEdges.add(e);
					unopenedOne.currentDelta -= e.cost;
					openedZero.currentDelta -= e.cost;
					openedZeroNodes.add(new Entry(openedZero));
					openedOneNodes.add(new Entry(unopenedOne));
				}
				else
				{
					Node unopenedOne = unopenedOneNodes.pollLast().node;
					Node openedZero = openedZeroNodes.pollLast().node;
					Edge e = new Edge(unopenedOne, openedZero, Math.min(unopenedOne.currentDelta, openedZero.currentDelta));
					allEdges.add(e);
					unopenedOne.currentDelta -= e.cost;
					openedZero.currentDelta -= e.cost;
					openedZeroNodes.add(new Entry(openedZero));
					openedOneNodes.add(new Entry(unopenedOne));
				}
			}
		}
		for(Edge e : allEdges)
			sc.printLine(e.a.id + 1, e.b.id + 1, e.cost);
	}
}