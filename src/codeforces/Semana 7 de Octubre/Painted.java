import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class Painted 
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
	
	static class IntHashMap
	{
		private Entry[] table;

	    private int count;

	    private int threshold;

	    private final float loadFactor;

	    private static class Entry {
	        final int hash;
	        int value;
	        Entry next;

	        private Entry(int hash, int key, int value, Entry next) {
	            this.hash = hash;
	            this.value = value;
	            this.next = next;
	        }
	    }
	    
	    public IntHashMap() {
	        this(20, 0.75f);
	    }

	    public IntHashMap(int initialCapacity, float loadFactor) 
	    {
	        this.loadFactor = loadFactor;
	        table = new Entry[initialCapacity];
	        threshold = (int) (initialCapacity * loadFactor);
	    }

	    public int get(int key) {
	        Entry tab[] = table;
	        int hash = key;
	        int index = (hash & 0x7FFFFFFF) % tab.length;
	        for (Entry e = tab[index]; e != null; e = e.next) {
	            if (e.hash == hash) {
	                return e.value;
	            }
	        }
	        return Integer.MAX_VALUE;
	    }

	    protected void rehash() {
	        int oldCapacity = table.length;
	        Entry oldMap[] = table;

	        int newCapacity = oldCapacity * 2 + 1;
	        Entry newMap[] = new Entry[newCapacity];

	        threshold = (int) (newCapacity * loadFactor);
	        table = newMap;

	        for (int i = oldCapacity; i-- > 0;) {
	            for (Entry old = oldMap[i]; old != null;) {
	                Entry e = old;
	                old = old.next;
	                int index = (e.hash & 0x7FFFFFFF) % newCapacity;
	                e.next = newMap[index];
	                newMap[index] = e;
	            }
	        }
	    }

	    public void increase(int key) 
	    {
	        Entry tab[] = table;
	        int hash = key;
	        int index = (hash & 0x7FFFFFFF) % tab.length;
	        for (Entry e = tab[index]; e != null; e = e.next) {
	            if (e.hash == hash) {
	                Object old = e.value;
	                e.value++;
	                return;
	            }
	        }

	        if (count >= threshold) {
	            // Rehash the table if the threshold is exceeded
	            rehash();

	            tab = table;
	            index = (hash & 0x7FFFFFFF) % tab.length;
	        }

	        // Creates the new entry.
	        Entry e = new Entry(hash, key, 1, tab[index]);
	        tab[index] = e;
	        count++;
	        return;
	    }
	    
	    public void put(int key, int newVal) 
	    {
	        Entry tab[] = table;
	        int hash = key;
	        int index = (hash & 0x7FFFFFFF) % tab.length;
	        for (Entry e = tab[index]; e != null; e = e.next) {
	            if (e.hash == hash) {
	                Object old = e.value;
	                e.value = newVal;
	                return;
	            }
	        }

	        if (count >= threshold) {
	            // Rehash the table if the threshold is exceeded
	            rehash();

	            tab = table;
	            index = (hash & 0x7FFFFFFF) % tab.length;
	        }

	        // Creates the new entry.
	        Entry e = new Entry(hash, key, newVal, tab[index]);
	        tab[index] = e;
	        count++;
	        return;
	    }
	}
	
	static class FastArrayDeque
	{
		final int[] queue;
		int head, tail;
		
		FastArrayDeque(int size)
		{
			queue = new int[size];
			clear();
		}
		
		
		void add(int val)
		{
			queue[++tail] = val;
		}
		
		int poll()
		{
			return queue[head++];
		}
		
		void clear()
		{
			head = 0; tail = -1;
		}
		
		boolean isEmpty()
		{
			return head > tail;
		}
	}
	
//	static class Faces
//	{
//		static final int clearMask = ~(((1 << 6) - 1) << 20);
//		
//		final boolean front;
//		final boolean back;
//		final boolean left;
//		final boolean right;
//		final boolean top;
//		boolean bottom;
//		
//		Faces(int state)
//		{
//			state >>= 20;
//			front = (state & 1) == 1;
//			state >>= 1;
//			back = (state & 1) == 1;
//			state >>= 1;
//			left = (state & 1) == 1;
//			state >>= 1;
//			right = (state & 1) == 1;
//			state >>= 1;
//			top = (state & 1) == 1;
//			state >>= 1;
//			bottom = (state & 1) == 1;
//		}
//
//		
//		int right(int state)
//		{
//			int mask = 0;
//			if(right)
//				mask |= 1;
//			mask <<= 1;
//			if(left)
//				mask |= 1;
//			mask <<= 1;
//			if(top)
//				mask |= 1;
//			mask <<= 1;
//			if(bottom)
//				mask |= 1;
//			mask <<= 1;
//			if(back)
//				mask |= 1;
//			mask <<= 1;
//			if(front)
//				mask |= 1;
//	//		int mask = (right << 5) | (left << 4) | (top << 3) | (bottom << 2) | (back << 1) | front;
//			return (state & clearMask) | (mask << 20);
//		}
//		
//		int left(int state)
//		{
//			int mask = 0;
//			if(left)
//				mask |= 1;
//			mask <<= 1;
//			if(right)
//				mask |= 1;
//			mask <<= 1;
//			if(bottom)
//				mask |= 1;
//			mask <<= 1;
//			if(top)
//				mask |= 1;
//			mask <<= 1;
//			if(back)
//				mask |= 1;
//			mask <<= 1;
//			if(front)
//				mask |= 1;
//			// int mask = (left << 5) | (right << 4) | (bottom << 3) | (top << 2) | (back << 1) | front;
//			return (state & clearMask) | (mask << 20);
//		}
//		
//		int up(int state)
//		{
//			int mask = 0;
//			if(back)
//				mask |= 1;
//			mask <<= 1;
//			if(front)
//				mask |= 1;
//			mask <<= 1;
//			if(right)
//				mask |= 1;
//			mask <<= 1;
//			if(left)
//				mask |= 1;
//			mask <<= 1;
//			if(top)
//				mask |= 1;
//			mask <<= 1;
//			if(bottom)
//				mask |= 1;
////			int mask = (back << 5) | (front << 4) | (right << 3) | (left << 2) | (top << 1) | bottom;
//			return (state & clearMask) | (mask << 20);
//		}
//		
//		int down(int state)
//		{
//			int mask = 0;
//			if(front)
//				mask |= 1;
//			mask <<= 1;
//			if(back)
//				mask |= 1;
//			mask <<= 1;
//			if(right)
//				mask |= 1;
//			mask <<= 1;
//			if(left)
//				mask |= 1;
//			mask <<= 1;
//			if(bottom)
//				mask |= 1;
//			mask <<= 1;
//			if(top)
//				mask |= 1;
////			int mask = (front << 5) | (back << 4) | (right << 3) | (left << 2) | (bottom << 1) | top;
//			return (state & clearMask) | (mask << 20);
//		}
//	}
	

//	boolean front;
//	boolean back;
//	boolean left;
//	boolean right;
//	boolean top;
//	boolean bottom;
	
	static int right(final int faces)
	{
		int mask = 0;
		mask |= ((faces >> 0) & 1) << 0;
		mask |= ((faces >> 1) & 1) << 1;
		mask |= ((faces >> 5) & 1) << 2;
		mask |= ((faces >> 4) & 1) << 3;
		mask |= ((faces >> 2) & 1) << 4;
		mask |= ((faces >> 3) & 1) << 5;
//		int mask = (right << 5) | (left << 4) | (top << 3) | (bottom << 2) | (back << 1) | front;
		return mask;
	}
	
	

	static int left(final int faces)
	{
		int mask = 0;
		mask |= ((faces >> 0) & 1) << 0;
		mask |= ((faces >> 1) & 1) << 1;
		mask |= ((faces >> 4) & 1) << 2;
		mask |= ((faces >> 5) & 1) << 3;
		mask |= ((faces >> 3) & 1) << 4;
		mask |= ((faces >> 2) & 1) << 5;
	 // int mask = (left << 5) | (right << 4) | (bottom << 3) | (top << 2) | (back << 1) | front;
		return mask;
	}
	
	static int up(final int faces)
	{
		int mask = 0;
		mask |= ((faces >> 5) & 1) << 0;
		mask |= ((faces >> 4) & 1) << 1;
		mask |= ((faces >> 2) & 1) << 2;
		mask |= ((faces >> 3) & 1) << 3;
		mask |= ((faces >> 0) & 1) << 4;
		mask |= ((faces >> 1) & 1) << 5;
//		int mask = (back << 5) | (front << 4) | (right << 3) | (left << 2) | (top << 1) | bottom;
		return mask;
	}
	
	static int down(final int faces)
	{
		int mask = 0;
		mask |= ((faces >> 4) & 1) << 0;
		mask |= ((faces >> 5) & 1) << 1;
		mask |= ((faces >> 2) & 1) << 2;
		mask |= ((faces >> 3) & 1) << 3;
		mask |= ((faces >> 1) & 1) << 4;
		mask |= ((faces >> 0) & 1) << 5;
//		int mask = (front << 5) | (back << 4) | (right << 3) | (left << 2) | (bottom << 1) | top;
		return mask;
	}
	
	static int getI(int state)
	{
		int id = state >>> 26;
		return idPosition[id][0];
	}
	
	static int getJ(int state)
	{
		int id = state >>> 26;
		return idPosition[id][1];
	}
	
	static int idMask = ((1 << 5) - 1) << 26;
	
	static int setPosition(int state, int id)
	{
		int idM = state & idMask;
		state ^= idM;
		return state | (id << 26);
	}
	
	static final int[][] diffs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	
	static final int putBottom = 1 << 5;
	static final int clearBottom = ~putBottom;
	static final int clearMask = ~(((1 << 6) - 1) << 20);
	static final int clearId = ~(((1 << 5) - 1) << 26);
	static int[][] idSquares;
	static int[][] idPosition;
	static IntHashMap visited;
	static FastArrayDeque queue = new FastArrayDeque(5000000);
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		boolean termino = false;
		final int[][] moveMemo = new int[4][64];
		long time = 0;
		for(int i = 0; i < 64; i++)
		{
			moveMemo[0][i] = left(i);
			moveMemo[1][i] = right(i);
			moveMemo[2][i] = up(i);
			moveMemo[3][i] = down(i);
		}
		int nTrece = 0;
		while(!termino)
		{
			String line = sc.nextLine();
			if(time == 0)
				time = System.currentTimeMillis();
			if(line == null) break;
			ArrayList <char[]> matrixA = new ArrayList <char[]> ();
			matrixA.add(line.trim().toCharArray());
			while(true)
			{
				String l = sc.nextLine();
				if(l == null)
				{
					termino = true;
					break;
				}
				else if(l.trim().isEmpty())
					break;
				matrixA.add(l.trim().toCharArray());
			}
			char[][] matrix = new char[matrixA.size()][];
			for(int i = 0; i < matrixA.size(); i++)
				matrix[i] = matrixA.get(i);
			idSquares = Scanner.fill(new int[matrix.length][matrix[0].length], -1);
			int currentId = 0;
			int currentState = 0;
			int startingId = 0;
			int endingId = 0;
			for(int i = 0; i < matrix.length; i++)
				for(int j = 0; j < matrix[0].length; j++)
				{
					if(matrix[i][j] == 'P')
					{
						idSquares[i][j] = currentId++;
						currentState |= 1 << idSquares[i][j];
					}
					else if(matrix[i][j] == 'C')
					{
						idSquares[i][j] = currentId++;
						startingId = currentId - 1;
					}
					else if(matrix[i][j] == 'G')
					{
						idSquares[i][j] = currentId++;
						endingId = currentId - 1;
					}
					else if(matrix[i][j] == '.')
						idSquares[i][j] = currentId++;
				}
			idPosition = new int[currentId][2];
			for(int i = 0; i < matrix.length; i++)
				for(int j = 0; j < matrix[0].length; j++)
					if(idSquares[i][j] >= 0)
					{
						idPosition[idSquares[i][j]][0] = i;
						idPosition[idSquares[i][j]][1] = j;
					}
			currentState = setPosition(currentState, startingId);
			visited = new IntHashMap();
			queue.clear();
			queue.add(currentState);
			visited.put(currentState, 0);
			int answer = Integer.MAX_VALUE;
			final int allButBottom = ((1 << 6) - 1) & clearBottom;
			while(!queue.isEmpty())
			{
				int state = queue.poll();
				int steps = visited.get(state);
				int i = getI(state);
				int j = getJ(state);
				int id = idSquares[i][j];
				int facesState = (state >> 20) & ((1 << 6) - 1);
				if(id == endingId)
				{
					boolean inPosition = ((state >> id) & 1) == 1;
					boolean all = (facesState == allButBottom) && inPosition;
					if(all)
					{
						answer = steps;
						break;
					}
				}
				boolean bottom = ((facesState >> 5) & 1) == 1;
				boolean inSquare = ((state >> id) & 1) == 1;
				if(bottom && !inSquare)
					facesState &= clearBottom;
				else if(!bottom && inSquare)
					facesState |= putBottom;
				else if(bottom)
					facesState |= putBottom;
				else
					facesState &= clearBottom;
				if(bottom && !inSquare)
					state |= (1 << id);
				else if(!bottom && inSquare)
					state &= ~(1 << id);
				for(int k = 0; k < diffs.length; k++)
				{
					int iN = i + diffs[k][0];
					int jN = j + diffs[k][1];
					if(iN < 0 || iN >= idSquares.length || jN < 0 || jN >= idSquares[0].length || idSquares[iN][jN] < 0)
						continue;
					int newState = (state & clearMask) | (moveMemo[k][facesState] << 20);
					newState = (newState & clearId) | (idSquares[iN][jN] << 26);
					if(visited.get(newState) != Integer.MAX_VALUE) continue;
					else
					{
						queue.add(newState);
						visited.put(newState, steps + 1);
					}
				}
			}
			if(answer == Integer.MAX_VALUE)
				System.out.println(-1);
			else
			{
				System.out.println(answer);
				if(answer == 13 && ++nTrece == 3)
					System.out.println(System.currentTimeMillis() - time);
			}
		}
	}
}