import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
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
		int n = sc.nextInt();
		HashSet <String> forbidden = new HashSet <String> ();
		for(int i = 0; i < n; i++)
			forbidden.add(sc.next().toLowerCase());
		String buscadoS = sc.next();
		char lucky = Character.toLowerCase(sc.next().charAt(0));
		boolean[] pailas = new boolean[buscadoS.length()];
		for(int i = 0; i < buscadoS.length(); i++)
			for(int j = i + 1; j <= buscadoS.length(); j++)
			{
				if(forbidden.contains(buscadoS.substring(i, j).toLowerCase()))
					for(int k = i; k < j; k++)
						pailas[k] = true;
			}
		char[] buscado = buscadoS.toCharArray();
		for(int i = 0; i < buscado.length; i++)
		{
			if(pailas[i])
			{
				if(Character.toLowerCase(buscado[i]) == lucky)
				{
					if(lucky == 'a')
						buscado[i] = 'b';
					else
						buscado[i] = 'a';
				}
				else
					buscado[i] = lucky;
			}
			if(Character.isUpperCase(buscadoS.charAt(i)) && !Character.isUpperCase(buscado[i]))
				buscado[i] = Character.toUpperCase(buscado[i]);
			if(!Character.isUpperCase(buscadoS.charAt(i)) && Character.isUpperCase(buscado[i]))
				buscado[i] = Character.toLowerCase(buscado[i]);
		}
		System.out.println(new String(buscado));
	}

}
