import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class A 
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "aplusb";
		BufferedReader br;
		StringTokenizer st = new StringTokenizer("");
		PrintStream out;		
		
		Scanner()
		{
			if(debug)
			{
				br = new BufferedReader(new InputStreamReader(System.in));
				out = System.out;
			}
			else
			{
				try 
				{
					br = new BufferedReader(new FileReader(name + ".in"));
					out = new PrintStream(new BufferedOutputStream(new FileOutputStream(name + ".out")), false);
				}
				catch (FileNotFoundException e) 
				{
					throw(new RuntimeException());
				}
			}
		}
		
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
		
		public void println(String s)
		{
			out.println(s);
		}
		
		public void print(String s)
		{
			out.print(s);
		}
	}

	static int n;
	static boolean[][] cuales;
	static int[] cuentas;
	

	static BigInteger normaTotal;
	
	static int[] pasarArreglo(char[] val)
	{
		int[] arreglo = new int[n + 1];
		arreglo[0] = 0;
		for(int i = 0; i < n; i++)
		{
			int valAct = val[i] - 'a';
			int posReal = 0;
			for(int j = 0; j < 26; j++)
				if(j == valAct)
					break;
				else if(cuales[i][j])
					posReal++;
			arreglo[i + 1] = posReal;
		}
		return arreglo;
	}
	
	static char[] pasarString(int[] val)
	{
		char[] arreglo = new char[n];
		arreglo[0] = 0;
		for(int i = 0; i < n; i++)
		{
			int t = val[i + 1];
			int posReal = 0;
			for(int j = 0; j < 26; j++)
				if(t == 0 && cuales[i][j])
				{
					posReal = j;
					break;
				}
				else if(cuales[i][j])
					t--;
			arreglo[i] = (char) (posReal + 'a');
		}
		return arreglo;
	}
	
	static int[] suma(int[] a, int[] b)
	{
		int[] resultado = new int[a.length];
		int carry = 0;
		for(int i = a.length - 1; i >= 1; i--)
		{
			int suma = a[i] + b[i] + carry;
			if(suma >= cuentas[i - 1])
			{
				suma -= cuentas[i - 1];
				carry = 1;
			}
			else
				carry = 0;
			resultado[i] = suma;
		}
		resultado[0] = carry;
		return resultado;
	}
	
	static int[] resta(int[] a, int[] b)
	{
		int[] resultado = new int[a.length];
		int borrow = 0;
		for(int i = a.length - 1; i >= 0; i--)
		{
			int suma = a[i] - b[i] - borrow;
			if(suma < 0)
			{
				suma += cuentas[i - 1];
				borrow = 1;
			}
			else
				borrow = 0;
			resultado[i] = suma;
		}
		return resultado;
	}
	
	static char[] calcularInverso(BigInteger cuenta)
	{
		BigInteger norma = normaTotal;
		char[] result = new char[n];
		for(int i = 0; i < n; i++)
		{
			norma = norma.divide(BigInteger.valueOf(cuentas[i]));
			BigInteger[] divR = cuenta.divideAndRemainder(norma);
			int t = divR[0].intValue();
			int posReal = 0;
			for(int j = 0; j < 26; j++)
				if(t == 0 && cuales[i][j])
				{
					posReal = j;
					break;
				}
				else if(cuales[i][j])
					t--;
			result[i] = (char) (posReal + 'a');
			cuenta = divR[1];
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		int k = sc.nextInt();
		cuales = new boolean[n][26];
		cuentas = new int[n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < 26; j++)
				cuales[i][j] = true;
		for(int i = 0; i < k; i++)
		{
			char[] val = sc.next().toCharArray();
			for(int j = 0; j < n; j++)
				cuales[j][val[j] - 'a'] = false;
		}
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < 26; j++)
				if(cuales[i][j])
					cuentas[i]++;
		}
		char[] a = sc.next().toCharArray();
		char[] b = sc.next().toCharArray();
		sc.println(new String(pasarString(suma(pasarArreglo(a), pasarArreglo(b)))));
		sc.out.flush();
	}
}
