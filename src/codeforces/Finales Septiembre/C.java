import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class C 
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
	}

	static class Hexagon
	{
		final int[] original;
		final int[][] rotations;
		final int[][] rotateTo;
		int[] current;

		Hexagon(int[] values)
		{
			rotations = new int[6][];
			original = rotations[0] = values.clone();
			for(int i = 1; i < 6; i++)
			{
				rotate(values);
				rotations[i] = values.clone();
			}
			rotateTo = new int[6][7];
			for(int i = 0; i < rotations.length; i++)
				for(int j = 0; j < rotations[i].length; j++)
					rotateTo[j][rotations[i][j]] = i;
			current = original;
		}
		
		void rotate(int[] array)
		{
			int primero = array[0];
			for(int i = 1; i < array.length; i++)
				array[i - 1] = array[i];
			array[array.length - 1] = primero;
		}

		void rotateTo(int where, int val)
		{
			current = rotations[rotateTo[where][val]];
		}
	}

	static class Restriction
	{
		final int fromWhere;
		final int fromIndex;
		final int toWhere;
		final int toIndex;

		Restriction(int fW, int fI, int tW, int tI)
		{
			fromWhere = fW;
			fromIndex = fI;
			toWhere = tW;
			toIndex = tI;
		}

		boolean cumple(Hexagon[] all)
		{
			return all[fromWhere].current[fromIndex] == all[toWhere].current[toIndex];
		}
	}

	static Restriction[] allRestrictions = new Restriction[]{
		new Restriction(1, 1, 2, 4),
		new Restriction(1, 3, 6, 0),
		new Restriction(2, 2, 3, 5),
		new Restriction(3, 3, 4, 0),
		new Restriction(4, 4, 5, 1),
		new Restriction(5, 5, 6, 2),
	};

	static Restriction[] restrictionZero = new Restriction[]{
		new Restriction(0, 5, 1, 2),
		new Restriction(0, 1, 3, 4),
		new Restriction(0, 2, 4, 5),
		new Restriction(0, 3, 5, 0),
		new Restriction(0, 4, 6, 1),
		new Restriction(0, 0, 2, 3),
	};
	
	static boolean isOk(Hexagon[] permutation)
	{
		permutation[0].current = permutation[0].original;
		for(Restriction r : restrictionZero)
			permutation[r.toWhere].rotateTo(r.toIndex, permutation[0].current[r.fromIndex]);
		for(Restriction r : allRestrictions)
		{
			if(!r.cumple(permutation))
				return false;
		}
		return true;
	}
	
	static ArrayList <int[]> allPermutations = new ArrayList <int[]> ();
	
	static void permutate(int[] permutation, boolean[] used, int n)
	{
		if(n == permutation.length)
		{
			allPermutations.add(permutation.clone());
			return;
		}
		else
		{
			for(int i = 0; i < permutation.length; i++)
			{
				if(!used[i])
				{
					permutation[n] = i;
					used[i] = true;
					permutate(permutation, used, n + 1);
					used[i] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Hexagon[] hexagons = new Hexagon[7];
		Hexagon[] permutation = new Hexagon[7];
		int[] values = new int[6];
		int[][] permutations = new int[5040][];
		permutate(new int[7], new boolean[7], 0);
		int currentId = 0;
		for(int[] p : allPermutations)
			permutations[currentId++] = p;
		allPermutations = null;
		while(true)
		{
			for(int i = 0; i < 7; i++)
			{
				Integer primero = sc.nextInteger();
				if(primero == null)
				{
					bw.flush();
					return;
				}
				values[0] = primero;
				for(int j = 1; j < 6; j++)
					values[j] = sc.nextInt();
				hexagons[i] = new Hexagon(values);
			}
			boolean ok = false;
			for(int i = 0; i < 5040; i++)
			{
				for(int j = 0; j < 7; j++)
					permutation[j] = hexagons[permutations[i][j]];
				if(isOk(permutation))
				{
					bw.write("YES");
					ok = true;
					break;
				}
			}
			if(!ok)
				bw.write("NO");
			bw.write("\n");
		}
	}
}