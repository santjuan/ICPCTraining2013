import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;


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

		public String[] nextStringArray(int n) 
		{	
			String[] vals = new String[n];
			for(int i = 0; i < n; i++)
				vals[i] = next();
			return vals;
		}
	}
	
	static class Termino implements Comparable <Termino>
	{
		int coeficiente = 1;
		boolean inicio = false;
		
		@Override
		public int compareTo(Termino o) 
		{
			return  coeficiente - o.coeficiente;
		}
	}
	
	static ArrayList <Termino> s(LinkedList <Character> lista)
	{
		ArrayList <Termino> terminos = new ArrayList <Termino> ();
		if(lista.isEmpty())
			return terminos;
		terminos.add(summand(lista));
		terminos.addAll(expression(lista));
		return terminos;
	}
	
	static ArrayList <Termino> expression(LinkedList <Character> lista) 
	{
		ArrayList <Termino> terminos = new ArrayList <Termino> ();
		if(lista.isEmpty())
			return terminos;
		if(lista.peek() == '+')
		{
			lista.poll();
			terminos.add(summand(lista));
			terminos.addAll(expression(lista));
			return terminos;
		}
		else
		{
			lista.poll();
			Termino t = summand(lista);
			t.coeficiente = -t.coeficiente;
			terminos.add(t);
			terminos.addAll(expression(lista));
			return terminos;
		}
	}
	
	static Termino summand(LinkedList <Character> lista)
	{
		if(lista.peek() >= '0' && lista.peek() <= '9')
		{
			int val = leerNumero(lista);
			lista.poll();
			Termino t = summand(lista);
			t.coeficiente = val;
			return t;
		}
		else
		{
			if(lista.peek() == 'a')
			{
				lista.poll();
				lista.poll();
				lista.poll();
				return new Termino();
			}
			else
			{
				lista.poll();
				lista.poll();
				lista.poll();
				Termino t = new Termino();
				t.inicio = true;
				return t;
			}
		}
	}
	
	static int leerNumero(LinkedList <Character> lista) 
	{
		int val = 0;
		while(!lista.isEmpty() && lista.peek() >= '0' && lista.peek() <= '9')
		{
			val *= 10;
			val += lista.poll() - '0';
		}
		return val;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int a = sc.nextInt();
		LinkedList <Character> lista = new LinkedList <Character> ();
		for(char c : sc.nextLine().toCharArray())
			lista.add(c);
		ArrayList <Termino> terminos = s(lista);
		Collections.sort(terminos);
		long ans = 0;
		for(Termino t : terminos)
		{
			ans += a++ * t.coeficiente;
			if(t.inicio)
				ans += t.coeficiente;
		}
		System.out.println(ans);
	}

}
