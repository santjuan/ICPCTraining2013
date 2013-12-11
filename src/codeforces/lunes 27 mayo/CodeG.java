import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	
	static class Procedimiento
	{
		String nombre;
		ArrayList <String> tipos = new ArrayList <String> ();
		
		Procedimiento(String n)
		{
			nombre = n;
		}

		boolean match(String a, String b)
		{
			return a.equals(b) || a.equals("T");
		}
		
		boolean match(String n, ArrayList<String> t) 
		{
			if(!n.equals(nombre))
				return false;
			if(tipos.size() != t.size())
				return false;
			for(int i = 0; i < tipos.size(); i++)
			{
				if(!match(tipos.get(i), t.get(i)))
					return false;
			}
			return true;
		}
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		Procedimiento[] procedimientos = new Procedimiento[n];
		for(int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " \t(),");
			st.nextToken();
			procedimientos[i] = new Procedimiento(st.nextToken());
			while(st.hasMoreTokens())
				procedimientos[i].tipos.add(st.nextToken());
		}
		HashMap <String, String> variables = new HashMap <String, String> ();
		int nVariables = sc.nextInt();
		for(int i = 0; i < nVariables; i++)
		{
			String a = sc.next();
			variables.put(sc.next(), a);
		}
		int llamados = sc.nextInt();
		for(int i = 0; i < llamados; i++)
		{
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " \t(),");
			String nombre = st.nextToken();
			ArrayList <String> tipos = new ArrayList <String> ();
			while(st.hasMoreTokens())
				tipos.add(variables.get(st.nextToken()));
			int cuenta = 0;
			for(Procedimiento p : procedimientos)
				if(p.match(nombre, tipos))
					cuenta++;
			System.out.println(cuenta);
		}
	}

}
