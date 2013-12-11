import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeSegment 
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
				res[i] = nextLong();
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
	}
	
	static class Nodo
	{
		int a;
		int b;
		int valorActual;
		int valorAcumulado;
		Nodo izquierdo;
		Nodo derecho;
		
		void actualizar()
		{
			if(a == b)
			{
				if(valorAcumulado != -1)
				{
					valorActual = valorAcumulado;
					valorAcumulado = -1;
				}
			}
			else
			{
				if(valorAcumulado != -1)
				{
					valorActual = valorAcumulado;
					izquierdo.update(izquierdo.a, valorActual, izquierdo.b - izquierdo.a + 1);
					derecho.update(derecho.a, valorActual + (izquierdo.b - izquierdo.a + 1), derecho.b - derecho.a + 1);
					valorAcumulado = -1;
				}
			}
		}
		
		Nodo(int i, int d)
		{
			a = i;
			b = d;
			valorAcumulado = -1;
			if(a == b)
				valorActual = -1;
			else
			{
				izquierdo = new Nodo(a, (a + b) / 2);
				derecho = new Nodo((a + b) / 2 + 1, b);
			}
		}
		
		void update(int from, int start, int length)
		{
			if(from > b || from + length - 1 < a)
				return;
			if(from <= a && from + length - 1 >= b)
			{
				int posReal = (a - from) + start;
				valorAcumulado = posReal;
			}
			else
			{
				actualizar();
				izquierdo.update(from, start, length);
				derecho.update(from, start, length);
			}
		}
		
		int query(int i)
		{
			if(i > b || i < a)
				return Integer.MIN_VALUE;
			actualizar();
			if(a == i && b == i)
				return valorActual == -1 ? bI[a] : aI[valorActual];
			else
				return Math.max(izquierdo.query(i), derecho.query(i));
		}
	}
	
	static int[] aI;
	static int[] bI;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		aI = sc.nextIntArray(n);
		bI = sc.nextIntArray(n);
		Nodo raiz = new Nodo(0, n - 1);
		for(int i = 0; i < m; i++)
		{
			int tipo = sc.nextInt();
			if(tipo == 1)
			{
				int x = sc.nextInt() - 1;
				int y = sc.nextInt() - 1;
				int k = sc.nextInt();
				raiz.update(y, x, k);
			}
			else
			{
				int y = sc.nextInt() - 1;
				System.out.println(raiz.query(y));
			}
		}
	}
}