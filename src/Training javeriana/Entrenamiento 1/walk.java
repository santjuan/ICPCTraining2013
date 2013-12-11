import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class walk 
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
		
		static <T> T fill(T arreglo, int val)
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
	}

	
	static class DefaultMap <K, V> extends TreeMap <K, V>
	{
		private static final long serialVersionUID = 7072521407134705877L;

		Class <V> clazz;
		
		@SuppressWarnings("unchecked")
		public DefaultMap(V def) 
		{
			super();
			clazz = (Class <V>) def.getClass();
		}
		
		public V newInstance()
		{
			try 
			{
				return clazz.newInstance();
			} 
			catch (Exception e)
			{
				throw new RuntimeException(e);
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public V get(Object key) {
			if(!containsKey(key))
				put((K) key, newInstance());
			return super.get(key);
		}
	}

	static DefaultMap < Long, TreeSet <Long> > xs, ys;
	static TreeSet <Long> xPaths, yPaths;
	
	
	static boolean canBeSeen(long[] arbol, boolean x, boolean dir)
	{
		TreeSet <Long> where = x ? ys.get(arbol[1]) : xs.get(arbol[0]);
		TreeSet <Long> wherePath = x ? xPaths : yPaths;
		Long nextTree = dir ? where.higher(arbol[x ? 0 : 1]) : where.lower(arbol[x ? 0 : 1]);
		Long nextPath = dir ? wherePath.higher(arbol[x ? 0 : 1]) : wherePath.lower(arbol[x ? 0 : 1]);
		return (nextTree == null && nextPath != null) || (nextTree != null && nextPath != null && (dir ? nextTree > nextPath : nextTree < nextPath));
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n == 0 && m == 0) return;
			xs = new DefaultMap < Long, TreeSet <Long> > (new TreeSet <Long> ());
			ys = new DefaultMap < Long, TreeSet <Long> > (new TreeSet <Long> ());
			xPaths = new TreeSet <Long> ();
			yPaths = new TreeSet <Long> ();
			long[][] arboles = new long[n][2];
			for(int i = 0; i < n; i++)
			{
				arboles[i][0] = sc.nextLong();
				arboles[i][1] = sc.nextLong();
				xs.get(arboles[i][0]).add(arboles[i][1]);
				ys.get(arboles[i][1]).add(arboles[i][0]);
			}
			for(int i = 0; i < m; i++)
			{
				String s = sc.next();
				if(s.startsWith("x=")) xPaths.add(Long.parseLong(s.substring(2)));
				else yPaths.add(Long.parseLong(s.substring(2)));
			}
			int count = 0;
			for(long[] arbol : arboles)
			{
				if(canBeSeen(arbol, true, true) || canBeSeen(arbol, true, false) || canBeSeen(arbol, false, true) || canBeSeen(arbol, false, false))
					count++;
			}
			System.out.println(count);
		}
	}
}