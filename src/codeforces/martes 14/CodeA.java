import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CodeA 
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
	
	static int idActual = 0;
	
	static class Entero implements Comparable <Entero>
	{
		int valor;
		int id = idActual++;
		
		Entero(int v)
		{
			valor = v;
		}
		
		@Override
		public int compareTo(Entero o) 
		{
			if(valor == o.valor)
				return id - o.id;
			return valor - o.valor;
		}
	}
	
	static class Piloto implements Comparable <Piloto>
	{
		String nombre;
		int puntos;
		boolean invertido;
		
		
		Piloto(String n, int p)
		{
			nombre = n;
			puntos = p;
		}

		@Override
		public int compareTo(Piloto o)
		{
			if(puntos == o.puntos)
				return invertido ? nombre.compareTo(o.nombre) : o.nombre.compareTo(nombre);
			return puntos - o.puntos;
		}
	}
	
	static int posicion(ArrayList <Piloto> pilotos, ArrayList <Entero> posiciones, Piloto yo)
	{
		TreeSet <Piloto> pilotosT = new TreeSet <Piloto> ();
		TreeSet <Entero> posicionesT = new TreeSet <Entero> ();
		for(Piloto p : pilotos)
			if(p != yo)
				pilotosT.add(p);
		for(Entero t : posiciones)
			posicionesT.add(t);
		Entero miPosicion = posicionesT.pollLast();
		int puesto = 0;
		while(!pilotosT.isEmpty() && pilotosT.first().compareTo(yo) < 0)
		{
			pilotosT.pollFirst();
			posicionesT.pollLast();
		}
		int misPuntos = miPosicion.valor + yo.puntos;
		while(!pilotosT.isEmpty())
		{
			Piloto actual = pilotosT.pollFirst();
			int buscado = misPuntos - actual.puntos;
			if(yo.invertido ? yo.nombre.compareTo(actual.nombre) < 0 : actual.nombre.compareTo(yo.nombre) < 0)
				buscado--;
			Entero mejor = posicionesT.floor(new Entero(buscado));
			if(mejor == null)
			{
				posicionesT.pollLast();
				puesto++;
			}
			else
				posicionesT.remove(mejor);
		}
		return puesto;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		ArrayList <Piloto> pilotos = new ArrayList <Piloto> ();
		for(int i = 0; i < n; i++)
			pilotos.add(new Piloto(sc.next(), sc.nextInt()));
		int m = sc.nextInt();
		ArrayList <Entero> posiciones = new ArrayList <Entero> ();
		for(int i = 0; i < m; i++)
			posiciones.add(new Entero(sc.nextInt()));
		while(posiciones.size() < n)
			posiciones.add(new Entero(0));
		String nombre = sc.next();
		Piloto yo = null;
		for(Piloto p : pilotos)
			if(p.nombre.equals(nombre))
				yo = p;
		System.out.print(posicion(pilotos, posiciones, yo) + 1);
		for(Piloto p : pilotos)
		{
			p.puntos = -p.puntos;
			p.invertido = true;
		}
		for(Entero t : posiciones)
			t.valor = -t.valor;
		System.out.println(" " + (n - posicion(pilotos, posiciones, yo)));
	}
}