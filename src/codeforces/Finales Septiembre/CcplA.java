import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CcplA 
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
	
	private static void generarDivisores(ArrayList<Integer> factores, ArrayList<Integer> numeroVeces, int i, ArrayList<Integer> respuesta, int acum)
	{
		if(i == factores.size())
		{
			respuesta.add(acum);
			return;
		}
		for(int j = 0; j <= numeroVeces.get(i); j++)
		{
			generarDivisores(factores, numeroVeces, i + 1, respuesta, acum);
			acum *= factores.get(i);
		}
	}
	
	public static ArrayList <Integer> generarDivisores(int n)
	{
		ArrayList <Integer> factores = new ArrayList <Integer> ();
		ArrayList <Integer> numeroVeces = new ArrayList <Integer> ();
		for(int i = 2; i <= n; i++)
		{
			if(n % i == 0)
			{
				int cuenta = 0;
				while(n % i == 0)
				{
					n /= i;
					cuenta++;
				}
				factores.add(i);
				numeroVeces.add(cuenta);
			}
		}
		ArrayList <Integer> respuesta = new ArrayList <Integer> ();
		generarDivisores(factores, numeroVeces, 0, respuesta, 1);
		return respuesta;
	}
	
	static class Par
	{
		long b;
		long c;
	
		public Par(long b, long c) 
		{
			this.b = b;
			this.c = c;
		}
		
		@Override
		public int hashCode() 
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (b ^ (b >>> 32));
			result = prime * result + (int) (c ^ (c >>> 32));
			return result;
		}
		@Override
		public boolean equals(Object obj) 
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Par other = (Par) obj;
			if (b != other.b)
				return false;
			if (c != other.c)
				return false;
			return true;
		}
	}
	
	
	static int gcd(int a, int b)
	{
		if(b == 0) return a;
		return gcd(b, a % b);
	}

	static ArrayList <Par> generarBsA(int restante, int[] factores)
	{
		restante /= 2;
		ArrayList <Par> generados = new ArrayList <Par> ();
		for(int f : factores)
		{
			if((restante % f) != 0) continue;
			int x1 = f;
			int x2 = restante / f;
			int m = Math.max(x1, x2);
			int n = Math.min(x1, x2);
			if(m <= n) continue;
			if(((m - n) & 1) == 0) continue;
			if(m == 0 || n == 0) continue;
			if(gcd(m, n) != 1) continue;
			if(m * n != restante) continue;
			generados.add(new Par(m, n));
		}
		return generados;
	}
	
	static ArrayList <Par> generarBsB(int restante, int[] factores)
	{
		ArrayList <Par> generados = new ArrayList <Par> ();
		for(int f : factores)
		{
			if((restante % f) != 0) continue;
			int x1 = f;
			int x2 = restante / f;
			int mMasN = Math.max(x1, x2);
			int mMenosN = Math.min(x1, x2);
			int dosB = mMasN - mMenosN;
			if(dosB == 0 || ((dosB & 1) != 0)) continue;
			int b = dosB / 2;
			int a = mMasN - b;
			if(a <= 0) continue;
			int m = Math.max(a, b);
			int n = Math.min(a, b);
			if(m <= n) continue;
			if(((m - n) & 1) == 0) continue;
			if(m == 0 || n == 0) continue;
			if(gcd(m, n) != 1) continue;
			if(m * m - n * n != restante) continue;
			generados.add(new Par(m, n));
		}
		return generados;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int x = sc.nextInt();
			if(x == 0) return;
			int[] divisores = sc.unwrap(generarDivisores(x));
			HashSet <Par> pares = new HashSet <Par> ();
			for(int k : divisores)
			{
				int restante = x / k;
				if((restante & 1) == 0)
				{
					for(Par p : generarBsA(restante, divisores))
					{
						long m = p.b;
						long n = p.c;
						long a = 2 * k;
						a *= m * n;
						if(a != x) continue;
						long b = m * m - n * n;
						b *= k;
						long c = m * m + n * n;
						c *= k;
						if((a * a + b * b == c * c) && b > a)
							pares.add(new Par(b, c));
					}
				}
				for(Par p : generarBsB(restante, divisores))
				{
					long m = p.b;
					long n = p.c;
					long b = 2 * k;
					b *= m * n;
					long a = m * m - n * n;
					a *= k;
					if(a != x) continue;
					long c = m * m + n * n;
					c *= k;
					if((a * a + b * b == c * c) && b > a)
						pares.add(new Par(b, c));
				}
			}
			System.out.println(pares.size());
		}
	}
}