import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeC 
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
	}

	static class Punto
	{
		int x;
		int y;
		int id;
		
		public Punto(Scanner sc, int i)
		{
			x = sc.nextInt();
			y = sc.nextInt();
			id = i + 1;
		}
		
		public Punto(int xx, int yy)
		{
			x = xx;
			y = yy;
		}
		
		int dotProduct(Punto otro)
		{
			return x * otro.x + y * otro.y;
		}
		
		int distancia(Punto otro)
		{
			return (x - otro.x) * (x - otro.x) + (y - otro.y) * (y - otro.y);
		}
		
		Punto resta(Punto a)
		{
			return new Punto(x - a.x, y - a.y);
		}
	}
	
	static boolean esRectangulo(Punto[] puntos)
	{
		for(int i = 0; i < 4; i++)
		{
			Punto lineaA = puntos[(i + 1) % 4].resta(puntos[i]);
			Punto lineaB = puntos[(i + 2) % 4].resta(puntos[(i + 1) % 4]);
			if(lineaA.dotProduct(lineaB) != 0) return false;
		}
		return true;
	}
	
	static boolean esCuadrado(Punto[] puntos)
	{
		if(!esRectangulo(puntos)) return false;
		for(int i = 0; i < 4; i++)
		{
			Punto lineaA = puntos[(i + 1) % 4].resta(puntos[i]);
			Punto lineaB = puntos[(i + 2) % 4].resta(puntos[(i + 1) % 4]);
			if(lineaA.distancia(new Punto(0, 0)) != lineaB.distancia(new Punto(0, 0))) return false;
		}
		return true;
	}
	
	static boolean permutar(Punto[] original, Punto[] permutacion, int n, boolean[] usados, boolean cuadrado)
	{
		if(n == original.length)
		{
			if(cuadrado) return esCuadrado(permutacion);
			else return esRectangulo(permutacion);
		}
		else
		{
			for(int i = 0; i < original.length; i++)
			{
				if(!usados[i])
				{
					permutacion[n] = original[i];
					usados[i] = true;
					if(permutar(original, permutacion, n + 1, usados, cuadrado)) return true;
					usados[i] = false;
				}
			}
			return false;
		}
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		Punto[] puntos = sc.nextObjectArray(Punto.class, 8);
		int limite = 1 << 8;
		for(int i = 0; i < limite; i++)
		{
			if(Integer.bitCount(i) != 4) continue;
			Punto[] a = new Punto[4];
			Punto[] b = new Punto[4];
			int sizeA = 0;
			int sizeB = 0;
			for(int j = 0; j < 8; j++)
			{
				if(((i >> j) & 1) == 1)
					a[sizeA++] = puntos[j];
				else
					b[sizeB++] = puntos[j];
			}
			if(permutar(a, new Punto[4], 0, new boolean[4], true) && permutar(b, new Punto[4], 0, new boolean[4], false))
			{
				System.out.println("YES");
				for(int j = 0; j < 4; j++)
				{
					if(j != 0) System.out.print(" ");
					System.out.print(a[j].id);
				}
				System.out.println();
				for(int j = 0; j < 4; j++)
				{
					if(j != 0) System.out.print(" ");
					System.out.print(b[j].id);
				}
				System.out.println();
				return;
			}
		}
		System.out.println("NO");
	}
}