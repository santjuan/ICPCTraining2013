import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CodeD 
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

	static class City
	{
		int id;
		DisjointSet currentDS;
		
		public City(Scanner sc, int i)
		{
			id = i;
			currentDS = new DisjointSet(this);
		}
	}
	
	static class DisjointSet implements Comparable <DisjointSet>
	{
		ArrayList <City> cities = new ArrayList <City> ();
		long edgeTotal = 0;
		boolean inPQ = false;
		
		public DisjointSet(City city)
		{
			cities.add(city);
		}

		@Override
		public int compareTo(DisjointSet o)
		{
			if(edgeTotal < o.edgeTotal)
				return -1;
			else if(edgeTotal == o.edgeTotal)
				return 0;
			else
				return 1;
		}
	}
	
	static DisjointSet merge(DisjointSet a, DisjointSet b)
	{
		if(a.cities.size() < b.cities.size())
			return merge(b, a);
		else
		{
			for(City c : b.cities)
			{
				c.currentDS = a;
				a.cities.add(c);
			}
			a.edgeTotal += b.edgeTotal;
			return a;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int p = sc.nextInt();
		int q = sc.nextInt();
		City[] cities = sc.nextObjectArray(City.class, n);
		for(int i = 0; i < m; i++)
		{
			int xi = sc.nextInt() - 1;
			int yi = sc.nextInt() - 1;
			long length = sc.nextLong();
			if(cities[xi].currentDS != cities[yi].currentDS)
				merge(cities[xi].currentDS, cities[yi].currentDS);
			cities[xi].currentDS.edgeTotal += length;
		}
		PriorityQueue <DisjointSet> pq = new PriorityQueue <DisjointSet> ();
		for(City c : cities)
			if(!c.currentDS.inPQ)
			{
				c.currentDS.inPQ = true;
				pq.add(c.currentDS);
			}
		int left = p;
		long total = 0;
		ArrayList <String> solution = new ArrayList <String> ();
		while(pq.size() > q && left > 0 && pq.size() > 1)
		{
			DisjointSet first = pq.poll();
			DisjointSet second = pq.poll();
			solution.add((first.cities.get(0).id + 1) + " " + (second.cities.get(0).id + 1));
			long edgeCost = first.edgeTotal + second.edgeTotal + 1;
			edgeCost = Math.min(edgeCost, 1000000000L);
			total += edgeCost;
			first = merge(first, second);
			first.edgeTotal += edgeCost;
			pq.add(first);
			left--;
		}
		if(pq.size() != q)
			System.out.println("NO");
		else
		{
			if(left > 0)
			{
				String solutionLeft = null;
				while(!pq.isEmpty())
				{
					if(pq.peek().cities.size() > 1)
						solutionLeft = (pq.peek().cities.get(0).id + 1) + " " + (pq.peek().cities.get(1).id + 1);
					pq.poll();
				}
				if(solutionLeft != null)
				{
					for(int i = 0; i < left; i++)
					{
						total += 1000L;
						solution.add(solutionLeft);
					}
				}
				else
				{
					System.out.println("NO");
					return;
				}
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			bw.write("YES");
			bw.write('\n');
			for(String s : solution)
			{
				bw.write(s);
				bw.write('\n');
			}
//			bw.write(total + "");
			bw.flush();
			bw.close();
		}
	}
}