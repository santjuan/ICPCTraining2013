import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class Tutantobby2 
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
	
	
	static final int[] bitCount = new int[1 << 16];
	
	static 
	{
		for(int i = 0; i < bitCount.length; i++)
			bitCount[i] = Integer.bitCount(i);
	}
	
	static class BitSet
	{
		final long[] bits = new long[(1000001) / 64]; 
		int currentBit = -1;
		
		void clear()
		{
			currentBit = -1;
		}
		
		void clearAndErase(int size)
		{
			currentBit = -1;
			final int lastSet = (size - 1) / 64;
			for(int i = 0; i <= lastSet; i++)
				bits[i] = 0L;
		}
		
		void set(int index)
		{
			bits[index / 64] |= 1L << (index % 64);
		}
		
		void shiftLeftAndPut(boolean first)
		{
			int nextBit = currentBit + 1;
			boolean start = (nextBit % 64) == 0;
			if(start)
				bits[nextBit / 64] = 0L;
			final int lastSet = Math.min(bits.length - 1, currentBit / 64 + (start ? 1 : 0));
			long carry = first ? 1L : 0L;
			for(int i = 0; i < lastSet; i++)
			{
				long nextCarry = bits[i] >> 63;
				bits[i] = (bits[i] << 1) | carry;
				carry = nextCarry;
			}
			bits[lastSet] = (bits[lastSet] << 1) | carry;
			currentBit = nextBit;
		}
		
		final int bitCountMask = (1 << 16) - 1;
		
		int bitCount(long value)
		{
			return bitCount[(int) (value >>> 48)] + bitCount[(int) ((value >> 32) & bitCountMask)] + bitCount[(int) ((value >> 16) & bitCountMask)] + bitCount[(int) (value & bitCountMask)];
		}
		
		int andCount(BitSet other)
		{
			final long[] array = other.bits;
			final int maxIndex = currentBit / 64;
			int total = 0;
			for(int i = 0; i <= maxIndex; i++)
				total += bitCount(array[i] & bits[i]);
			return total;
		}
	}
	
	static final BitSet[] tmpSets = new BitSet[]{new BitSet(), new BitSet(), new BitSet(), new BitSet()};
	
	
	static int[] getBest(final char[] a, final char[] b, boolean equal)
	{

		int bestShift = -b.length;
		int bestCount = 0;
		for(int i = 0; i < b.length; i++)
		{
			int count = 0;
			int startA = 0;
			int startB = b.length - (i + 1);
			while(startA < a.length && startB < b.length)
				count += a[startA++] == b[startB++] ? 1 : 0;
			if(equal ? (count >= bestCount) : (count > bestCount))
			{
				bestShift = -b.length + (i + 1);
				bestCount = count;
			}
		}
		return new int[]{bestShift, bestCount};
	}
	
	static String generateShift(final char[] as, final char[] bs, int shift)
	{
		int currentA = shift;
		int currentB = 0;
		char[] ans = new char[-shift + Math.max(as.length, bs.length + shift)];
		int current = 0;
		while(currentA < as.length || currentB < bs.length)
		{
			if(currentB == bs.length)
				ans[current++] = as[currentA++];
			else if(currentA == as.length)
				ans[current++] = bs[currentB++];
			else if(currentA < 0)
			{
				ans[current++] = bs[currentB++];
				currentA++;
			}
			else
			{
				ans[current++] = as[currentA] == bs[currentB] ? as[currentA] : 'X';
				currentA++;
				currentB++;
			}
		}
		return new String(ans);
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		System.setIn(new FileInputStream("dna2.in"));
		System.setOut(new PrintStream("dna.santi"));
		Scanner sc = new Scanner();
		final BitSet[] asSets = new BitSet[4];
		for(int i = 0; i < asSets.length; i++)
			asSets[i] = new BitSet();
		while(true)
		{
			String start = sc.nextLine();
			if(start == null)
				return;
			char[] a = start.toCharArray();
			char[] b = sc.nextLine().toCharArray();
			sc.nextLine();
			int[] bests = new int[]{0, -1};
			int indexAnswer = -1;
			for(int index = 0; index < 2; index++)
			{
				boolean[][] bs = new boolean[4][b.length];
				for(int i = 0; i < b.length; i++)
				{
					switch(b[i])
					{
						case 'A': bs[0][i] = true; break;
						case 'G': bs[1][i] = true; break;
						case 'T': bs[2][i] = true; break;
						case 'C': bs[3][i] = true; break;
					}
				}
				for(int i = 0; i < 4; i++)
					asSets[i].clearAndErase(a.length);
				for(int i = 0; i < a.length; i++)
				{
					switch(a[i])
					{
						case 'A': asSets[0].set(i); break;
						case 'G': asSets[1].set(i); break;
						case 'T': asSets[2].set(i); break;
						case 'C': asSets[3].set(i); break;
					}
				}
				int[] possible = getBest(a, b, index == 0);
				if(possible[1] >= bests[1])
				{
					 bests[0] = possible[0];
					 bests[1] = possible[1];
					 indexAnswer = index;
				}
				char[] tmp = a;
				a = b;
				b = tmp;
			}
			if(indexAnswer == 1)
			{
				char[] tmp = a;
				a = b;
				b = tmp;
			}
			System.out.println(bests[1]);
			if(bests[1] == 0)
				System.out.println("No matches");
			else
				System.out.println(generateShift(a, b, bests[0]));
			System.out.println();
		}
	}
}