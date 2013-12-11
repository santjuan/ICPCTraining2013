import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class CodeD 
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
		int k = sc.nextInt();
		String tituloS = sc.nextLine();
		boolean posible = true;
		char[] titulo = tituloS.toCharArray();
		LinkedList <Character> faltantes = new LinkedList <Character> ();
		for(int i = 0; i < k; i++)
			faltantes.add((char) ('a' + i));
		LinkedList <Integer> pares = new LinkedList <Integer> ();
		int limite = (titulo.length >> 1) + (titulo.length & 1);
		for(int i = 0; i < limite; i++)
		{
			int opuesto = titulo.length - i - 1;
			if(titulo[i] == '?')
			{
				if(titulo[opuesto] == '?')
					pares.add(i);
				else
					titulo[i] = titulo[opuesto];
			}
			else if(titulo[opuesto] == '?')
				titulo[opuesto] = titulo[i];
			else
			{
				if(titulo[i] != titulo[opuesto])
				{
					posible = false;
					break;
				}
			}
			if(titulo[i] != '?')
				faltantes.remove(Character.valueOf(titulo[i]));
		}
		if(!posible)
			System.out.println("IMPOSSIBLE");
		else
		{
			while(faltantes.size() != 0)
			{
				if(pares.size() == 0)
				{
					posible = false;
					break;
				}
				int val = pares.pollLast();
				char f = faltantes.pollLast();
				titulo[val] = f;
				titulo[titulo.length - val - 1] = f;
			}
			if(!posible)
				System.out.println("IMPOSSIBLE");
			else
			{
				while(pares.size() != 0)
				{
					int val = pares.pollLast();
					titulo[val] = 'a';
					titulo[titulo.length - val - 1] = 'a';
				}
				System.out.println(new String(titulo));
			}
		}
	}
}