import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class JamA 
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
	
	static class Evento implements Comparable <Evento>
	{
		long tiempo;
		long pasajeros;
		boolean entrada;
		
		
		public Evento(long t, long p, boolean e)
		{
			tiempo = t;
			pasajeros = p;
			entrada = e;
		}
		
		@Override
		public int compareTo(Evento o) 
		{
			if(tiempo == o.tiempo)
				return Boolean.valueOf(o.entrada).compareTo(entrada);
			return Long.valueOf(tiempo).compareTo(o.tiempo);
		}
	}
	
	static class Tiquete implements Comparable <Tiquete>
	{
		long nActual;
		long nTiquetes;
		
		public Tiquete(long nA, long nT) 
		{
			nActual = nA;
			nTiquetes = nT;
		}

		@Override
		public int compareTo(Tiquete o)
		{
			return Long.valueOf(o.nActual).compareTo(nActual);
		}
	}
	
	static long sumaHasta(long n) 
	{
		return (n * (n + 1)) / 2;
	}
	
	static long sumar(long dist, long nInicial)
	{
		return (sumaHasta(nInicial) - sumaHasta(nInicial - dist)) % 1000002013;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.setIn(new FileInputStream("Al.in"));
		System.setOut(new PrintStream("Al.out"));
		Scanner sc = new Scanner();
		int tc = sc.nextInt();
		for(int caso = 1; caso <= tc; caso++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			int[][] entradas = sc.nextIntMatrix(m, 3);
			LinkedList <Evento> eventos = new LinkedList <Evento> ();
			LinkedList <Tiquete> tiquetes = new LinkedList <Tiquete> ();
			for(int i = 0; i < m; i++)
			{
				eventos.add(new Evento(entradas[i][0], entradas[i][2], true));
				eventos.add(new Evento(entradas[i][1], entradas[i][2], false));
			}
			long anteriorCobro = 0;
			Collections.sort(eventos);
			long total = 0;
			while(!eventos.isEmpty())
			{
				Evento e = eventos.poll();
				total += cobrarTiquetes(anteriorCobro, e.tiempo, tiquetes);
				total %= 1000002013;
				anteriorCobro = e.tiempo;
				if(e.entrada)
					tiquetes.add(new Tiquete(n + 1, e.pasajeros));
				else
					quitarTiquetes(tiquetes, e.pasajeros);
			}
			long totalNormal = 0;
			for(int i = 0; i < m; i++)
			{
				long dist = entradas[i][1] - entradas[i][0];
				totalNormal += (sumar(dist, n) * entradas[i][2]) % 1000002013;
				totalNormal %= 1000002013;
			}
			totalNormal -= total;
			totalNormal += 1000002013;
			totalNormal %= 1000002013;
			System.out.println("Case #" + caso + ": " + totalNormal); 
		}
	}

	static void quitarTiquetes(LinkedList <Tiquete> tiquetes, long pasajeros)
	{
		Collections.sort(tiquetes);
		while(pasajeros > 0)
		{
			Tiquete t = tiquetes.peekFirst();
			if(t.nTiquetes < pasajeros)
			{
				tiquetes.pollFirst();
				pasajeros -= t.nTiquetes;
			}
			else
			{
				t.nTiquetes -= pasajeros;
				if(t.nTiquetes == 0)
					tiquetes.poll();
				return;
			}
		}
	}

	static long cobrarTiquetes(long anteriorC, long actual, LinkedList <Tiquete> tiquetes)
	{
		if(anteriorC == actual)
			return 0;
		for(Tiquete t : tiquetes)
			t.nActual--;
		long resultado = 0;
		for(Tiquete t : tiquetes)
		{
			long distancia = actual - anteriorC;
			resultado += (sumar(distancia, t.nActual) * t.nTiquetes) % 1000002013;
			resultado %= 1000002013;
			t.nActual -= distancia;
			t.nActual++;
		}
		return resultado;
	}
}