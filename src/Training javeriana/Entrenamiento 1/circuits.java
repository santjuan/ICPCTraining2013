import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class circuits 
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
	
	static int[] nResistencias;
	
	static class Resistencia implements Comparable <Resistencia>
	{
		char a;
		char b;
		double valor;
		
		Resistencia(char aa, char bb, double val)
		{
			if(bb < aa)
			{
				a = bb;
				b = aa;
			}
			else
			{
				a = aa;
				b = bb;
			}
			valor = val;
		}
		
		Resistencia unir(Resistencia x)
		{
			if(a == x.a)
			{
				if(b == x.b)
					return new Resistencia(a, b, 1 / ((1 / valor) + (1 / x.valor)));
				else
				{
					if(nResistencias[a - 'A'] == 2)
						return new Resistencia(b, x.b, valor + x.valor);
				}
			}
			else if(a == x.b)
			{
				if(b == x.a)
					return new Resistencia(a, b, 1 / ((1 / valor) + (1 / x.valor)));
				if(nResistencias[a - 'A'] == 2)
					return new Resistencia(b, x.a, valor + x.valor);
			}
			else if(b == x.a)
			{
				if(nResistencias[b - 'A'] == 2)
					return new Resistencia(a, x.b, valor + x.valor);
			}
			else if(b == x.b)
			{
				if(nResistencias[b - 'A'] == 2)
					return new Resistencia(a, x.a, valor + x.valor);
			}
			return null;
		}

		@Override
		public int compareTo(Resistencia o) 
		{
			if(a == o.a)
				return b - o.b;
			else
				return a - o.a;
		}
	}
	
	static ArrayList <Resistencia> todasResistencias;
	
	
	static double solve()
	{
		boolean cambio = true;
		while(cambio && todasResistencias.size() != 1)
		{
			cambio = false;
			outer:
			for(int i = 0; i < todasResistencias.size(); i++)
				for(int j = i + 1; j < todasResistencias.size(); j++)
				{
					Resistencia a = todasResistencias.get(i);
					Resistencia b = todasResistencias.get(j);
					Resistencia posible = a.unir(b);
					if(posible != null)
					{
						if(a.a == 'A' && b.a == 'A' && posible.a != 'A') continue;
						if(a.b == 'Z' && b.b == 'Z' && posible.b != 'Z') continue;
						cambio = true;
						eliminarResistencia(a);
						eliminarResistencia(b);
						registrarResistencia(posible);
						break outer;
					}
				}
			
		}
		if(todasResistencias.size() == 1 && todasResistencias.get(0).a == 'A' && todasResistencias.get(0).b == 'Z')
			return todasResistencias.get(0).valor;
		else
			return -1;
	}
	
	static void registrarResistencia(Resistencia x)
	{
		nResistencias[x.a - 'A']++;
		nResistencias[x.b - 'A']++;
		todasResistencias.add(x);
		Collections.sort(todasResistencias);
	}
	
	static void eliminarResistencia(Resistencia x)
	{
		nResistencias[x.a - 'A']--;
		nResistencias[x.b - 'A']--;
		todasResistencias.remove(x);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) return;
			todasResistencias = new ArrayList <Resistencia> ();
			nResistencias = new int[26];
			for(int i = 0; i < n; i++)
				registrarResistencia(new Resistencia(sc.next().charAt(0), sc.next().charAt(0), sc.nextInt()));
			sc.printLine(3, solve());
		}
	}
}