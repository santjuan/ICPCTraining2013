import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;


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
	
	
	static String[] formas = new String[]{"012345", "102354", "542301", "452310", "325401", "234501"};
	
	static String rotar(String a)
	{
		char[] val = a.toCharArray();
		char tmp = val[2];
		val[2] = val[3];
		val[3] = tmp;
		tmp = val[4];
		val[4] = val[5];
		val[5] = tmp;
		return new String(val);
	}
	

	private static String rotarUno(String a) 
	{
		char[] val = a.toCharArray();
		char[] valSig = new char[6];
		valSig[0] = val[0];
		valSig[1] = val[1];
		valSig[2] = val[4];
		valSig[3] = val[5];
		valSig[4] = val[3];
		valSig[5] = val[2];
		return new String(valSig);
	}
	
	static ArrayList <String> todas = new ArrayList <String> ();
	static TreeSet <String> encontradas = new TreeSet <String> ();
	static int cuenta;
	
	static ArrayList <String> generarRotaciones(String a)
	{
		ArrayList <String> salida = new ArrayList <String> ();
		for(String rotacion : todas)
		{
			String res = "";
			for(char c : rotacion.toCharArray())
				res += a.charAt(c - '0');
			salida.add(res);
		}
		return salida;
	}
	
	static void permutar(char[] actual, char[] deDonde, int n, boolean[] usados)
	{
		if(n == actual.length)
		{
			if(encontradas.contains(new String(actual)))
					return;
			else
			{
				cuenta++;
				encontradas.addAll(generarRotaciones(new String(actual)));
			}
		}
		else
		{
			for(int i = 0; i < deDonde.length; i++)
			{
				if(!usados[i])
				{
					usados[i] = true;
					actual[n] = deDonde[i];
					permutar(actual, deDonde, n + 1, usados);
					usados[i] = false;
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		for(String s : formas)
		{
			todas.add(s);
			todas.add(rotar(s));
			todas.add(rotarUno(s));
			todas.add(rotarUno(rotar(s)));
			todas.add(rotar(rotarUno(s)));
			todas.add(rotarUno(rotar(rotarUno(s))));
		}
		permutar(new char[6], sc.next().toCharArray(), 0, new boolean[6]);
		System.out.println(cuenta);
	}
}