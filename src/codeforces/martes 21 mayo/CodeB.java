import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class CodeB 
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

		public String[] nextStringArray(int n) 
		{	
			String[] vals = new String[n];
			for(int i = 0; i < n; i++)
				vals[i] = next();
			return vals;
		}
	}
	
	static long tiempoActual = 0;
	
	static class Nodo
	{
		long n;
		long o;
		boolean visitado;
		ArrayList <Arista> aristas = new ArrayList <Arista> ();
		
		void calcular(Nodo padre)
		{
			n = 1;
			o = 0;
			for(Arista a : aristas)
			{
				if(a.otro == padre)
					continue;
				a.otro.calcular(this);
				long nEsta = a.otro.n;
				long oEsta = a.otro.o;
				oEsta += (a.peso << 1);
				n += nEsta;
				o += oEsta;
				a.n = nEsta;
				a.o = oEsta;
			}
		}
		
		long simular(Nodo padre)
		{
			long resultado = 0;
			if(!visitado)
			{
				visitado = true;
				resultado += tiempoActual;
			}
			Arista alPadre = null;
			for(Arista a : aristas)
			{
				if(a.otro == padre)
				{
					alPadre = a;
					aristas.remove(a);
					break;
				}
			}
			Collections.sort(aristas);
			for(Arista a : aristas)
			{
				if(a.otro == padre)
					continue;
				tiempoActual += a.peso;
				resultado += a.otro.simular(this);
			}
			if(alPadre != null)
				tiempoActual += alPadre.peso;
			return resultado;
		}
	}
	
	static class Arista implements Comparable <Arista>
	{
		long n;
		long o;
		long peso;
		Nodo otro;
		
		Arista(Nodo o, long p)
		{
			otro = o;
			peso = p;
		}

		@Override
		public int compareTo(Arista otro) 
		{
			long a = n * otro.o;
			long b = otro.n * o;
			return Long.valueOf(b).compareTo(a);
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		Nodo[] nodos = new Nodo[n];
		for(int i = 0; i < n; i++)
			nodos[i] = new Nodo();
		for(int i = 0; i < (n - 1); i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int c = sc.nextInt();
			nodos[a].aristas.add(new Arista(nodos[b], c));
			nodos[b].aristas.add(new Arista(nodos[a], c));
		}
		nodos[0].calcular(null);
		System.out.println(nodos[0].simular(null) / ((double) (n - 1)));
	}
}