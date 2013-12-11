import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		LinkedList <Integer> valores = new LinkedList <Integer> ();
		for(int i = 0; i < n; i++)
			valores.add(sc.nextInt());
		int esperado = 1;
		ArrayList <Integer> cambiados = new ArrayList <Integer> ();
		ArrayList <Integer> todos = new ArrayList <Integer> ();
		int primero = 0;
		int ultimo = 0;
		for(int i = 0; i < n; i++)
		{
			int siguiente = valores.poll();
			if(siguiente == esperado)
			{
				todos.add(siguiente);
				esperado++;
			}
			else
			{
				cambiados.add(siguiente);
				while(!valores.isEmpty() && valores.peek() < siguiente)
					cambiados.add(valores.poll());
				Collections.reverse(cambiados);
				todos.addAll(cambiados);
				todos.addAll(valores);
				primero = cambiados.get(0);
				ultimo = cambiados.get(cambiados.size() - 1);
				break;
			}
		}
		boolean malo = todos.size() != n;
		for(int i = 1; !malo && i <= n; i++)
		{
			if(todos.get(i - 1) != i)
				malo = true;
		}
		if(malo)
			System.out.println("0 0");
		else
			System.out.println(primero + " " + ultimo);
	}

}
