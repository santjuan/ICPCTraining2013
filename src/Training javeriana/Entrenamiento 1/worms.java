import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class worms 
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
	
	static int[][][] rules;
	static int[] end;
	
	static int[][][] dp1;
	
	static int bestPossible(int rule, int fromB, int toB)
	{
		if(dp1[rule][fromB][toB] != -1) return dp1[rule][fromB][toB];
		if(fromB == toB)
			return dp1[rule][fromB][toB] = end[fromB] == rule ? 0 : Integer.MAX_VALUE;
		int best = Integer.MAX_VALUE;
		for(int j = fromB; j < toB; j++)
		{
			for(int[] r : rules[rule])
			{
				int posA = bestPossible(r[0], fromB, j);
				if(posA != Integer.MAX_VALUE)
					posA++;
				int posB = bestPossible(r[1], j + 1, toB);
				if(posB != Integer.MAX_VALUE)
					posB++;
				best = Math.min(best, Math.max(posA, posB));
			}
		}
		return dp1[rule][fromB][toB] = best;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) return;
			@SuppressWarnings("unchecked")
			ArrayList <int[]> [] rulesA = sc.nextObjectArray(ArrayList.class, 20);
			for(int i = 0; i < n; i++)
			{
				String e = sc.next();
				rulesA[e.charAt(0) - 'A'].add(new int[]{e.charAt(1) - 'A', e.charAt(2) - 'A'});
			}
			String f = sc.next();
			end = new int[f.length()];
			for(int i = 0; i < end.length; i++)
				end[i] = f.charAt(i) - 'A';
			rules = new int[20][][];
			for(int i = 0; i < 20; i++)
				rules[i] = rulesA[i].toArray(new int[0][]);
			dp1 = Scanner.fill(new int[20][f.length()][f.length()], -1);
			int best = Integer.MAX_VALUE;
			for(int i = 0; i < 20; i++)
				best = Math.min(best, bestPossible(i, 0, f.length() - 1));
			if(best == Integer.MAX_VALUE)
				sc.printLine(-1);
			else
				sc.printLine(best);
		}
	}
}