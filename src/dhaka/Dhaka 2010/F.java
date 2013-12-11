import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.StringTokenizer;


public class F
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
		int q = sc.nextInt();
		String vals = sc.next();
		BitSet[] letras = new BitSet[n];
		for(int i = 0; i < n; i++)
		{
			BitSet actual = new BitSet(16 * 43);
			int cuenta = 0;
			for(int j = 0; j < 16; j++)
			{
				char[] current = sc.nextLine().toCharArray();
				for(int k = 0; k < 43; k++)
					actual.set(cuenta + k, current[k] == '*');
				cuenta += 43;
			}
			letras[i] = actual;
			sc.nextLine();
		}
		for(int caso = 1; caso <= q; caso++)
		{
			char[] actual = sc.next().toCharArray();
			BitSet tmp = new BitSet(16 * 43);
			BitSet tmp1 = new BitSet(16 * 43);
			StringBuilder sb = new StringBuilder(actual.length);
			for(int i = 0; i < actual.length; i++)
			{
				tmp.clear();
				for(int j = 0; j < actual.length; j++)
					if(i != j)
						tmp.or(letras[vals.indexOf(actual[j])]);
				tmp1.clear();
				tmp1.or(letras[vals.indexOf(actual[i])]);
				tmp1.andNot(tmp);
				sb.append(tmp1.isEmpty() ? 'N' : 'Y');
			}
			System.out.println("Query " + caso + ": " + sb.toString());
		}
	}

}
