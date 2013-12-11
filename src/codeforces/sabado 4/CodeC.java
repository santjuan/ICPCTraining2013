import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
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
		
		public Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
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
	
	static class Arista implements Comparable <Arista>
	{
		double costo;
		Punto siguiente;

		public Arista(Punto b, double dist) 
		{
			siguiente = b;
			costo = dist;
		}

		@Override
		public int compareTo(Arista o) 
		{
			return Double.valueOf(costo).compareTo(o.costo);
		}
	}
	
	static int idC = 0;
	
	static class Punto
	{
		final double x;
	    final double y;
		final double z;
		ArrayList <Arista> aristas = new ArrayList <Arista> ();
		double mejor = Double.POSITIVE_INFINITY;
		
		Punto(double xx, double yy, double zz)
		{
			x = xx;
			y = yy;
			z = zz;
		}
		
		Punto resta(Punto otro)
		{
			return new Punto(x - otro.x, y - otro.y, z - otro.z);
		}
		
		double norma()
		{
			return Math.sqrt(x * x + y * y + z * z);
		}
		
		double productoPunto(Punto p)
		{
			return x * p.x + y * p.y + z * p.z;
		}
		
		double acos(double x)
		{
			if(Math.abs(x) > 1)
				return Math.acos(1 * Math.signum(x));
			return Math.acos(x);
		}
		
		double angleWith(Punto p)
		{
			return acos(productoPunto(p) / norma() / p.norma()) * 180 / Math.PI;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = (int) (prime * result + x);
			result = (int) (prime * result + y);
			result = (int) (prime * result + z);
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			Punto other = (Punto) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			if (z != other.z)
				return false;
			return true;
		}
	}
	
	static class Linea
	{
		Punto a;
		Punto b;
		
		public Linea(Punto aa, Punto bb) 
		{
			a = aa;
			b = bb;
		}
	}

	static Linea[] lineas;
	
	static HashMap <Punto, ArrayList <Linea> > lineasPunto;

	
	static double distanciaAngular(Linea a, Linea b, int cualA, int cualB, int vel)
	{
		Punto first = (cualA == 0 ? a.a : a.b).resta(cualA == 1 ? a.a : a.b);
		Punto second = (cualB == 1 ? b.a : b.b).resta(cualB == 0 ? b.a : b.b);
		return second.angleWith(first) / vel;
	}
	

	private static double dijkstra(Punto pInicial, Punto pFinal, double s)
	{
		if(pInicial.equals(pFinal))
			return 0.0d;
		PriorityQueue <Arista> pq = new PriorityQueue <Arista> ();
		for(Linea p : lineasPunto.get(pInicial))
		{
			if(p.b.equals(pInicial))
			{
				p.a.mejor = p.a.resta(p.b).norma() / s;
				pq.add(new Arista(p.a, p.a.mejor));
			}
			else
			{
				p.b.mejor = p.a.resta(p.b).norma() / s;
				pq.add(new Arista(p.b, p.b.mejor));
			}
		}
		while(!pq.isEmpty())
		{
			Arista actual = pq.poll();
			if(actual.costo > actual.siguiente.mejor)
				continue;
			if(actual.siguiente.equals(pFinal))
				return actual.costo;
			for(Arista a : actual.siguiente.aristas)
			{
				double siguienteCosto = actual.costo + a.costo;
				if(a.siguiente.mejor > siguienteCosto)
				{
					a.siguiente.mejor = siguienteCosto;
					pq.add(new Arista(a.siguiente, siguienteCosto));
				}
			}
		}
		return Double.MAX_VALUE;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			Integer tmp = sc.nextInteger();
			if(tmp == null)
				return;
			int n = tmp;
			int s = sc.nextInt();
			int t = sc.nextInt();
			Punto pInicial = new Punto(sc.nextInt(), sc.nextInt(), sc.nextInt());
			Punto pFinal = new Punto(sc.nextInt(), sc.nextInt(), sc.nextInt());
			lineas = new Linea[n];
			for(int i = 0; i < n; i++)
				lineas[i] = new Linea(new Punto(sc.nextInt(), sc.nextInt(), sc.nextInt()), new Punto(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			lineasPunto = new HashMap <Punto, ArrayList <Linea> > ();
			for(int i = 0; i < n; i++)
			{
				if(!lineasPunto.containsKey(lineas[i].a))
					lineasPunto.put(lineas[i].a, new ArrayList <Linea> ());
				lineasPunto.get(lineas[i].a).add(lineas[i]);
				if(!lineasPunto.containsKey(lineas[i].b))
					lineasPunto.put(lineas[i].b, new ArrayList <Linea> ());
				lineasPunto.get(lineas[i].b).add(lineas[i]);
			}
			for(int i = 0; i < n; i++)
			{
				Linea actual = lineas[i];
				for(Linea l : lineasPunto.get(actual.a))
				{
					if(l == actual)
						continue;
					Punto x = actual.a;
					Punto xO = actual.b;
					Punto y = l.a.equals(x) ? l.a : l.b;
					Punto yO = l.a.equals(x) ? l.b : l.a;
					double dist = distanciaAngular(actual, l, 0, y == l.a ? 0 : 1, t);
					x.aristas.add(new Arista(yO, dist + y.resta(yO).norma() / s));
				}
				for(Linea l : lineasPunto.get(actual.b))
				{
					if(l == actual)
						continue;
					Punto x = actual.b;
					Punto xO = actual.a;
					Punto y = l.a.equals(x) ? l.a : l.b;
					Punto yO = l.a.equals(x) ? l.b : l.a;
					double dist = distanciaAngular(actual, l, 1, y == l.a ? 0 : 1, t);
					x.aristas.add(new Arista(yO, dist + (y.resta(yO).norma() / s)));
				}
			}
			System.out.println(String.format("%.4f", dijkstra(pInicial, pFinal, s)).replace(",", "."));
		}
	}
}
