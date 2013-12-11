import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		TreeMap <Integer, Integer> veces = new TreeMap <Integer, Integer> ();
		int[] valores = new int[n];
		for(int i = 0; i < n; i++)
		{
			int siguiente = sc.nextInt();
			if(!veces.containsKey(siguiente))
				veces.put(siguiente, 0);
			veces.put(siguiente, veces.get(siguiente) + 1);
			valores[i] = siguiente;
		}
		if(veces.size() == 1)
			System.out.println("Exemplary pages.");
		else if(veces.size() != 3 && n != 2)
			System.out.println("Unrecoverable configuration.");
		else
		{
			int min = 0;
			int max = 0;
			for(int i = 0; i < n; i++)
			{
				if(valores[min] > valores[i])
					min = i;
				if(valores[max] < valores[i])
					max = i;
			}
			int cuantos = (valores[max] - valores[min]);
			valores[max] -= cuantos / 2;
			valores[min] += cuantos / 2;
			boolean ok = true;
			for(int i = 0; i < n; i++)
				if(valores[i] != valores[max])
					ok = false;
			if(((cuantos & 1) == 1) || !ok)
				System.out.println("Unrecoverable configuration.");
			else
				System.out.println(cuantos / 2 + " ml. from cup #" + (min + 1) + " to cup #" + (max + 1) + ".");
		}
	}

}
