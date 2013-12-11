import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CcplG 
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

	static class Node
	{
		Node[] allAdjacent;
		int bitId;
		long bitIdPut;
		boolean isFirst;
		boolean isLast;
		int columnId;
		char letter;
		
		Node(char letter, int bitId, int columnId, boolean isFirst, boolean isLast)
		{
			this.bitId = bitId;
			this.bitIdPut = 1L << bitId;
			this.columnId = columnId;
			this.isFirst = isFirst;
			this.isLast = isLast;
			this.letter = letter;
		}
		
		void buildAdjacents(Node[][] allNodes, int rowId, int colId)
		{
			ArrayList <Node> adjacentsA = new ArrayList <Node> ();
			if(rowId != 0)
			{
				if(colId != 0)
					adjacentsA.add(allNodes[rowId - 1][colId - 1]);
				if(colId != allNodes[0].length - 1)
					adjacentsA.add(allNodes[rowId - 1][colId + 1]);
			}
			if(rowId != allNodes.length - 1)
			{
				if(colId != 0)
					adjacentsA.add(allNodes[rowId + 1][colId - 1]);
				if(colId != allNodes[0].length - 1)
					adjacentsA.add(allNodes[rowId + 1][colId + 1]);
			}
			if(colId >= 2 && allNodes[rowId][colId - 2] != null)
				adjacentsA.add(allNodes[rowId][colId - 2]);
			if(colId + 2 < allNodes[0].length && allNodes[rowId][colId + 2] != null)
				adjacentsA.add(allNodes[rowId][colId + 2]);
			allAdjacent = adjacentsA.toArray(new Node[0]);
		}
	}
	
	static Node[][] allNodes;
	static Node[] byBit;
	static int n;
	static TreeSet <String> viablePatterns;
	
	static void generateViablePatterns(long visited, int currentId, char[] currentBuilt, int currentSize)
	{
		currentBuilt[currentSize] = byBit[currentId].letter;
		if(currentSize == n - 1)
			viablePatterns.add(new String(currentBuilt));
		else
		{
			for(Node n : byBit[currentId].allAdjacent)
				if((visited & n.bitIdPut) == 0)
					generateViablePatterns(visited ^ n.bitIdPut, n.bitId, currentBuilt, currentSize + 1);
		}
	}
	
	static char[] currentPattern;
	
	static int dp(long mask, int currentId)
	{
		boolean isFirst = byBit[currentId].isFirst;
		boolean visitedLast = false;
		long maskTmp = mask;
		int count = 0;
		for(int i = 0; i < byBit.length; i++)
		{
			if((maskTmp & 1) == 1)
			{
				count++;
				visitedLast |= byBit[i].isLast;
			}
			maskTmp >>= 1;
		}
		if(isFirst && visitedLast && (count >= (currentPattern.length << 1)) && (count % currentPattern.length) == 0)
			return 0;
		char searchedChar = currentPattern[count % currentPattern.length];
		int best = 1000000;
		for(Node a : byBit[currentId].allAdjacent)
			if(a.letter == searchedChar && ((mask & a.bitIdPut) == 0))
				best = Math.min(best, 1 + dp(mask ^ a.bitIdPut, a.bitId));
		return best;
	}
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int rows = sc.nextInt();
			if(rows == 0) return;
			int colsSmall = sc.nextInt();
			int colsLarge = colsSmall + 1;
			n = sc.nextInt();
			allNodes = new Node[rows][(colsSmall << 1) + 1];
			int currentBit = 0;
			for(int i = 0; i < rows; i++)
				for(int j = ((i & 1) == 0 ? 1 : 0); j < allNodes[0].length; j += 2)
					allNodes[i][j] = new Node(sc.next().charAt(0), currentBit++, j, i == 0, i == rows - 1);
			byBit = new Node[currentBit];
			for(int i = 0; i < rows; i++)
				for(int j = ((i & 1) == 0 ? 1 : 0); j < allNodes[0].length; j += 2)
				{
					allNodes[i][j].buildAdjacents(allNodes, i, j);
					byBit[allNodes[i][j].bitId] = allNodes[i][j];
				}
			viablePatterns = new TreeSet <String> ();
			for(int i = 0; i < colsSmall; i++)
				generateViablePatterns(1L << i, i, new char[n], 0);
			String bestPattern = null;
			int bestCount = 1000000;
			for(String s : viablePatterns)
			{
				currentPattern = s.toCharArray();
				int bestAll = 1000000;
				for(int i = 0; i < colsSmall; i++)
				{
					if(byBit[i].letter == currentPattern[0])
						bestAll = Math.min(bestAll, 1 + dp(byBit[i].bitIdPut, byBit[i].bitId));
				}
				if(bestAll < bestCount)
				{
					bestCount = bestAll;
					int times = bestCount / s.length();
					bestPattern = "";
					for(int i = 0; i < times; i++)
						bestPattern += s;
				}
			}
			if(bestCount > 1000)
				System.out.println("no solution");
			else
				System.out.println(bestPattern);
		}
	}
}