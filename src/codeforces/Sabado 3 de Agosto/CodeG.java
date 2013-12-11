import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeG 
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

	
	static class Jugador implements Comparable <Jugador>
	{
		int ganados;
		int ganadosActual;
		int rondas;
		String nombre;
		
		Jugador(String n, int g)
		{
			nombre = n;
			ganados = g;
		}

		@Override
		public int compareTo(Jugador otro)
		{
			if(rondas == otro.rondas)
				return nombre.compareTo(otro.nombre);
			return otro.rondas - rondas;
		}
	}
	
	static void resolver(int m, Jugador[] jugadores)
	{
		int[] esperados = new int[m + 1];
		esperados[0] = 1 << m;
		for(int i = 1; i <= m; i++)
			esperados[i] = esperados[i - 1] >>> 1;
		for(int a = 0; a <= m; a++)
		{
			for(int b = 0; b <= m; b++)
			{
				if(a + b != m) continue;
				int[] cuantos = new int[m + 1];
				for(Jugador g : jugadores) g.ganadosActual = g.ganados;
				boolean paila = false;
				for(Jugador g : jugadores)
				{
					int rondaActual = 0;
					while(rondaActual <= m)
					{
						if(rondaActual < a)
							g.ganadosActual -= 2;
						else
							g.ganadosActual -= 3;
						if(g.ganadosActual < 0)
							break;
						rondaActual++;
						if(g.ganadosActual == 0)
							break;
					}
					if(g.ganadosActual > 0) 
						paila = true;
					if(rondaActual > m)
						paila = true;
					g.rondas = rondaActual;
					if(rondaActual <= m)
						for(int i = 0; i <= rondaActual; i++)
							cuantos[i]++;
				}
				for(int i = 0; i <= m; i++)
					if(cuantos[i] != esperados[i])
						paila = true;
				if(!paila) return;
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < n; i++)
		{
			int m = sc.nextInt();
			int limite = 1 << m;
			Jugador[] jugadores = new Jugador[limite];
			for(int j = 0; j < limite; j++)
				jugadores[j] = new Jugador(sc.next(), sc.nextInt());
			resolver(m, jugadores);
			Arrays.sort(jugadores);
			for(Jugador j : jugadores)
				bw.write(j.nombre + "\n");
		}
		bw.flush();
		bw.close();
	}
}