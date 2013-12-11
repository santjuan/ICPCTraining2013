import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


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
				res[i] = nextDouble();
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
	
	static LinkedList <Character> entrada = new LinkedList <Character> (); 

	
	static String leer()
	{
		StringBuilder sb = new StringBuilder();
		while(!entrada.isEmpty() && entrada.peek().charValue() == ' ')
			entrada.poll();
		while(!entrada.isEmpty() && entrada.peek().charValue() != ' ')
		{
			if(sb.length() != 0 && sb.charAt(sb.length() - 1) >= '0' && sb.charAt(sb.length() - 1) <= '9' && (entrada.peek() < '0' || entrada.peek() > '9')) break;
			sb.append(entrada.poll());
			if(sb.length() == 1 && sb.toString().equals(",")) return sb.toString();
			if(sb.length() == 3 && sb.toString().equals("...")) return sb.toString();
		}
		return sb.toString();
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		for(char c : sc.nextLine().toCharArray()) entrada.add(c);
		StringBuilder sb = new StringBuilder();
		while(!entrada.isEmpty())
		{
			String n = leer().trim();
			if(n.trim().isEmpty()) break;
			if(n.equals(","))
			{
				sb.append(n);
				sb.append(' ');
			}
			else
			{
				if(sb.length() == 0 || sb.charAt(sb.length() - 1) == ' ')
					sb.append(n);
				else
				{
					if(!(n.charAt(0) >= '0' && n.charAt(0) <= '9' && sb.charAt(sb.length() - 1) == '.'))
						sb.append(' ');
					sb.append(n);
				}
			}
		}
		System.out.println(sb.toString().trim());
	}
}