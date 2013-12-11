import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

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

		public String[] nextStringArray(int n) 
		{	
			String[] vals = new String[n];
			for(int i = 0; i < n; i++)
				vals[i] = next();
			return vals;
		}
	}
	
	static class Pair implements Comparable <Pair>
	{
		int a;
		int b;
		int tam;
		public Pair(int aa, int bb)
		{
			a = aa;
			b = bb;
			tam = b - a;
		}
		@Override
		public int compareTo(Pair o)
		{
			if(tam == o.tam)
				return a - o.a;
			return tam - o.tam;
		}
	}
	
	static boolean leq(int a1, int a2,   int b1, int b2) 
	{
		return(a1 < b1 || a1 == b1 && a2 <= b2);
	}             

	static boolean leq(int a1, int a2, int a3,   int b1, int b2, int b3) 
	{
		return(a1 < b1 || a1 == b1 && leq(a2,a3, b2,b3));
	}

	static int[] c;

	static void radixPass(int[] a, int[] b, int[] r, int n, int K, int delta)
	{
		if(c == null || c.length != K + 1) c = new int[K + 1];
		for(int i = 0;  i <= K; i++) c[i] = 0;
		for(int i = 0;  i < n;  i++) c[r[a[i] + delta]]++;
		for(int i = 0, sum = 0;  i <= K;  i++) 
		{
			int t = c[i];
			c[i] = sum;
			sum += t;
		}
		for (int i = 0;  i < n;  i++) b[c[r[a[i] + delta]]++] = a[i];
	}

	// find the suffix array SA of s[0..n-1] in {1..K}^n
	// require s[n]=s[n+1]=s[n+2]=0, n>=2
	static void suffixArray(int[] s, int[] SA, int n, int K) 
	{
		int n0=(n+2)/3, n1=(n+1)/3, n2=n/3, n02=n0+n2;
		int[] s12  = new int[n02 + 3];  s12[n02] = s12[n02+1] = s12[n02+2] = 0;
		int[] SA12 = new int[n02 + 3]; SA12[n02]=SA12[n02+1]=SA12[n02+2]=0;
		int[] s0   = new int[n0];
		int[] SA0  = new int[n0];
		for (int i=0, j=0; i < n + (n0 - n1); i++) if (i % 3 != 0) s12[j++] = i;
		radixPass(s12, SA12, s, n02, K, 2);
		radixPass(SA12, s12 , s, n02, K, 1);
		radixPass(s12, SA12, s, n02, K, 0);
		int name = 0, c0 = -1, c1 = -1, c2 = -1;
		for(int i = 0; i < n02; i++) 
		{
			if(s[SA12[i]] != c0 || s[SA12[i] + 1] != c1 || s[SA12[i] + 2] != c2) 
			{
				name++;
				c0 = s[SA12[i]];
				c1 = s[SA12[i] + 1];
				c2 = s[SA12[i] + 2];
			}
			if(SA12[i] % 3 == 1) s12[SA12[i] / 3] = name;
			else s12[SA12[i] / 3 + n0] = name;
		}
		if (name < n02) 
		{
			suffixArray(s12, SA12, n02, name);
			for (int i = 0; i < n02; i++) s12[SA12[i]] = i + 1;
		} 
		else for(int i = 0; i < n02; i++) SA12[s12[i] - 1] = i;
		for (int i = 0, j = 0; i < n02; i++) if (SA12[i] < n0) s0[j++] = 3 * SA12[i];
		radixPass(s0, SA0, s, n0, K, 0);
		for(int p = 0,  t = n0 - n1,  k = 0;  k < n;  k++) 
		{
			int i = (SA12[t] < n0 ? SA12[t] * 3 + 1 : (SA12[t] - n0) * 3 + 2);
			int j = SA0[p];
			if (SA12[t] < n0 ? leq(s[i], s12[SA12[t] + n0], s[j], s12[j/3]) :
				leq(s[i],s[i+1],s12[SA12[t]-n0+1], s[j],s[j+1],s12[j/3+n0]))
			{
				SA[k] = i;
				t++;
				if (t == n02) for(k++; p < n0; p++, k++) SA[k] = SA0[p]; 
			} 
			else 
			{
				SA[k] = j;
				p++;
				if(p == n0) for(k++; t < n02; t++, k++) SA[k] = (SA12[t] < n0 ? SA12[t] * 3 + 1 : (SA12[t] - n0) * 3 + 2); 
			}
		}
	}
	
	static class SuffixArray
	{
		int n;
		int[] g, b, sa, lcp, rmq, aux;
		int[] t;

		SuffixArray(int[] entrada)
		{
			int tamMax = entrada.length;
			g = new int[tamMax];
			b = new int[tamMax];
			sa = new int[tamMax];
			lcp = new int[tamMax];
			aux = new int[tamMax + 3];
			int logn = 1;
			int n = tamMax;
			buildSA(entrada);
		}

		void buildSA(int[] sas)
		{
			n = sas.length;
			t = sas.clone();
			for(int i = 0; i < n; i++)
				aux[i] = t[i];
			aux[n] = aux[n + 1] = aux[n + 2] = 0;
			suffixArray(aux, sa, n, 100010);
			if(n == 1)
				sa[0] = 0;
			if(n != 1)
				buildLCP();
			else
				lcp = new int[1];
		}

		void buildLCP()
		{
			int[] a = sa;
			int h = 0;
			for(int i = 0; i < n; i++) b[a[i]] = i;
			for(int i = 0; i < n; i++) 
			{
				if (b[i] != 0)
				{
					for(int j = a[b[i] - 1]; j + h < n && i + h < n && t[j + h] == t[i + h]; ++h);
					lcp[b[i]] = h;
				} 
				else lcp[b[i]] = -1;
				if(h > 0) --h;
			}
		}
		
		ArrayList <Pair> darRepeats()
		{
			int actual = 0;
			ArrayList <Pair> respuestas = new ArrayList <Pair> ();
			while(actual < n)
			{
				int fin = actual;
				while(fin < n && t[sa[fin]] == t[sa[actual]])
					fin++;
				for(int este = actual; este < fin; este++)
				{
					int min = Integer.MAX_VALUE;
					for(int otro = este + 1; otro < fin; otro++)
					{
						min = Math.min(min, lcp[otro]);
						if(min >= Math.abs(sa[este] - sa[otro]))
							respuestas.add(new Pair(Math.min(sa[este], sa[otro]), Math.max(sa[este], sa[otro])));
					}
				}
				actual = fin;
			}
			return respuestas;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[] entrada = sc.nextIntArray(n);
		TreeSet <Integer> valores = new TreeSet <Integer> ();
		TreeMap <Integer, Integer> mapeo = new TreeMap <Integer, Integer> ();
		for(int i : entrada)
			valores.add(i);
		int tamAlfabeto = 1;
		for(int i : valores)
			mapeo.put(i, tamAlfabeto++);
		int[] entradaMapeada = new int[entrada.length];
		for(int i = 0; i < entradaMapeada.length; i++)
			entradaMapeada[i] = mapeo.get(entrada[i]);
		SuffixArray suffix = new SuffixArray(entradaMapeada);
		ArrayList <Pair> repeats = suffix.darRepeats();
		Collections.sort(repeats);
		int leftMost = 0;
		for(Pair p : repeats)
		{
			if(p.a >= leftMost)
				leftMost = p.b;
		}
		System.out.println(entrada.length - leftMost);
		StringBuilder sb = new StringBuilder();
		for(int i = leftMost; i < entrada.length; i++)
			sb.append(entrada[i]).append(' ');
		System.out.println(sb.toString());
	}

}
