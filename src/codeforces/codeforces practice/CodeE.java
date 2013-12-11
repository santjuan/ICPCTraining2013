import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
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
	
	static class Nodo
	{
		int id;
		ArrayList <Nodo> aristas = new ArrayList <Nodo> ();
		boolean voluntario = false;
		int mejor;
		int voluntarioCercano;
		
		Nodo(int i)
		{
			id = i;
		}
	}
	
	
	static class Entrada implements Comparable <Entrada>
	{
		Nodo n;
		int costo;
		
		public Entrada(Nodo n2, int c) 
		{
			n = n2;
			costo = c;
		}

		@Override
		public int compareTo(Entrada o) 
		{
			return costo - o.costo;
		}
	}
	
	
	static Nodo[] intersecciones;
	
	static void encontrarCercanos()
	{
		PriorityQueue <Entrada> pq = new PriorityQueue <Entrada> ();
		for(Nodo n : intersecciones)
		{
			if(n.voluntario)
			{
				n.voluntarioCercano = 0;
				pq.add(new Entrada(n, 0));
			}
			else
				n.voluntarioCercano = 10000000;
		}
		while(!pq.isEmpty())
		{
			Entrada actual = pq.poll();
			if(actual.costo != actual.n.voluntarioCercano)
				continue;
			for(Nodo n : actual.n.aristas)
			{
				int costoSiguiente = actual.n.voluntarioCercano + 1;
				if(costoSiguiente < n.voluntarioCercano)
				{
					n.voluntarioCercano = costoSiguiente;
					pq.add(new Entrada(n, n.voluntarioCercano));
				}
			}
		}
	}
	
	static int s, t;
	
	static boolean esPosible(int q)
	{
		for(Nodo n : intersecciones)
			n.mejor = Integer.MAX_VALUE;
		intersecciones[s].mejor = 0;
		PriorityQueue <Entrada> pq = new PriorityQueue <Entrada> ();
		pq.add(new Entrada(intersecciones[s], 0));
		while(!pq.isEmpty())
		{
			Entrada e = pq.poll();
			if(e.n.mejor != e.costo)
				continue;
			if(e.n.id == t && e.costo <= q)
				return true;
			for(Nodo n : e.n.aristas)
			{
				int costoSiguiente = e.costo + 1;
				if(costoSiguiente + n.voluntarioCercano <= q)
					costoSiguiente = n.voluntarioCercano;
				if(costoSiguiente > q)
					continue;
				if(costoSiguiente < n.mejor)
				{
					n.mejor = costoSiguiente;
					pq.add(new Entrada(n, n.mejor));
				}
			}
		}
		return false;
	}
	
	static int busquedaBinaria(int a, int b)
	{
		if(a == b)
			return esPosible(a) ? a : -1;
		if(a + 1 == b)
			return esPosible(a) ? a : esPosible(b) ? b : -1;
		int mid = (a + b) >>> 1;
		if(esPosible(mid))
			return busquedaBinaria(a, mid);
		else
			return busquedaBinaria(mid + 1, b);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		intersecciones = new Nodo[n];
		for(int i = 0; i < n; i++)
			intersecciones[i] = new Nodo(i);
		for(int i = 0; i < k; i++)
			intersecciones[sc.nextInt() - 1].voluntario = true;
		for(int i = 0; i < m; i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			intersecciones[a].aristas.add(intersecciones[b]);
			intersecciones[b].aristas.add(intersecciones[a]);
		}
		s = sc.nextInt() - 1;
		t = sc.nextInt() - 1;
		encontrarCercanos();
		System.out.println(busquedaBinaria(0, n));
	}
}