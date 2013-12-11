import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class CodeI 
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
	}
	
	static int count(ArrayList <Integer> arreglo, int v)
	{
		int c = 0;
		for(int i : arreglo) if(i == v) c++;
		return c;
	}
	
	static class Punto implements Comparable <Punto>
	{
		int x;
		int y;
		
		public Punto(Scanner sc)
		{
			x = sc.nextInt();
			y = sc.nextInt();
		}

		@Override
		public int compareTo(Punto o) 
		{
			if(x == o.x) return y - o.y;
			return x - o.x;
		}
	}

	static boolean checkExist(Punto[][] lineas, int xI, int yI, int xF, int yF)
	{
		for(Punto[] l : lineas)
		{
			if(l[0].x == xI && l[0].y == yI && l[1].x == xF && l[1].y == yF) return true;
			if(l[1].x == xI && l[1].y == yI && l[0].x == xF && l[0].y == yF) return true;
		}
		return false;
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		TreeMap <Punto, Integer> puntos = new TreeMap <Punto, Integer> ();
		Punto[][] lineas = new Punto[4][2];
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 2; j++)
			{
				lineas[i][j] = new Punto(sc);
				if(!puntos.containsKey(lineas[i][j]))
					puntos.put(lineas[i][j], 0);
				puntos.put(lineas[i][j], puntos.get(lineas[i][j]) + 1);
			}
		if(puntos.size() != 4)
		{
			System.out.println("NO");
			return;
		}
		for(int val : puntos.values())
			if(val != 2)
			{
				System.out.println("NO");
				return;
			}
		ArrayList <Integer> filas = new ArrayList <Integer> ();
		ArrayList <Integer> columnas = new ArrayList <Integer> ();
		for(Punto p : puntos.keySet())
		{
			filas.add(p.x);
			columnas.add(p.y);
		}
		int filaR = 0;
		int columaR = 0;
		int minFila = Integer.MAX_VALUE;
		int maxFila = Integer.MIN_VALUE;
		int minColumna = Integer.MAX_VALUE;
		int maxColumna = Integer.MIN_VALUE;
		for(int i : filas)
		{
			minFila = Math.min(minFila, i);
			maxFila = Math.max(maxFila, i);
			if(count(filas, i) != 2)
			{
				System.out.println("NO");
				return;
			}
		}
		for(int i : columnas)
		{
			minColumna = Math.min(minColumna, i);
			maxColumna = Math.max(maxColumna, i);
			if(count(columnas, i) != 2)
			{
				System.out.println("NO");
				return;
			}
		}
		if(minFila == maxFila)
		{
			System.out.println("NO");
			return;
		}
		if(minColumna == maxColumna)
		{
			System.out.println("NO");
			return;
		}
		boolean ok = checkExist(lineas, minFila, minColumna, minFila, maxColumna);
		ok = ok && checkExist(lineas, maxFila, minColumna, maxFila, maxColumna);
		ok = ok && checkExist(lineas, minFila, minColumna, maxFila, minColumna);
		ok = ok && checkExist(lineas, minFila, maxColumna, maxFila, maxColumna);
		System.out.println(ok ? "YES" : "NO");
	}
}