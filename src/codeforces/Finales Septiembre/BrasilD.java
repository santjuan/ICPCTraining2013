import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BrasilD 
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
	
	static class Entry
	{
		static int[] current;
		static int[] target;
		static int[] targetTemp;
		static int[] original;
		static int maximum;
		
		long where;

		Entry(long whereTemp)
		{
			where = whereTemp;
		}
		
		void unpack()
		{
			long whereTemp = where;
			maximum = 0;
			for(int i = 0; i < current.length; i++)
			{
				current[i] = (int) (whereTemp & 15);
				maximum = Math.max(maximum, current[i]);
				whereTemp >>>= 4;
			}
		}
		
		boolean isTarget()
		{
			unpack();
			if(maximum + 1 != target.length) return false;
			for(int i = 0; i < target.length; i++)
				targetTemp[i] = 0;
			for(int i = 0; i < current.length; i++)
				targetTemp[current[i]] += original[i];
			boolean ok = true;
			for(int i = 0; i < target.length; i++)
				if(target[i] != targetTemp[i])
					ok = false;
			if(ok) return true;
			ok = true;
			for(int i = 0; i < target.length; i++)
				if(target[target.length - 1 - i] != targetTemp[i])
					return false;
			return true;
		}
		
		@Override
		public String toString()
		{
			unpack();
			int[] vals = new int[maximum + 1];
			for(int i = 0; i < current.length; i++)
				vals[current[i]] += original[i];
			return Arrays.toString(vals);
		}
		
		static void normalize()
		{
			int min = Integer.MAX_VALUE;
			for(int i : current)
				min = Math.min(min, i);
			for(int i = 0; i < current.length; i++)
				current[i] = current[i] - min;
		}
		
		static Entry pack()
		{
			long whereTemp = 0;
			for(int i = current.length - 1; i >= 0; i--)
			{
				whereTemp <<= 4;
				whereTemp |= current[i];
			}
			return new Entry(whereTemp);
		}
		
		static void rotate(int from)
		{
			if(from == 0)
			{
				for(int i = 0; i < current.length; i++)
					current[i] = maximum - current[i];
			}
			else
			{
				int fromLeft = from - 1;
				int fromRight = from;
				boolean leftRight = from <= ((maximum + 1) >>> 1);
				while(fromLeft >= 0 && fromRight <= maximum)
				{
					if(leftRight)
						moveAll(fromLeft, fromRight);
					else
						moveAll(fromRight, fromLeft);
					fromLeft--;
					fromRight++;
				}
			}
			normalize();
		}

		static void moveAll(int a, int b) 
		{
			for(int i = 0; i < current.length; i++)
				if(current[i] == a)
					current[i] = b;
		}
		
		@Override
		public int hashCode()
		{
			return (int) (where ^ (where >>> 32));
		}
		
		@Override
		public boolean equals(Object other)
		{
			return where == ((Entry) other).where;
		}
		
		public boolean visit()
		{
			if(isTarget()) return true;
			if(maximum + 1 < target.length) return false;
			if(visited.contains(this)) return false;
			visited.add(this);
			for(int i = 1; i <= maximum; i++)
			{
				rotate(i);
				if(pack().visit()) return true;
				unpack();
			}
			return false;
		}
	}
	
	static HashSet <Entry> visited = new HashSet <Entry> ();
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			Integer nTemp = sc.nextInteger();
			if(nTemp == null) return;
			int n = nTemp;
			int[] sourceValues = sc.nextIntArray(n);
			int m = sc.nextInt();
			Entry.target = sc.nextIntArray(m);
			Entry.targetTemp = new int[m];
			Entry.original = sourceValues;
			Entry.current = new int[n];
			for(int i = 0; i < n; i++)
				Entry.current[i] = i;
			Entry.maximum = n - 1;
			Entry currentEntry = Entry.pack();
			visited.clear();
			System.out.println(currentEntry.visit() ? "S" : "N");
		}
	}
}