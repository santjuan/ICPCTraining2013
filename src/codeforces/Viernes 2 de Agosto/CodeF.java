import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;


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
	}
	
	static final int[] puntuacion = new int[] {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
	static boolean normal;
	
	static class Driver implements Comparable <Driver>
	{
		String nombre;
		int[] puestos = new int[50];
		int puntos;
		
		Driver(String n)
		{
			nombre = n;
		}
		
		void agregar(int puesto)
		{
			if(puesto < puntuacion.length)
				puntos += puntuacion[puesto];
			puestos[puesto]++;
		}

		static int compararArreglos(int[] a, int[] b)
		{
			for(int i = 0; i < a.length; i++)
				if(a[i] != b[i])
					return b[i] - a[i];
			return 0;
		}
		
		@Override
		public int compareTo(Driver otro) 
		{
			if(normal || otro.puestos[0] == puestos[0])
			{
				if(puntos == otro.puntos)
					return compararArreglos(puestos, otro.puestos);
				return otro.puntos - puntos;
			}
			else
				return otro.puestos[0] - puestos[0];
		}
	}

	static ArrayList <Driver> allDrivers = new ArrayList <Driver> ();
	static TreeMap <String, Driver> drivers = new TreeMap <String, Driver> ();
	
	static Driver darDriver(String s)
	{
		if(!drivers.containsKey(s))
		{
			Driver n = new Driver(s);
			allDrivers.add(n);
			drivers.put(s, n);
		}
		return drivers.get(s);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int i = 0; i < t; i++)
		{
			int n = sc.nextInt();
			for(int j = 0; j < n; j++)
				darDriver(sc.nextLine()).agregar(j);
		}
		normal = true;
		Collections.sort(allDrivers);
		System.out.println(allDrivers.get(0).nombre);
		normal = false;
		Collections.sort(allDrivers);
		System.out.println(allDrivers.get(0).nombre);
	}
}