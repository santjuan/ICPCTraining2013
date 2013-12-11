import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class Rings 
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
	
	
	static int getIndex(long number, int rings)
	{
		if(number == 0)
			return -3;
		if(number > rings || number < -rings)
			return -2;
		return (int) (Math.abs(number) - 1);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int g = sc.nextInt();
		for(int problem = 1; problem <= g; problem++)
		{
			int rings = sc.nextInt();
			int runes = sc.nextInt();
			int[] allRunesValsPos = new int[runes];
			int[] allRunesValsNeg = new int[runes];
			int worstPaila = 0;
			for(int i = 0; i < runes; i++)
			{
				long a = sc.nextLong();
				long b = sc.nextLong();
				long c = sc.nextLong();
				boolean signoA = a >= 0;
				boolean signoB = b >= 0;
				boolean signoC = c >= 0;
				int indexA = getIndex(a, rings);
				int indexB = getIndex(b, rings);
				int indexC = getIndex(c, rings);
				sc.nextLong();
				if(indexA < 0 || indexB < 0 || indexC < 0)
				{
					worstPaila = Math.min(worstPaila, indexA);
					worstPaila = Math.min(worstPaila, indexB);
					worstPaila = Math.min(worstPaila, indexC);
				}
				else if(indexA == indexB || indexA == indexC || indexB == indexC)
					worstPaila = Math.min(worstPaila, -1);
				else
				{
					if(signoA)
						allRunesValsPos[i] |= 1 << indexA;
					else
						allRunesValsNeg[i] |= 1 << indexA;
					if(signoB)
						allRunesValsPos[i] |= 1 << indexB;
					else
						allRunesValsNeg[i] |= 1 << indexB;
					if(signoC)
						allRunesValsPos[i] |= 1 << indexC;
					else
						allRunesValsNeg[i] |= 1 << indexC;
				}
			}
			if(worstPaila < 0)
			{
				if(worstPaila == -3)
					System.out.println("INVALID: NULL RING");
				else if(worstPaila == -2)
					System.out.println("INVALID: RING MISSING");
				else if(worstPaila == -1)
					System.out.println("INVALID: RUNE CONTAINS A REPEATED RING");
			}
			else
			{
				final int limite = 1 << rings;
				boolean wasOk = false;
				outer:
				for(int mask = 0; mask < limite; mask++)
				{
					int maskInverse = (~mask) & (limite - 1);
					for(int i = 0; i < runes; i++)
					{
						boolean ok = false;
						if((allRunesValsPos[i] & mask) != 0)
							ok = true;
						if((allRunesValsNeg[i] & maskInverse) != 0)
							ok = true;
						if(!ok)
							continue outer;
					}
					wasOk = true;
					break;
				}
				if(wasOk)
					System.out.println("RUNES SATISFIED!");
				else
					System.out.println("RUNES UNSATISFIABLE! TRY ANOTHER GATE!");
			}
		}
	}
}