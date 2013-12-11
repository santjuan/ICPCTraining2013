import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class CodeF 
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
	
	private static void generarDivisores(ArrayList<Integer> factores, ArrayList<Integer> numeroVeces, int i, ArrayList<Integer> respuesta, int acum)
	{
		if(i == factores.size())
		{
			respuesta.add(acum);
			return;
		}
		for(int j = 0; j <= numeroVeces.get(i); j++)
		{
			generarDivisores(factores, numeroVeces, i + 1, respuesta, acum);
			acum *= factores.get(i);
		}
	}
	
	public static ArrayList <Integer> generarDivisores(int n)
	{
		ArrayList <Integer> factores = new ArrayList <Integer> ();
		ArrayList <Integer> numeroVeces = new ArrayList <Integer> ();
		int limite = (int) Math.ceil(Math.sqrt(n)) + 1;
		limite = Math.min(n, limite);
		for(int i = 2; i <= limite; i++)
		{
			if(n % i == 0)
			{
				int cuenta = 0;
				while(n % i == 0)
				{
					n /= i;
					cuenta++;
				}
				factores.add(i);
				numeroVeces.add(cuenta);
			}
		}
		if(n != 1)
		{
			if(factores.contains(n))
				numeroVeces.set(factores.indexOf(n), numeroVeces.get(factores.indexOf(n)) + 1);
			else
			{
				factores.add(n);
				numeroVeces.add(1);
			}
		}
			
		ArrayList <Integer> respuesta = new ArrayList <Integer> ();
		generarDivisores(factores, numeroVeces, 0, respuesta, 1);
		return respuesta;
	}

	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		TreeMap <Integer, Integer> ultimos = new TreeMap <Integer, Integer> ();
		for(int i = 0; i < n; i++)
		{
			int x = sc.nextInt();
			int y = sc.nextInt();
			ArrayList <Integer> divs = generarDivisores(x);
			int cuenta = 0;
			for(int div : divs)
			{
				if(!ultimos.containsKey(div) || ultimos.get(div) < i - y)
					cuenta++;
				ultimos.put(div, i);
			}
			System.out.println(cuenta);
		}
	}

}
