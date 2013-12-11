import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

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
	
	
	static boolean[] usado;
	static int[] noDeseado;
	static int[] actual;
	static ArrayList <Integer> [] grafo;
	static TreeSet <Integer> mapa = new TreeSet <Integer> ();
	
	static void push(int cual)
	{
		actual[cual]++;
		if(!usado[cual] && actual[cual] == noDeseado[cual])
			mapa.add(cual);
		else
			mapa.remove(cual);
	}
	
	static void pushFirst(int cual)
	{
		usado[cual] = true;
		push(cual);
		for(int i : grafo[cual])
			push(i);
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		grafo = new ArrayList[n];
		for(int i = 0; i < n; i++)
			grafo[i] = new ArrayList <Integer> ();
		for(int i = 0; i < m; i++)
		{
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			grafo[u].add(v);
			grafo[v].add(u);
		}
		actual = new int[n];
		usado = new boolean[n];
		noDeseado = sc.nextIntArray(n);
		StringBuilder sb = new StringBuilder();
		int k = 0;
		for(int i = 0; i < n; i++)
			if(actual[i] == noDeseado[i])
				mapa.add(i);
		while(!mapa.isEmpty())
		{
			int current = mapa.pollFirst();
			k++;
			pushFirst(current);
			sb.append(" ").append(current + 1);
		}
		System.out.println(k);
		System.out.println(sb.toString().trim());
	}
}