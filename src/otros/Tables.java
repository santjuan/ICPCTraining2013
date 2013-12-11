import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Tables 
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

	
	static class Table
	{
		boolean[][] used;
		boolean[][] starting;
		String[][] ids;
		int[][] idsReal;
		int currentId = 0;
		
		void markUsed(int i, int j, int rows, int cols, String id)
		{
			starting[i][j] = true;
			ids[i][j] = id;
			idsReal[i][j] = currentId++;
			for(int a = 0; a < rows; a++)
				for(int b = 0; b < cols; b++)
				{
					used[a + i][b + j] = true;
					idsReal[a + i][b + j] = idsReal[i][j];
				}
		}
		
		Table(Scanner sc)
		{
			int rows = sc.nextInt();
			if(rows == 0)
				return;
			int colsFirst = sc.nextInt();
			int[] first = sc.nextIntArray(colsFirst * 2);
			int cols = 0;
			for(int i = 0; i < colsFirst; i++)
				cols += first[2 * i + 1];
			used = new boolean[rows][cols];
			starting = new boolean[rows][cols];
			ids = new String[rows][cols];
			idsReal = new int[rows][cols];
			LinkedList <Integer> pending = new LinkedList <Integer> ();
			for(int i : first)
				pending.add(i);
			for(int i = 0; i < rows; i++)
			{
				if(i != 0)
				{
					pending.clear();
					int n = sc.nextInt();
					for(int x : sc.nextIntArray(2 * n))
						pending.add(x);
				}
				int currentCol = 0;
				int curentPending = 1;
				while(!pending.isEmpty())
				{
					if(!used[i][currentCol])
					{
						int rs = pending.pollFirst();
						int cs = pending.pollFirst();
						markUsed(i, currentCol, rs, cs, (i + 1) + "" + (currentCol + 1));
					}
					currentCol++;
				}
			}
		}
		
		void print(String salida)
		{
			while(salida.length() >= 1 && salida.charAt(salida.length() - 1) == ' ')
				salida = salida.substring(0, salida.length() - 1);
			while(salida.length() < 3 * ids[0].length + 1)
				salida = salida + " ";
			System.out.println(salida);
		}
		
		void printTable()
		{
			StringBuilder firstTop = null;
			for(int i = 0; i < used.length; i++)
			{
				StringBuilder top = new StringBuilder();
				StringBuilder bottom = new StringBuilder();
				for(int j = 0; j < used[0].length; j++)
				{
					top.append(' ');
					if(j == 0 || idsReal[i][j] != idsReal[i][j - 1])
						bottom.append('|');
					else
						bottom.append(' ');
					if(i == 0 || idsReal[i][j] != idsReal[i - 1][j])
					{
						top.append('-');
						top.append('-');
					}
					else
					{
						top.append(' ');
						top.append(' ');
					}
					if(starting[i][j])
					{
						bottom.append(ids[i][j].charAt(0));	
						bottom.append(ids[i][j].charAt(1));
					}
					else
					{
						bottom.append(' ');
						bottom.append(' ');
					}
				}
				bottom.append('|');
				if(firstTop == null)
					firstTop = top;
				print(top.toString());
				print(bottom.toString());
			}
			print(firstTop.toString());
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		System.setOut(new PrintStream("s.txt"));
		Scanner sc = new Scanner();
		while(true)
		{
			Table table = new Table(sc);
			if(table.ids == null)
			{
				System.out.flush();
				System.out.close();
				return;
			}
			table.printTable();
			System.out.println();
		}
	}
}