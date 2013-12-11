import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
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
		int x;
		int tiempo;
		int tiempoBajado;
		
		@Override
		public int compareTo(Persona o)
		{
			return x - o.x;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		LinkedList <Persona> todas = new LinkedList <Persona> ();
		LinkedList <Persona> todasC = new LinkedList <Persona> ();
		for(int i = 0; i < n; i++)
		{
			Persona nueva = new Persona();
			nueva.tiempo = sc.nextInt();
			nueva.x = sc.nextInt();
			todas.add(nueva);
			todasC.add(nueva);
		}
		int tiempo = 0;
		while(!todas.isEmpty())
		{
			PriorityQueue <Persona> bus = new PriorityQueue <Persona> ();
			int mayorTiempo = 0;
			while(!todas.isEmpty() && bus.size() < m)
			{
				Persona p = todas.poll();
				mayorTiempo = p.tiempo;
				bus.add(p);
			}
			if(tiempo < mayorTiempo)
				tiempo = mayorTiempo;
			int posicionAnterior = 0;
			while(!bus.isEmpty())
			{
				ArrayList <Persona> aca = new ArrayList <Persona> ();
				aca.add(bus.poll());
				while(!bus.isEmpty() && bus.peek().x == aca.get(0).x)
					aca.add(bus.poll());
				tiempo += aca.get(0).x - posicionAnterior;
				for(Persona p : aca)
					p.tiempoBajado = tiempo;
				tiempo += (aca.size() / 2) + 1;
				posicionAnterior = aca.get(0).x;
				if(bus.isEmpty())
					tiempo += aca.get(0).x;
			}
		}
		for(Persona p : todasC)
			System.out.print(p.tiempoBajado + " ");
		System.out.println();
	}

}
