import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveB 
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
	
	static int gcd(int m, int n)
	{
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) return m;
        else return gcd(n, m % n);
    }
	
	private static void garantizarTam(StringBuilder stringBuilder, int length)
	{
		while(stringBuilder.length() < length)
			stringBuilder.append(' ');
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int caso = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				return;
			int total = 0;
			for(int v : sc.nextIntArray(n))
				total += v;
			boolean negativo = total < 0;
			total = Math.abs(total);
			int gcd = gcd(total, n);
			total /= gcd;
			n /= gcd;
			int entero = 0;
			if(total > n)
			{
				entero = total / n;
				total -= entero * n;
			}
			gcd = gcd(total, n);
			total /= gcd;
			n /= gcd;
			StringBuilder[] salida = new StringBuilder[]{new StringBuilder(), new StringBuilder(), new StringBuilder()};
			if(negativo)
				salida[1].append("- ");
			if(entero != 0)
				salida[1].append(entero);
			garantizarTam(salida[0], salida[1].length());
			garantizarTam(salida[2], salida[1].length());
			String c = n + "";
			for(int i = 0; i < c.length(); i++)
				salida[1].append('-');
			salida[2].append(c);
			String b = total + "";
			for(int i = 0; i < c.length() - b.length(); i++)
				salida[0].append(' ');
			salida[0].append(b);
			System.out.println("Case " + caso++ + ":");
			if(total == 0)
				System.out.println((negativo ? "- " : "") + entero);
			else
			{
				if(!salida[0].toString().trim().isEmpty())
					System.out.println(salida[0].toString());
				System.out.println(salida[1].toString());
				if(!salida[2].toString().trim().isEmpty())
					System.out.println(salida[2].toString());
			}
		}
	}
}