import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeC 
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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int r = sc.nextInt();
		int c = sc.nextInt();
		int[] primero = new int[r];
		Arrays.fill(primero, Integer.MAX_VALUE);
		int[] ultimo = new int[r];
		Arrays.fill(ultimo, Integer.MIN_VALUE);
		int ultimaFila = -1;
		for(int i = 0; i < r; i++)
		{
			char[] e = sc.next().toCharArray();
			for(int j = 0; j < c; j++)
				if(e[j] == 'W')
				{
					primero[i] = Math.min(primero[i], j);
					ultimo[i] = Math.max(ultimo[i], j);
					ultimaFila = i;
				}
		}
		int respuesta = 0;
		int fila = 0;
		boolean derecha = true;
		int posicionActual = 0;
		while(fila <= ultimaFila)
		{
			if(derecha)
			{
				if(posicionActual > primero[fila])
					throw(new RuntimeException(fila + ""));
				int posSiguiente = Math.max(ultimo[fila], fila + 1 == r ? 0 : ultimo[fila + 1]);
				if(posSiguiente == Integer.MIN_VALUE || posSiguiente < posicionActual)
					posSiguiente = posicionActual;
				respuesta += (posSiguiente - posicionActual);
				posicionActual = posSiguiente;
				derecha = false;
			}
			else
			{
				if(posicionActual < ultimo[fila])
					throw(new RuntimeException(fila + ""));
				int posSiguiente = Math.min(primero[fila], fila + 1 == r ? Integer.MAX_VALUE : primero[fila + 1]);
				if(posSiguiente == Integer.MAX_VALUE || posSiguiente > posicionActual)
					posSiguiente = posicionActual;
				respuesta += (posicionActual - posSiguiente);
				posicionActual = posSiguiente;
				derecha = true;
			}
			respuesta++;
			fila++;
		}
		System.out.println(respuesta == 0 ? 0 : respuesta - 1);
	}

}
