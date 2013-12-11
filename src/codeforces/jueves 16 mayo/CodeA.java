import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


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
	
	static void sumar(String s, TreeMap <String, Integer> mapa, int cuanto)
	{
		if(!mapa.containsKey(s))
			mapa.put(s, 0);
		int val = mapa.get(s);
		val += cuanto;
		if(val <= 0)
			mapa.remove(s);
		else
			mapa.put(s, val);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		TreeMap <String, Integer> con = new TreeMap <String, Integer> ();
		TreeMap < Integer, TreeMap <String, Integer> > sin = new TreeMap < Integer, TreeMap <String, Integer> > ();	
		int n = sc.nextInt();
		String[] f = sc.nextStringArray(n);
		String delim = sc.next();
		int tam = n / 2;
		for(String s : f)
		{
			sumar(s + delim, con, 1);
			if(!sin.containsKey(s.length()))
				sin.put(s.length(), new TreeMap <String, Integer> ());
			sumar(s, sin.get(s.length()), 1);
			tam += s.length();
		}
		tam /= (n / 2);
		StringBuilder sb = new StringBuilder();
		while(!con.isEmpty())
		{
			String a = con.firstKey();
			sumar(a, con, -1);
			int tamBuscado = tam - a.length();
			sumar(a.substring(0, a.length() - delim.length()), sin.get(a.length() - delim.length()), -1);
			String b = sin.get(tamBuscado).firstKey();
			sumar(b, sin.get(tamBuscado), -1);
			sumar(b + delim, con, -1);
			sb.append(a).append(b).append('\n');
		}
		System.out.print(sb.toString());
		System.out.flush();
	}
}