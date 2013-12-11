import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class LiveA 
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
	
	static class Estudiante implements Comparable <Estudiante>
	{
		long[] vals = new long[3];
		long id;
		long distance;
		boolean gano;
		int orden;
		
		Estudiante crearHijo(Estudiante aComparar)
		{
			Estudiante nuevo = new Estudiante();
			for(int i = 0; i < 3; i++)
				nuevo.distance += Math.abs(aComparar.vals[i] - vals[i]);
			nuevo.id = id;
			nuevo.orden = orden;
			nuevo.gano = gano;
			return nuevo;
		}

		public int compareTo(Estudiante o)
		{
			if(distance != o.distance)
				return Long.valueOf(distance).compareTo(o.distance);
			return orden - o.orden;
		}
	}
	
	public static String leerSiguienteNoBlanco(Scanner sc)
	{
		while(true)
		{
			String linea = sc.nextLine();
			if(linea == null)
				return null;
			else if(!linea.trim().isEmpty())
				return linea;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		ArrayList <Estudiante> originales = new ArrayList <Estudiante> ();
		while(true)
		{
			String s = sc.nextLine().trim();
			if(s.isEmpty())
				break;
			StringTokenizer st = new StringTokenizer(s);
			Estudiante nuevo = new Estudiante();
			nuevo.id = Long.parseLong(st.nextToken());
			for(int i = 0; i < 3; i++)
				nuevo.vals[i] = Long.parseLong(st.nextToken());
			nuevo.gano = Integer.parseInt(st.nextToken()) == 1;
			nuevo.orden = originales.size();
			originales.add(nuevo);
		}
		while(true)
		{
			String s = leerSiguienteNoBlanco(sc);
			if(s == null)
				return;
			StringTokenizer st = new StringTokenizer(s);
			int k = Integer.parseInt(st.nextToken());
			while(true)
			{
				String l = sc.nextLine();
				if(l == null)
					return;
				if(l.trim().isEmpty())
					break;
				st = new StringTokenizer(l);
				Estudiante nuevo = new Estudiante();
				nuevo.id = Long.parseLong(st.nextToken());
				for(int i = 0; i < 3; i++)
					nuevo.vals[i] = Long.parseLong(st.nextToken());
				ArrayList <Estudiante> actuales = new ArrayList <Estudiante> ();
				for(Estudiante e : originales)
					actuales.add(e.crearHijo(nuevo));
				Collections.sort(actuales);
				int ganaron = 0;
				int perdieron = 0;
				for(int i = 0; i < k; i++)
					if(actuales.get(i).gano)
						ganaron++;
					else
						perdieron++;
				System.out.println(nuevo.id + " " + (ganaron > perdieron ? 1 : 0));
			}
		}
	}

}
