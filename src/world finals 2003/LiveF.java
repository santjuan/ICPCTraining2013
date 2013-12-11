import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class LiveF 
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
	
	static class Nodo
	{
		Nodo[] hijos = new Nodo[4];
		boolean hoja;
		boolean color;
		
		Nodo()
		{
		}
		
		Nodo(LinkedList <Character> entrada)
		{
			hoja = entrada.poll().charValue() == '1';
			if(hoja)
				color = entrada.poll().charValue() == '1';
			else
			{
				for(int i = 0; i < 4; i++)
					hijos[i] = new Nodo(entrada);
			}
		}
		
		void print(StringBuilder sb)
		{
			if(hoja)
			{
				sb.append('1');
				sb.append(color ? '1' : '0');
			}
			else
			{
				sb.append('0');
				for(int i = 0; i < 4; i++)
					hijos[i].print(sb);
			}
		}
	}
	
	static Nodo match(Nodo a, Nodo b)
	{
		if(a.hoja)
		{
			if(a.color)
				return b;
			else
				return a;
		}
		else if(b.hoja)
			return match(b, a);
		else
		{
			Nodo nuevo = new Nodo();
			nuevo.hoja = false;
			for(int i = 0; i < 4; i++)
				nuevo.hijos[i] = match(a.hijos[i], b.hijos[i]);
			if(nuevo.hijos[0].hoja && nuevo.hijos[1].hoja && nuevo.hijos[2].hoja && nuevo.hijos[3].hoja)
			{
				boolean col = nuevo.hijos[0].color;
				if(nuevo.hijos[1].color == col && nuevo.hijos[2].color == col && nuevo.hijos[3].color == col)
				{
					nuevo.hoja = true;
					nuevo.color = col;
				}
			}
			return nuevo;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		boolean primero = true;
		int caso = 1;
		while(true)
		{
			BigInteger a = new BigInteger(sc.next(), 16);
			BigInteger b = new BigInteger(sc.next(), 16);
			if(a.equals(BigInteger.ZERO) && b.equals(BigInteger.ZERO)) return;
			if(!primero) System.out.println();
			primero = false;
			String entrada = a.toString(2);
			entrada = entrada.substring(1);
			Nodo primera = new Nodo(darLinkedList(entrada));
			entrada = b.toString(2);
			entrada = entrada.substring(1);
			Nodo segunda = new Nodo(darLinkedList(entrada));
			Nodo solucion = match(primera, segunda);
			StringBuilder respuesta = new StringBuilder();
			solucion.print(respuesta);
			System.out.println("Image " + caso++ + ":");
			System.out.println(new BigInteger("1" + respuesta.toString(), 2).toString(16).toUpperCase());
		}
	}

	private static LinkedList<Character> darLinkedList(String entrada) 
	{
		LinkedList <Character> lista = new LinkedList <Character> ();
		for(char c : entrada.toCharArray()) lista.add(c);
		return lista;
	}
}