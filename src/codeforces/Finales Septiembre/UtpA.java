import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

public class UtpA 
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
	
	static class Prefix
	{
		final char[] val;
		static final char[][] valTmp = new char[9][];
		int size;
		
		Prefix(char[] v, int s)
		{
			val = v;
			size = s;
		}
		
		@Override
		public int hashCode() 
		{
			for(int i = 0; i < size; i++)
				valTmp[size][i] = val[i];
			return Arrays.hashCode(valTmp[size]);
		}
		
		@Override
		public boolean equals(Object o) 
		{
			Prefix other = (Prefix) o;
			if(size != other.size) return false;
			for(int i = 0; i < size; i++)
				if(val[i] != other.val[i])
					return false;
			return true;
		}
	}
	
	static HashSet <Prefix> prefixes = new HashSet <Prefix> ();
	
	static class Node
	{
		Node[] nodes = new Node[26];
		
		void construct(int state, int visited, int height, char[] constructed)
		{
			if(height == 8)
				return;
			if(!prefixes.contains(new Prefix(constructed, height))) return;
			for(int adjacent : adjacents[state])
			{
				if((adjacent & visited) != 0) continue;
				int next = stateChars[adjacent];
				if(nodes[next] == null)
					nodes[next] = new Node();
				constructed[height] = (char) (next + 'A');
				nodes[next].construct(adjacent, visited | adjacent, height + 1, constructed);
			}
		}
		
		boolean findWord(char[] word, int index)
		{
			if(index == word.length) return true;
			if(nodes[word[index] - 'A'] == null) return false;
			return nodes[word[index] - 'A'].findWord(word, index + 1);
		}
	}
	
	static final int[][] adjacentsNormal = new int[][]{{1, 4, 5}, {0, 2, 4, 5, 6}, {1, 3, 5, 6, 7}, {2, 6, 7}, {0, 1, 5, 8, 9}, {0, 1, 2, 4, 6, 8, 9, 10}, {1, 2, 3, 5, 7, 9, 10, 11}, {2, 3, 6, 10, 11}, {4, 5, 9, 12, 13}, {4, 5, 6, 8, 10, 12, 13, 14}, {5, 6, 7, 9, 11, 13, 14, 15}, {6, 7, 10, 14, 15}, {8, 9, 13}, {8, 9, 10, 12, 14}, {9, 10, 11, 13, 15}, {10, 11, 14}};
	static final int[][] adjacents = new int[1 << 16][];
	static final int[] stateChars = new int[1 << 16];
	
	public static void main(String[] args)
	{
		for(int i = 0; i <= 8; i++)
			Prefix.valTmp[i] = new char[i];
		for(int i = 0; i < 16; i++)
		{
			adjacents[1 << i] = new int[adjacentsNormal[i].length];
			for(int j = 0; j < adjacentsNormal[i].length; j++)
				adjacents[1 << i][j] = 1 << adjacentsNormal[i][j];
		}
		Scanner sc = new Scanner();
		int w = sc.nextInt();
		char[][] allEntries = new char[w][];
		for(int i = 0; i < w; i++)
		{
			char[] entry = sc.next().toCharArray();
			for(int j = 0; j <= entry.length; j++)
				prefixes.add(new Prefix(entry, j));
			allEntries[i] = entry;
		}
		int b = sc.nextInt();
		for(int i = 0; i < b; i++)
		{
			int currentId = 0;
			for(int j = 0; j < 4; j++)
				for(char c : sc.next().toCharArray())
					stateChars[1 << currentId++] = c - 'A';
			Node initial = new Node();
			for(int j = 0; j < 16; j++)
				initial.construct(1 << j, 0, 0, new char[10]);
			int[] wordType = new int[9];
			String bestWord = "";
			for(char[] word : allEntries)
			{
				boolean isIn = initial.findWord(word, 0);
				if(isIn)
				{
					wordType[word.length]++;
					String wordS = new String(word);
					if(wordS.length() > bestWord.length())
						bestWord = wordS;
					if(wordS.length() == bestWord.length() && wordS.compareTo(bestWord) < 0)
						bestWord = wordS;
				}
			}
			int total = wordType[3] + wordType[4] + wordType[5] * 2 + wordType[6] * 3 + wordType[7] * 5 + wordType[8] * 11;
			int totalNormal = wordType[0] + wordType[1] + wordType[2] + wordType[3] + wordType[4] + wordType[5] + wordType[6] + wordType[7] + wordType[8];
			System.out.println(total + " " + new String(bestWord) + " " + totalNormal);
		}
	}
}