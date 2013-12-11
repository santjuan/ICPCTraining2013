import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class D
{
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st = new StringTokenizer("");
		
		Scanner()
		{
//			br = new BufferedReader(new InputStreamReader(System.in));
			try 
			{
				br = new BufferedReader(new FileReader("compression.in"));
			}
			catch (FileNotFoundException e) 
			{
				throw(new RuntimeException());
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
	}
	
	static class Respuesta
	{
		long p;
		int indicePalabra;
		int tamPrefijo;
		Respuesta siguiente;
		
		Respuesta(long pp, int pal, int tam)
		{
			p = pp;
			indicePalabra = pal;
			tamPrefijo = tam;
		}
	}
	
	static String[] palabras;
	static Respuesta[] dp;
	
	static Respuesta dp(int n)
	{
		if(dp[n] != null)
			return dp[n];
		if(n == palabras.length)
			return dp[n] = new Respuesta(0, n, 0);
		String estaPalabra = palabras[n];
		Respuesta mejor = new Respuesta(Long.MAX_VALUE, n, 0);
		for(int i = 1; i <= estaPalabra.length(); i++)
		{
			Respuesta siguiente = dp(busquedaBinaria(n, palabras.length - 1, estaPalabra.substring(0, i)) + 1);
			Respuesta esta = new Respuesta((1L << (40 - i)) + siguiente.p, n, i);
			esta.siguiente = siguiente;
			if(esta.p <= mejor.p)
				mejor = esta;
		}
		return dp[n] = mejor;
	}
	
	static int busquedaBinaria(int i, int j, String prefijo)
	{
		if(i == j)
			return i;
		if(i + 1 == j)
			return palabras[j].startsWith(prefijo) ? j : i;
		int mid = (i + j) >>> 1;
		if(palabras[mid].startsWith(prefijo))
			return busquedaBinaria(mid, j, prefijo);
		else
			return busquedaBinaria(i, mid - 1, prefijo);
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		// PrintStream out = System.out;
		@SuppressWarnings("resource")
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("compression.out")));
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		ArrayList <String> palabrasT = new ArrayList <String> ();
		dp = new Respuesta[n + 1];
		for(int i = 0; i < n; i++)
			palabrasT.add(sc.next());
		Collections.sort(palabrasT);
		palabras = new String[n];
		for(int i = 0; i < n; i++)
			palabras[i] = palabrasT.get(i);
		Respuesta mejor = dp(0);
		ArrayList <String> todas = new ArrayList <String> ();
		out.println(mejor.p);
		while(mejor.indicePalabra != n)
		{
			todas.add(palabras[mejor.indicePalabra].substring(0, mejor.tamPrefijo));
			mejor = mejor.siguiente;
		}
		out.println(todas.size());
		for(String s : todas)
			out.println(s);
		out.flush();
	}
}