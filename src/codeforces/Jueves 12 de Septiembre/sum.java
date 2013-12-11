import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sum 
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
	}
	
	static class IncreasingKeyArray <E>
	{
		static class Entry <E>
		{
			long key;
			E value;
			
			Entry(long k, E v)
			{
				key = k;
				value = v;
			}
		}
		
		Entry <E> [] array;
		int currentSize = 0;

		@SuppressWarnings("unchecked")
		public IncreasingKeyArray(int maxSize)
		{
			array = new Entry[maxSize];
		}
		
		void add(long key, E val)
		{
			int index = currentSize - 1;
			while(index >= 0 && key <= array[index].key)
				index--;
			index++;
			array[index] = new Entry <E> (key, val);
			currentSize = index + 1;
		}
		
		E getFirstLessOrEqual(long key)
		{
			if(array[0].key > key) return null;
			int from = 0;
			int to = currentSize - 1;
			while(true)
			{
				if(from == to)
					return array[from].key <= key ? array[from].value : null;
				if(from + 1 == to)
					return array[to].key <= key ? array[to].value : array[from].key <= key ? array[from].value : null;
				int mid = (from + to) >>> 1;
				if(array[mid].key <= key)
					from = mid;
				else
					to = mid - 1;
			}
		}
		
		void clear()
		{
			currentSize = 0;
		}
	}
	
	static int[][] transpose(int[][] matrix)
	{
		int[][] answer = new int[matrix[0].length][matrix.length];
		for(int i = 0; i < matrix.length; i++)
			for(int j = 0; j < matrix[0].length; j++)
				answer[j][i] = matrix[i][j];
		return answer;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
//		System.setIn(new FileInputStream("sum.in"));
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 0; caso < t; caso++)
		{
			int r = sc.nextInt();
			int c = sc.nextInt();
			int ro = r;
			int co = c;
			long s = sc.nextInt();
			int[][] matrix = sc.nextIntMatrix(r, c);
			if(r < c)
			{
				matrix = transpose(matrix);
				int tmp = r;
				r = c;
				c = tmp;
			}
			long[][] rowSum = new long[r][c];
			for(int i = 0; i < r; i++)
			{
				rowSum[i][0] = matrix[i][0];
				for(int j = 1; j < c; j++)
					rowSum[i][j] = rowSum[i][j - 1] + matrix[i][j];
			}
			int best = Integer.MAX_VALUE;
			IncreasingKeyArray <Integer> increasing = new IncreasingKeyArray <Integer> (r + 1);
			for(int i = 0; i < c; i++)
			{
				for(int j = i; j < c; j++)
				{
					int len = j - i + 1;
					if(len > best) break;		
					increasing.clear();
					increasing.add(0, -1);
					long acum = 0;
					for(int k = 0; k < r; k++)
					{
						long valsK = rowSum[k][j] - (i == 0 ? 0 : rowSum[k][i - 1]);
						acum += valsK;
						if(valsK >= s)
							best = Math.min(best, len);
						Integer posible = increasing.getFirstLessOrEqual(acum - s);
						if(posible != null)
						{
							int dist = k - posible;
							best = Math.min(best, dist * len);
						}
						increasing.add(acum, k);
					}
				}
			}
			System.out.println(best == Integer.MAX_VALUE ? -1 : best);
		}
	}
}