import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CodeF 
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
		
		public char[][] nextGrid(int r, int c) 
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
	
	static char[] a;
	static char[] b;
	
	static int[][] next;
	
	
	static int solve(int lengthA, int startA, boolean stop)
	{
		int currentA = startA;
		int currentB = 0;
		int count = 0;
		int countB = 0;
		boolean[] mods = new boolean[a.length];
		int[][] modData = new int[a.length][3];
		int posMod = -1;
		int vecesMod = -1;
		int vecesModA = -1;
		int vecesAnt = -1;
		int vecesAntA = -1;
		int inicioA = -1;
		int total = 0;
		while(count < lengthA)
		{
			if(currentA == a.length)
			{
				currentA = 0;
				count++;
			}
			int sig = next[currentA][b[currentB] - 'a'];
			if(sig == -1)
			{
				count++;
				if(count >= lengthA)
					break;
				sig = next[0][b[currentB] - 'a'];
			}
			currentA = sig;
			currentB++;
			currentA++;
			if(currentB == b.length)
			{
				countB++;
				currentB = 0;
				if(mods[currentA % a.length] && stop)
				{ 
					vecesMod = countB - modData[currentA % a.length][0];
					posMod = currentA == 1 ? (currentA % a.length) : ((currentA - 1 + a.length) % a.length);
					vecesModA = count - modData[currentA % a.length][1];
					vecesAntA = modData[currentA % a.length][1];
					vecesAnt = modData[currentA % a.length][0];
					inicioA = currentA == a.length - 1 ? modData[currentA % a.length][2] + 1 : modData[currentA % a.length][2];
					break;
				}
				else
				{
					mods[currentA % a.length] = true;
					modData[currentA % a.length][0] = countB;
					modData[currentA % a.length][1] = count;
					modData[currentA % a.length][2] = count * a.length + currentA;
				}
			}
			total++;
		}
		if(posMod != -1)
		{
			int veces = (lengthA * a.length - inicioA) / (vecesModA * a.length);
			int r1 = (lengthA * a.length - inicioA) % (vecesModA * a.length);
			int restantes = (((lengthA * a.length - inicioA) - (vecesModA * a.length * veces)) / a.length) + 1;
			if(r1 == 0) restantes--; 
			int restanteV = ((lengthA * a.length - inicioA) - (vecesModA * a.length * veces));
			return ((inicioA  == 0) ? 0 : vecesAnt) + vecesMod * veces + (restantes == 0 ? 0 : solve(restantes, (posMod + 1) % a.length, false));
		}
		return countB;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int bX = sc.nextInt();
		int dX = sc.nextInt();
		a = sc.next().toCharArray();
		b = sc.next().toCharArray();
		next = new int[a.length][26];
		@SuppressWarnings("unchecked")
		TreeSet <Integer> [] siguientes = new TreeSet[26];
		for(int i = 0; i < 26; i++) siguientes[i] = new TreeSet <Integer> ();
		for(int i = 0; i < a.length; i++)
			siguientes[a[i] - 'a'].add(i);
		TreeSet <Integer> enA = new TreeSet <Integer> ();
		for(char c : b)
			enA.add(c - 'a');
		for(int i = 0; i < a.length; i++)
			for(int j = 0; j < 26; j++)
			{
				Integer val = siguientes[j].ceiling(i);
				if(val == null)
					next[i][j] = -1;
				else
					next[i][j] = val;
			}
		boolean paila = false;
		for(int i : enA)
			if(next[0][i] == -1)
				paila = true;
		if(paila)
			System.out.println("0");
		else
		{
			int cuenta = solve(bX, 0, true);
			cuenta /= dX;
			System.out.println(cuenta);
		}
	}
}