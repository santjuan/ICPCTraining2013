import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class B 
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
	
	static class Estado
	{
		int[] matriz;
		int n;
		
		public Estado(int[] matriz, int n) 
		{
			this.matriz = matriz;
			this.n = n;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(matriz);
			result = prime * result + n;
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Estado other = (Estado) obj;
			if (!Arrays.equals(matriz, other.matriz))
				return false;
			if (n != other.n)
				return false;
			return true;
		}
	}
	
	static int nTotal;
	static int nBars;
	static int nInicial;
	
	static HashMap <Long, double[]> dp = new HashMap <Long, double[]> ();
	
	
	static int[] cargar(long estado)
	{
		int[] salida = new int[nBars];
		int count = 0;
		for(int i = 0; i < nBars; i++)
		{
			salida[i] += ((estado >> count) & 63);
			count += 6;
		}
		return salida;
	}
	
	static long comprimir(int[] estado)
	{
		long sal = 0;
		int count = 0;
		for(int i = 0; i < nBars; i++)
		{
			sal |= ((long) estado[i]) << count;
			count += 6;
		}
		return sal;
	}
	
	
	static double[] darSalida(int[] estado)
	{
		int[] estadoOrdenado = estado.clone();
		Arrays.sort(estadoOrdenado);
		double[] resPermutado = dp(comprimir(estadoOrdenado));
		double[] salida = new double[estado.length];
		for(int i = 0; i < estado.length; i++)
			for(int j = 0; j < estado.length; j++)
			{
				if(estado[i] == estadoOrdenado[j])
					salida[i] = resPermutado[j];
			}
		return salida;
	}
	
	static double[] dp(long estado)
	{
		if(dp.containsKey(estado))
			return dp.get(estado);
		int[] estadoA = cargar(estado);
		double sumaTotal = 0;
		for(int i = 0; i < nBars; i++)
			sumaTotal += estadoA[i];
		int n = ((int) sumaTotal) - nInicial;
		if(n == nTotal)
		{
			double[] res = new double[nBars];
			long mejor = 0;
			for(int i = 0; i < nBars; i++)
				mejor = Math.max(mejor, estadoA[i]);
			double cuantos = 0;
			for(int i = 0; i < nBars; i++)
				if(estadoA[i] == mejor)
					cuantos++;
			for(int i = 0; i < nBars; i++)
				if(estadoA[i] == mejor)
					res[i] += 1 / cuantos;
			dp.put(estado, res);
			return res;
		}
		double[] miRes = new double[nBars];
		for(int i = 0; i < nBars; i++)
		{
			estadoA[i]++;
			double[] res = darSalida(estadoA);
			estadoA[i]--;
			double factor = estadoA[i] / sumaTotal;
			for(int j = 0; j < res.length; j++)
				miRes[j] += res[j] * factor;
		}
		dp.put(estado, miRes);
		return miRes;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			String nPubsString = sc.next();
			if(nPubsString == null)
				return;
			nBars = Integer.parseInt(nPubsString);
			nTotal = sc.nextInt();
			nInicial = 0;
			int[] entrada = new int[nBars];
			for(int i = 0; i < nBars; i++)
			{
				entrada[i] = sc.nextInt();
				nTotal -= entrada[i];
				nInicial += entrada[i];
			}
			dp.clear();
			long estado = 0;
			for(int i = 0; i < entrada.length; i++)
				estado |= ((long) entrada[i]) << (i * 6);
			dp.clear();
			double[] res = dp(estado);
			for(int i = 0; i < res.length; i++)
				System.out.println(String.format("pub %d: %.2f", (i + 1), res[i] * 100).replace(",", ".") + " %");
		}
		
	}
}