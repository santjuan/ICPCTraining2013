import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CodeE 
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
		
		static <T> T fill(T arreglo, int val)
		{
			if(arreglo instanceof Object[])
			{
				Object[] a = (Object[]) arreglo;
				for(Object x : a)
					fill(x, val);
			}
			else if(arreglo instanceof int[])
				Arrays.fill((int[]) arreglo, val);
			else if(arreglo instanceof double[])
				Arrays.fill((double[]) arreglo, val);
			else if(arreglo instanceof long[])
				Arrays.fill((long[]) arreglo, val);
			return arreglo;
		}
		
		<T> T[] nextObjectArray(Class <T> clazz, int size)
		{
			@SuppressWarnings("unchecked")
			T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
			for(int c = 0; c < 3; c++)
			{
				Constructor <T> constructor;
				try 
				{
					if(c == 0)
						constructor = clazz.getDeclaredConstructor(Scanner.class, Integer.TYPE);
					else if(c == 1)
						constructor = clazz.getDeclaredConstructor(Scanner.class);
					else
						constructor = clazz.getDeclaredConstructor();
				} 
				catch(Exception e)
				{
					continue;
				}
				try
				{
					for(int i = 0; i < result.length; i++)
					{
						if(c == 0)
							result[i] = constructor.newInstance(this, i);
						else if(c == 1)
							result[i] = constructor.newInstance(this);
						else
							result[i] = constructor.newInstance();	
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
				return result;
			}
			throw new RuntimeException("Constructor not found");
		}
	}

	static String cut(String a, int n)
	{
		return a.substring(n).concat(a.substring(0, n));
	}
	
	static long mod = 1000000007;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		String a = sc.next();
		String b = sc.next();
		int k = sc.nextInt();
		HashMap <String, Integer> strings = new HashMap <String, Integer> ();
		for(int i = 0; i < a.length(); i++)
		{
			String cut = cut(a, i);
			if(!strings.containsKey(cut))
				strings.put(cut, 0);
			strings.put(cut, strings.get(cut) + 1);
		}
		ArrayList <String> cuts = new ArrayList <String> ();
		int[] veces = new int[strings.size()];
		int indice = 0;
		int total = 0;
		for(String s : strings.keySet())
		{
			cuts.add(s);
			veces[indice] = strings.get(s);
			total += veces[indice];
			indice++;
		}
		int indiceA = cuts.indexOf(a);
		int indiceB = cuts.indexOf(b);
		if(indiceB == -1)
		{
			System.out.println(0);
			return;
		}
//		int[] actual = new int[veces.length];
//		actual[indiceA] = 1;
//		long sumaAnteriores = 1;
//		for(int i = 0; i < k; i++)
//		{
////			for(int j = 0; j < actual.length; j++)
////			{
////				for(int l = 0; l < actual.length; l++)
////				{
////					if(j == l)
////						siguiente[l] += ((veces[l] - 1) * ((long) actual[j])) % mod;
////					else
////						siguiente[l] += (veces[l] * ((long) actual[j])) % mod;
////					siguiente[l] %= mod;
////				}
////			}
//			long sumaSiguiente = 0;
//			for(int j = 0; j < actual.length; j++)
//			{
//				long respuesta = ((sumaAnteriores * veces[j]) + (mod - actual[j])) % mod;
//				sumaSiguiente += respuesta;
//				actual[j] = (int) respuesta;
//			}
//			sumaSiguiente %= mod;
//			sumaAnteriores = sumaSiguiente;
//		}
//		System.out.println(actual[indiceB]);
		total -= veces[indiceB];
		long[] dpF = new long[k + 1];
		long[] dpO = new long[k + 1];
		if(indiceA == indiceB)
		{
			dpF[0] = 1;
			dpO[0] = 0;
		}
		else
		{
			dpO[0] = 1;
			dpF[0] = 0;
		}
		for(int i = 1; i <= k; i++)
		{
			dpF[i] = dpF[i - 1] * (veces[indiceB] - 1) + dpO[i - 1] * veces[indiceB];
			dpF[i] %= mod;
			dpO[i] = dpF[i - 1] * total + dpO[i - 1] * (total - 1);
			dpO[i] %= mod;
		}
		System.out.println(dpF[k]);
	}
}