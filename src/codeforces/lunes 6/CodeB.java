import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
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
	

	static abstract class Base
	{
		abstract void print(BufferedWriter bw) throws IOException;
	}
	
	static class Int extends Base
	{
		static final char[] intF = "int".toCharArray();
		
		@Override
		void print(BufferedWriter bw) throws IOException 
		{
			bw.write(intF);
		}
	}
	
	static class Pair extends Base
	{
		Base a;
		Base b;
		static final char[] x = "pair<".toCharArray();
		static final char[] y = ",".toCharArray();
		static final char[] z = ">".toCharArray();
		
		@Override
		void print(BufferedWriter bw) throws IOException 
		{
			bw.write(x);
			a.print(bw);
			bw.write(y);
			b.print(bw);
			bw.write(z);
		}
	}
	
	
	static Base solve(LinkedList <String> lista)
	{
		String s = lista.poll();
		if(s.equals("int"))
			return new Int();
		else
		{
			Pair actual = new Pair();
			actual.a = solve(lista);
			actual.b = solve(lista);
			return actual;
		}
	}
	
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		LinkedList <String> lista = new LinkedList <String> ();
		while(st.hasMoreTokens())
			lista.add(st.nextToken());
		boolean paila = false;
		Base b = null;
		try
		{
			b = solve(lista);
		}
		catch(Exception e)
		{
			paila = true;
		}
		paila |= !lista.isEmpty();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		if(paila)
			System.out.println("Error occurred");
		else
		{
			b.print(bw);
			bw.write("\n");
			bw.flush();
			bw.close();
		}
			
	}

}

