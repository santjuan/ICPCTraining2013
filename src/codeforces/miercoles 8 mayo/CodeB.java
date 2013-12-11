import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	
	static char[] primero;
	static char[] segundo;
	static int actualPrimero;
	static int actualSegundo;
	static int[] digitosPrimero;
	static int[] digitosSegundo;
	
	static void ponerPrimero(int i)
	{
		digitosPrimero[i]--;
		primero[actualPrimero--] = (char) ('0' + i);
	}
	
	static void ponerSegundo(int i)
	{
		digitosSegundo[i]--;
		segundo[actualSegundo--] = (char) ('0' + i);
		
	}
	
	static boolean puedoPonerAmbos(int i, int j)
	{
		return digitosPrimero[i] > 0 && digitosSegundo[j] > 0;
	}
	
	static void ponerAmbos(int i, int j)
	{
		ponerPrimero(i);
		ponerSegundo(j);
	}

	static void arreglar()
	{
		if(primero[0] == '0')
			for(int i = 0; i < primero.length; i++)
			{
				if(primero[i] != '0')
				{
					primero[0] = primero[i];
					primero[i] = '0';
					break;
				}
			}
		if(segundo[0] == '0')
			for(int i = 0; i < segundo.length; i++)
			{
				if(segundo[i] != '0')
				{
					segundo[0] = segundo[i];
					segundo[i] = '0';
					break;
				}
			}
	}
	
	static int contarCeros() 
	{
		int carry = 0;
		int nCeros = 0;
		for(int i = primero.length - 1; i >= 0; i--)
		{
			int res = (primero[i] - '0') + (segundo[i] - '0') + carry;
			int actual = res % 10;
			carry = res / 10;
			if(actual == 0)
				nCeros++;
			else
				break;
		}
		return nCeros;
	}
	
	static Object[] intentarSolucion(int[] digitos, int n, int diez)
	{
		digitosPrimero = digitos.clone();
		digitosSegundo = digitos.clone();
		primero = new char[n];
		segundo = new char[n];
		actualPrimero = n - 1;
		actualSegundo = n - 1;
		if(diez != 0 && !puedoPonerAmbos(diez, 10 - diez))
			return null;
		while(digitosPrimero[0] > (digitosPrimero[9] - ((diez == 1 || diez == 9) ? 1 : 0)))
			ponerAmbos(0, 0);
		if(diez != 0)
			ponerAmbos(diez, 10 - diez);
		while(puedoPonerAmbos(0, 9))
			ponerAmbos(0, 9);
		while(puedoPonerAmbos(9, 0))
			ponerAmbos(9, 0);
		for(int i = 1; i < 10; i++)
		{
			while(puedoPonerAmbos(i, 9 - i))
				ponerAmbos(i, 9 - i);
		}
		for(int i = 1; i < 10; i++)
		{
			while(digitosPrimero[i] > 0)
				ponerPrimero(i);
			while(digitosSegundo[i] > 0)
				ponerSegundo(i);
		}
		arreglar();
		return new Object[] {contarCeros(), new String(primero), new String(segundo)};
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		char[] numero = sc.next().toCharArray();
		int[] digitos = new int[10];
		for(char c : numero)
			digitos[c - '0']++;
		Object[] mejor = new Object[]{0, new String(numero), new String(numero)};
		for(int i = 0; i < 10; i++)
		{
			Object[] actual = intentarSolucion(digitos, numero.length, i);
			if(actual != null && ((Integer) actual[0]).intValue() > ((Integer) mejor[0]).intValue())
				mejor = actual;
		}
		System.out.println(mejor[1]);
		System.out.println(mejor[2]);
	}
}