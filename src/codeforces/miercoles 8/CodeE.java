import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeE 
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
	
	static int cuentaG = 1;
	static class Nodo
	{
		Nodo siguiente;
		Nodo anterior;
		boolean gared;
		int numero;
		
		public Nodo(boolean b)
		{
			gared = b;
			numero = cuentaG++;
		}
	}
	
	static boolean solve(int n, int m, int k)
	{
		Nodo cabeza = new Nodo(n != 0);
		Nodo cola = cabeza;
		cabeza.anterior = cabeza;
		cabeza.siguiente = cabeza;
		for(int i = 0; i < n - 1; i++)
		{
			Nodo siguiente = new Nodo(true);
			cola.siguiente = siguiente;
			cabeza.anterior = siguiente;
			siguiente.anterior = cola;
			siguiente.siguiente = cabeza;
			cola = siguiente;
		}
		for(int i = 0; i < (n == 0 ? m - 1 : m); i++)
		{
			Nodo siguiente = new Nodo(false);
			cola.siguiente = siguiente;
			cabeza.anterior = siguiente;
			siguiente.anterior = cola;
			siguiente.siguiente = cabeza;
			cola = siguiente;
		}
		Nodo actual = cabeza;
		while(actual.siguiente != actual)
		{
		//	imprimir(actual);
			for(int i = 1; i < k; i++)
				actual = actual.siguiente;
			eliminar(actual);
			boolean tipoUno = actual.gared;
			actual = actual.siguiente;
			for(int i = 1; i < k; i++)
				actual = actual.siguiente;
			eliminar(actual);
			boolean tipoDos = actual.gared;
			if(actual.siguiente == actual)
				return tipoUno == tipoDos;
			actual = actual.anterior;
			agregar(actual, new Nodo(tipoUno == tipoDos));
			actual = actual.siguiente;
			actual = actual.siguiente;
		}
		return actual.gared;
	}
	
	static void imprimir(Nodo n)
	{
		Nodo inicio = n;
		System.out.print((inicio.gared ? "G" : "K") + inicio.numero + " ");
		do
		{
			n = n.siguiente;
			if(n != inicio)  System.out.print((n.gared ? "G" : "K") + n.numero + " ");
		}
		while(n != inicio);
		System.out.println();
	}
	
	private static void agregar(Nodo actual, Nodo nuevo)
	{
		nuevo.anterior = actual;
		nuevo.siguiente = actual.siguiente;
		actual.siguiente.anterior = nuevo;
		actual.siguiente = nuevo;
	}
	
	private static void eliminar(Nodo actual)
	{
		actual.anterior.siguiente = actual.siguiente;
		actual.siguiente.anterior = actual.anterior;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			if(n == 0 && m == 0 && k == 0)
				return;
			if(n + m == 0)
				System.out.println("Keka");
			else if(n == 0)
			{
				if((m & 1) == 0)
					System.out.println("Gared");
				else
					System.out.println("Keka");
			}
			else
			{
				if((m & 1) == 0)
					System.out.println("Gared");
				else
					System.out.println("Keka");
			}
		}
	}

}
