import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


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
		int[][] entrada = new int[n][2];
		for(int i = 0; i < n; i++)
		{
			String tipo = sc.next();
			if(tipo.equals("win"))
				entrada[i][0] = 0;
			else
				entrada[i][0] = 1;
			entrada[i][1] = sc.nextInt();
		}
		boolean[] borrar = new boolean[n];
		boolean[] vendido = new boolean[2001];
		int[] ultimaCompra = new int[2001];
		Arrays.fill(ultimaCompra, -1);
		for(int i = n - 1; i >= 0; i--)
		{
			if(entrada[i][0] == 1)
				vendido[entrada[i][1]] = true;
			else
			{
				if(!vendido[entrada[i][1]])
					borrar[i] = true;
				else
				{
					if(ultimaCompra[entrada[i][1]] == -1)
					{
						for(int j = i + 1; j < n; j++)
						{
							if(entrada[j][0] == 1 && entrada[j][1] == entrada[i][1])
								break;
							if(borrar[j])
								continue;
							if(entrada[j][0] == 0 && entrada[j][1] > entrada[i][1])
							{
								borrar[i] = true;
								break;
							}
						}
						if(!borrar[i])
							ultimaCompra[entrada[i][1]] = 1;
						
					}
					else
						borrar[i] = true;
				}
			}
		}
		ArrayList <int[]> todos = new ArrayList <int[]> ();
		for(int i = 0; i < n; i++)
			if(!borrar[i])
				todos.add(entrada[i]);
		entrada = todos.toArray(new int[0][]);
		int memoriaActual = -1;
		BigInteger acumulado = BigInteger.ZERO;
		for(int i = 0; i < entrada.length; i++)
		{
			if(entrada[i][0] == 0)
			{
				if(entrada[i][1] > memoriaActual)
					memoriaActual = entrada[i][1];
			}
			else
			{
				if(entrada[i][1] == memoriaActual)
				{
					acumulado = acumulado.add(BigInteger.valueOf(2).pow(memoriaActual));
					memoriaActual = -1;
				}
			}
		}
		System.out.println(acumulado);
	}
}