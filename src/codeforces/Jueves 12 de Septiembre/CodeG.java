import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CodeG 
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
		
		public char[][] nextGrid(int r, int c) 
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
	}
	
	
	static class Value implements Comparable <Value>
	{
		int val;
		int id;
		
		Value(int v, int i)
		{
			val = v;
			id = i;
		}

		@Override
		public int compareTo(Value o) 
		{
			return val - o.val;
		}
	}
	
	static class Color
	{
		ArrayList <Value> cubes = new ArrayList <Value> ();
		int id;
		
		Color(int i)
		{
			id = i;
		}
	}
	
	static class Entry implements Comparable <Entry>
	{
		long count;
		int id;
		
		public Entry(int i, long suma) 
		{
			id = i;
			count = suma;
		}

		@Override
		public int compareTo(Entry o) 
		{
			if(count == o.count)
				return id - o.id;
			return Long.valueOf(o.count).compareTo(count);
		}
	}
	
	static HashMap < Integer, TreeSet <Entry> > entryMap = new HashMap < Integer, TreeSet <Entry> > ();
	static HashMap <Integer, Color> colors = new HashMap <Integer, Color> ();
		
	static Color getColor(int id)
	{
		if(!colors.containsKey(id))
			colors.put(id, new Color(id));
		return colors.get(id);
	}
	
	static TreeSet <Entry> getEntrySet(int id)
	{
		if(!entryMap.containsKey(id))
			entryMap.put(id, new TreeSet <Entry> ());
		return entryMap.get(id);
	}
	
	static long getDifferent(TreeSet <Entry> set, int id)
	{
		for(Entry e : set)
			if(e.id != id)
				return e.count;
		return -1;
	}
	
	static Entry getDifferentEntry(TreeSet <Entry> set, int id)
	{
		for(Entry e : set)
			if(e.id != id)
				return e;
		return null;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int id = 1;
		for(int[] c : sc.nextIntMatrix(n, 2))
			getColor(c[0]).cubes.add(new Value(c[1], id++));
		for(Color c : colors.values())
			Collections.sort(c.cubes, Collections.reverseOrder());
		for(Color c : colors.values())
		{
			int total = 0;
			long suma = 0;
			getEntrySet(0).add(new Entry(c.id, suma));
			for(Value v : c.cubes)
			{
				int i = v.val;
				suma += i;
				total++;
				getEntrySet(total).add(new Entry(c.id, suma));
			}
		}
		long best = 0;
		int bestAns1 = -1;
		int bestAns2 = 0;
		int bestAns3 = 0;
		int total = entryMap.size();
		for(int i = 0; i < total; i++)
		{
			for(Entry e : entryMap.get(i))
			{
				long possible = getDifferent(getEntrySet(i - 1), e.id);
				if(possible != -1)
				{
					if(e.count + possible > best)
					{
						best = e.count + possible;
						bestAns1 = e.id;
						bestAns2 = i;
						bestAns3 = i - 1;
					}
				}
				possible = getDifferent(getEntrySet(i), e.id);
				if(possible != -1)
				{
					if(e.count + possible > best)
					{
						best = e.count + possible;
						bestAns1 = e.id;
						bestAns2 = i;
						bestAns3 = i;
					}
				}
				possible = getDifferent(getEntrySet(i + 1), e.id);
				if(possible != -1)
				{
					if(e.count + possible > best)
					{
						best = e.count + possible;
						bestAns1 = e.id;
						bestAns2 = i;
						bestAns3 = i + 1;
					}
				}
			}
		}
		String bestAns = (bestAns2 + bestAns3) + "";
		if(bestAns2 + bestAns3 != 0)
		{
			bestAns = generateAns(bestAns1, bestAns2, bestAns3);
		}
		System.out.println(best);
		System.out.println(bestAns);
	}

	private static String generateAns(int id, int i, int j) 
	{
		int otherId = getDifferentEntry(entryMap.get(j), id).id;
		String ans = (i + j) + "\n";
		StringBuilder sb = new StringBuilder();
		boolean anterior = i < j ? false : true;
		int countI = 0;
		int countJ = 0;
		int total = i + j;
		while(total > 0)
		{
			if(anterior)
				sb.append(' ').append(colors.get(id).cubes.get(countI++).id);
			else
				sb.append(' ').append(colors.get(otherId).cubes.get(countJ++).id);
			total--;
			anterior = !anterior;
		}
		return ans + sb.toString().trim();
	}
}