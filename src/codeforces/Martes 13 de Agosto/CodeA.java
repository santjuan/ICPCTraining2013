import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class CodeA 
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
		
		Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
		
		static <T> T fill(T arreglo, int val)
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
			T[] result = (T[]) new Object[size];
			try 
			{
				Constructor <T> constructor = clazz.getConstructor(Scanner.class, Integer.TYPE);
				for(int i = 0; i < result.length; i++)
					result[i] = constructor.newInstance(this, i);
				return result;
			} 
			catch(Exception e)
			{
				throw new RuntimeException(e);
			} 
		}
	}

	static class Posicion
	{
		int x;
		int y;
		
		Posicion(int xx, int yy)
		{
			x = xx;
			y = yy;
		}
	}
	
	static String[] moves = {"L", "R", "U", "D", "LU", "LD", "RU", "RD"};
	static int[][] diff = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, -1}, {-1, -1}, {1, 1}, {-1, 1}};
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		String a = sc.next();
		String b = sc.next();
		String[][] path = new String[8][8];
		int[][] cuantas = Scanner.fill(new int[8][8], Integer.MAX_VALUE);
		int yI = a.charAt(0) - 'a';
		int xI = a.charAt(1) - '1';
		int yJ = b.charAt(0) - 'a';
		int xJ = b.charAt(1) - '1';
		LinkedList <Posicion> cola = new LinkedList <Posicion> ();
		path[xI][yI] = "";
		cuantas[xI][yI] = 0;
		cola.add(new Posicion(xI, yI));
		String ansR = null;
		int cuentaAns = 0;
		while(!cola.isEmpty())
		{
			Posicion p = cola.poll();
			if(p.x == xJ && p.y == yJ)
				break;
			for(int i = 0; i < diff.length; i++)
			{
				int x = p.x + diff[i][0];
				int y = p.y + diff[i][1];
				if(x >= 0 && x < 8 && y >= 0 && y < 8 && cuantas[x][y] == Integer.MAX_VALUE)
				{
					cuantas[x][y] = cuantas[p.x][p.y] + 1;
					path[x][y] = path[p.x][p.y] + (path[p.x][p.y].length() == 0 ? "" : "\n") + moves[i];
					cola.add(new Posicion(x, y));
				}
			}
		}
		System.out.println(cuantas[xJ][yJ]);
		System.out.print(path[xJ][yJ]);
		System.out.flush();
	}
}