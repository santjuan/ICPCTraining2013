import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;


public class E 
{
	
	static boolean debug = false;
	
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st = new StringTokenizer("");
		
		Scanner()
		{
			if(debug)
				br = new BufferedReader(new InputStreamReader(System.in));
			else
			{
				try 
				{
					br = new BufferedReader(new FileReader("frustum.in"));
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
	}
	
	static double L, r;
	
	static double cuadrado(double x)
	{
		return x * x;
	}
	
	static double cubo(double x)
	{
		return x * x * x;
	}
	
	static double funcion(double R)
	{
		double h = Math.sqrt((cuadrado(L) - cuadrado(R - r)) / cuadrado((R / r) - 1));
		double vol = (Math.PI / 3) * h * ((cubo(R) - cubo(r)) / r);
		return vol;
	}
	
	static double busquedaTerciaria(double i, double j)
	{
		if(Math.abs(j - i) < 1e-12)
			return funcion(i);
		double primera = i + (j - i) / 3;
		double segunda = i + (2 * (j - i)) / 3;
		double fPrimera = funcion(primera);
		double fSegunda = funcion(segunda);
		if(fPrimera < fSegunda)
			return busquedaTerciaria(primera, j);
		else
			return busquedaTerciaria(i, segunda);
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		@SuppressWarnings("resource")
		PrintStream out = debug ? System.out : new PrintStream(new BufferedOutputStream(new FileOutputStream("frustum.out")));
		Scanner sc = new Scanner();
		L = sc.nextInt();
		double d = sc.nextInt();
		r = d / 2;
		out.println(busquedaTerciaria(r, r + L));
		out.flush();
	}

}