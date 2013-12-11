import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class LiveC 
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
	
	public static String leerSiguienteNoBlanco(Scanner sc)
	{
		while(true)
		{
			String linea = sc.nextLine();
			if(linea == null)
				return null;
			else if(!linea.trim().isEmpty())
				return linea;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(true)
		{
			String l = leerSiguienteNoBlanco(sc);
			if(l == null)
			{
				bw.flush();
				bw.close();
				return;
			}
			StringTokenizer st = new StringTokenizer(l);
			st.nextToken();
			int w = Integer.parseInt(st.nextToken());
			int nPagina = 1;
			outer:
			while(true)
			{
				String v1 = sc.nextLine();
				if(v1.equals("##"))
					break;
				ArrayList <char[]> filas = new ArrayList <char[]> ();
				while(true)
				{
					String linea = sc.nextLine();
					if(linea == null)
					{
						bw.flush();
						bw.close();
						return;
					}
					if(linea.trim().equals("##"))
						break outer;
					if(linea.trim().equals("#"))
						break;
					filas.add(linea.toCharArray());
				}
				ArrayList <StringTokenizer> columnasString = new ArrayList <StringTokenizer> ();
				for(int i = 0; i < (filas.isEmpty() ? 0 : filas.get(0).length); i++)
				{
					StringBuilder acum = new StringBuilder();
					for(char[] v : filas)
						if(v.length <= i)
							acum.append(' ');
						else
							acum.append(v[i]);
					columnasString.add(new StringTokenizer(acum.toString()));
				}
				LinkedList <String> palabras = new LinkedList <String> ();
				while(true)
				{
					boolean alguna = false;
					for(StringTokenizer s : columnasString)
						if(s.hasMoreTokens())
						{
							alguna = true;
							palabras.add(s.nextToken());
						}
					if(!alguna)
						break;
				}
				bw.write("Page number " + nPagina++);
				bw.write('\n');
				if(!palabras.isEmpty())
				{
					StringBuilder acum = new StringBuilder(palabras.poll());
					while(!palabras.isEmpty())
					{
						String p = palabras.poll();
						if(acum.length() + p.length() + 1 > w)
						{
							bw.write(acum.toString());
							bw.write('\n');
							acum.setLength(0);
							acum.append(p);
						}
						else
							acum.append(' ').append(p);
					}
					if(acum.length() != 0)
					{
						bw.write(acum.toString());
						bw.write('\n');
					}
				}
				else
				{
					bw.write("This page is empty.");
					bw.write('\n');
				}
			}
		}
	}

}
