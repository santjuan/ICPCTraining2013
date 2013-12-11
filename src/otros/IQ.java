import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class IQ 
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

	static boolean check(int[] vals, long... coefs)
	{
		for(int i = coefs.length; i < vals.length; i++)
		{
			int predicted = 0;
			for(int j = 0, k = i - 1; j < coefs.length; j++, k--)
				predicted += coefs[j] * vals[k];
			if(predicted != vals[i])
				return false;
		}
		return true;
	}
	
	static int getNext(int[] vals, long... coefs)
	{
		int predicted = 0;
		for(int j = 0, k = vals.length - 1; j < coefs.length; j++, k--)
			predicted += coefs[j] * vals[k];
		return predicted;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int cases = sc.nextInt();
		outer:
		for(int thisCase = 0; thisCase < cases; thisCase++)
		{
			int n = sc.nextInt();
			int[] vals = sc.nextIntArray(n);
			if((vals[1] % vals[0]) == 0)
			{
				if(check(vals, vals[1] / vals[0]))
				{
					System.out.println(getNext(vals, vals[1] / vals[0]));
					continue outer;
				}
			}
			for(long a = -1000; a <= 1000; a++)
			{
				long b = vals[2] - a * vals[1];
				if((b % vals[0]) != 0)
					continue;
				b /= vals[0];
				if(check(vals, a, b))
				{
					System.out.println(getNext(vals, a, b));
					continue outer;
				}
			}
			double[] valsCopy = new double[vals.length];
			for(int i = 0; i < vals.length; i++)
				valsCopy[i] = vals[i];
			double down = (-(valsCopy[2] * valsCopy[2]) / (valsCopy[1] * valsCopy[1]) + valsCopy[3] / valsCopy[1]);
			if(down == 0)
			{
				for(long c = -1000; c <= 1000; c++)
				{
					for(long a = -1000; a <= 1000; a++)
					{
						long b = (int) (vals[4] - a * vals[3] - c * vals[1]);
						if((b % vals[2]) != 0)
							continue;
						b /= vals[2];
						if(check(vals, a, b, c))
						{
							System.out.println(getNext(vals, a, b, c));
							continue outer;
						}
					}
				}
			}
			for(long c = -1000; c <= 1000; c++)
			{
				double aD = valsCopy[4] / valsCopy[1] - c - (valsCopy[3] * valsCopy[2]) / (valsCopy[1] * valsCopy[1]) + c * (valsCopy[0] * valsCopy[2]) / (valsCopy[1] * valsCopy[1]);
				aD /= down;
				long a = Math.round(aD);
				long b = (int) (vals[4] - a * vals[3] - c * vals[1]);
				if((b % vals[2]) != 0)
					continue;
				b /= vals[2];
				if(check(vals, a, b, c))
				{
					System.out.println(getNext(vals, a, b, c));
					continue outer;
				}
			}
			throw new RuntimeException();
		}
	}
}