import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
	
	static class Candidato implements Comparable <Candidato>
	{
		int votos;
		String partido;
		
		public Candidato(String p) 
		{
			partido = p;
		}

		@Override
		public int compareTo(Candidato o) 
		{
			return o.votos - votos;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int tc = sc.nextInt();
		for(int caso = 1; caso <= tc; caso++)
		{
			int n = sc.nextInt();
			if(caso != 1)
				System.out.println();
			HashMap <String, Candidato> hash = new HashMap <String, Candidato> ();
			for(int i = 0; i < n; i++)
			{
				String nombre = sc.nextLine();
				hash.put(nombre, new Candidato(sc.nextLine()));
			}
			int m = sc.nextInt();
			for(int i = 0; i < m; i++)
			{
				String cual = sc.nextLine();
				if(hash.containsKey(cual))
					hash.get(cual).votos++;
			}
			ArrayList <Candidato> todos = new ArrayList <Candidato> (hash.values());
			Collections.sort(todos);
			if(todos.get(0).votos == todos.get(1).votos)
				System.out.println("tie");
			else
				System.out.println(todos.get(0).partido);
		}
	}

}
