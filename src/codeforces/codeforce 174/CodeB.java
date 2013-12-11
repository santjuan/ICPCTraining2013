import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
	}
	
	static class Arista
	{
		Nodo otro;
		long peso;
		
		public Arista(Nodo otro, long peso) 
		{
			this.otro = otro;
			this.peso = peso;
		}
	}
	
	static class Nodo
	{
		int numero;
		int paso;
		long visita = -1;
		boolean visitado;
		ArrayList <Arista> aristas = new ArrayList <Arista> ();
		
		public Nodo(int n, int p)
		{
			numero = n;
			paso = p;
		}
	}
	
	static int[] numeros;
	static Nodo[][] nodos;
	static int n;
	
	static void bfs()
	{
		Nodo inicial = nodos[0][0];
		inicial.visitado = true;
		LinkedList <Arista> cola = new LinkedList <Arista> ();
		cola.add(new Arista(inicial, 0));
		while(!cola.isEmpty())
		{
			Arista actual = cola.poll();
			actual.otro.visita = actual.peso;
			for(Arista a : actual.otro.aristas)
			{
				if(a.otro.visitado)
					continue;
				a.otro.visitado = true;
				cola.add(new Arista(a.otro, actual.peso + a.peso));
			}
		}
	}

	private static void generarAristas(int nodo, int estado) 
	{
		if(estado == 0)
		{
			int siguiente = nodo + numeros[nodo];
			if(siguiente <= 0 || siguiente > n)
				nodos[0][0].aristas.add(new Arista(nodos[nodo][estado], numeros[nodo]));
			else
				nodos[siguiente][1].aristas.add(new Arista(nodos[nodo][estado], numeros[nodo]));
		}
		else
		{
			int siguiente = nodo - numeros[nodo];
			if(siguiente <= 0 || siguiente > n)
				nodos[0][0].aristas.add(new Arista(nodos[nodo][estado], numeros[nodo]));
			else
				nodos[siguiente][0].aristas.add(new Arista(nodos[nodo][estado], numeros[nodo]));
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		Nodo finalizacion = new Nodo(0, 0);
		Nodo inicial = new Nodo(1, 0);
		numeros = new int[n + 2];
		nodos = new Nodo[n + 2][2];
		nodos[0][0] = nodos[0][1] = finalizacion;
		nodos[1][0] = nodos[1][1] = inicial;
		for(int i = 2; i <= n; i++)
		{
			numeros[i] = sc.nextInt();
			nodos[i][0] = new Nodo(i, 0);
			nodos[i][1] = new Nodo(i, 1);
		}
		for(int i = 2; i <= n; i++)
		{
			generarAristas(i, 0);
			generarAristas(i, 1);	
		}
		bfs();
		for(int i = 1; i <= n - 1; i++)
		{
			long valor = nodos[1 + i][1].visita;
			if(valor != -1)
				valor += i;
			System.out.println(valor);
		}
	}
}