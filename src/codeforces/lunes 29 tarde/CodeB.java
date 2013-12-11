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
	}

	static class Persona implements Comparable <Persona>
	{
		static boolean porAltura;
		
		String nombre;
		int a;
		int altura;
		
		public Persona(String n, int a1) 
		{
			nombre = n;
			a = a1;
		}

		@Override
		public int compareTo(Persona o)
		{
			if(porAltura)
				return altura - o.altura;
			else
				return a - o.a;
		}
	}
	
	static ArrayList <Persona> solve(Persona[] personas)
	{
		Persona.porAltura = false;
		Arrays.sort(personas);
		ArrayList <Persona> actuales = new ArrayList <Persona> ();
		Persona.porAltura = true;
		int siguienteVal = 100000000;
		for(Persona p : personas)
		{
			if(p.a > actuales.size())
				return null;
			ArrayList <Persona> actualesO = new ArrayList <Persona> (actuales);
			Collections.sort(actualesO);
			int indiceInf = (actuales.size() - p.a) - 1;
			int indiceSup = (actuales.size() - p.a);
			if(indiceSup == actuales.size())
			{
				p.altura = siguienteVal;
				siguienteVal += 1000000;
			}
			else if(indiceInf == -1)
				p.altura = actualesO.get(0).altura - 1;
			else
			{
				int valInf = actualesO.get(indiceInf).altura;
				p.altura = valInf;
				for(int i = indiceInf; i >= 0; i--)
					actualesO.get(i).altura--;
			}
			actuales.add(p);
		}
		return actuales;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		Persona[] personas = new Persona[n];
		for(int i = 0; i < n; i++)
			personas[i] = new Persona(sc.next(), sc.nextInt());
		ArrayList <Persona> solucion = solve(personas);
		if(solucion == null)
			System.out.println("-1");
		else
		{
			for(Persona p : solucion)
				System.out.println(p.nombre + " " + p.altura);
		}
	}
}