import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class CcplJ 
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
	
	
	static boolean[][] target;
	static int nTargets;
	
	static class Sokoban
	{
		char[][] board;
		int workerI;
		int workerJ;
		
		public Sokoban(char[][] b)
		{
			target = new boolean[b.length][b[0].length];
			nTargets = 0;
			for(int i = 0; i < b.length; i++)
			{
				for(int j = 0; j < b[0].length; j++)
				{
					if(b[i][j] == '+')
					{
						target[i][j] = true;
						nTargets++;
						b[i][j] = '.';
					}
					else if(b[i][j] == 'B')
					{
						target[i][j] = true;
						nTargets++;
						b[i][j] = 'b';
					}
					else if(b[i][j] == 'W')
					{
						target[i][j] = true;
						nTargets++;
						b[i][j] = '.';
						workerI = i;
						workerJ = j;
					}
					else if(b[i][j] == 'w')
					{
						b[i][j] = '.';
						workerI = i;
						workerJ = j;
					}
				}
				board = b;
			}
		}
		
		void move(int deltaI, int deltaJ)
		{
			if(board[workerI + deltaI][workerJ + deltaJ] == '#') return;
			if(board[workerI + deltaI][workerJ + deltaJ] != 'b') 
			{
				workerI += deltaI;
				workerJ += deltaJ;
			}
			else
			{
				if(board[workerI + 2 * deltaI][workerJ + 2 * deltaJ] != '.') return;
				board[workerI + 2 * deltaI][workerJ + 2 * deltaJ] = 'b';
				board[workerI + deltaI][workerJ + deltaJ] = '.';
				workerI += deltaI;
				workerJ += deltaJ;
			}
		}
		
		boolean isTarget()
		{
			int targetsInPlace = 0;
			for(int i = 0; i < board.length; i++)
				for(int j = 0; j < board[0].length; j++)
					if(board[i][j] == 'b' && target[i][j])
						targetsInPlace++;
			return targetsInPlace == nTargets;
		}
		
		void print()
		{
			for(int i = 0; i < board.length; i++)
			{
				for(int j = 0; j < board[0].length; j++)
				{
					char current = board[i][j];
					boolean isTarget = target[i][j];
					if(workerI == i && workerJ == j)
					{
						if(isTarget) current = 'W';
						else current = 'w';
					}
					else if(current == 'b')
					{
						if(isTarget) current = 'B';
						else current = 'b';
					}
					else if(isTarget)
						current = '+';
					System.out.print(current);
				}
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int game = 0;
		while(true)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n == 0 && m == 0) return;
			char[][] board = sc.nextGrid(n);
			Sokoban sokoban = new Sokoban(board);
			for(char c : sc.next().toCharArray())
			{
				if(sokoban.isTarget()) break;
				if(c == 'U')
					sokoban.move(-1, 0);
				else if(c == 'D')
					sokoban.move(1, 0);
				else if(c == 'L')
					sokoban.move(0, -1);
				else if(c == 'R')
					sokoban.move(0, 1);
			}
			System.out.println("Game " + ++game + ": " + (sokoban.isTarget() ? "" : "in") + "complete");
			sokoban.print();
		}
	}
}