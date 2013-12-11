import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class flatland 
{
	static class Scanner
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String nextLine()
		{
			try
			{
				return br.readLine();
			}
			catch(Exception e)
			{
				throw(new RuntimeException(e));
			}
		}
		
		String next()
		{
			while(!st.hasMoreTokens())
			{
				String line = nextLine();
				if(line == null)
					return null;
				st = new StringTokenizer(line);
			}
			return st.nextToken();
		}
		
		int nextInt()
		{
			return Integer.parseInt(next());
		}
		
		long nextLong()
		{
			return Long.parseLong(next());
		}
		
		double nextDouble()
		{
			return Double.parseDouble(next());
		}
		
		int[] nextIntArray(int size)
		{
			int[] result = new int[size];
			for(int i = 0; i < size; i++)
				result[i] = nextInt();
			return result;
		}
		
		Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
	}
	
	static class Punto implements Comparable<Punto>
	{
		double x;
		double y;
		
		public Punto(double xx, double yy)
		{
			x = xx;
			y = yy;
		}
		
		Punto multiplicar(double escalar)
		{
			return new Punto(x * escalar, y * escalar);
		}
		
		Punto suma(Punto otro)
		{
			return new Punto(x + otro.x, y + otro.y);
		}
		
		Punto resta(Punto otro)
		{
			return new Punto(x - otro.x, y - otro.y);
		}
		
		double norma()
		{
			return Math.sqrt(x * x + y * y);
		}
		
		Punto normalizar()
		{
			return multiplicar(1 / norma());
		}
		
		boolean isInside()
		{
			return x >= -EPS && x <= B + EPS && y >= -EPS && y <= H + EPS;
		}
		
		@Override
		public int compareTo(Punto o) 
		{
			if(isEqual(x, o.x))
			{
				if(isEqual(y, o.y))
					return 0;
				return Double.valueOf(y).compareTo(o.y);
			}
			return Double.valueOf(x).compareTo(o.x);
		}

		public Punto division(Punto bP) {
			double[] a = new double[2];
		    double[] b = new double[2];
		    double[] c = new double[2];
		    a[0] = x;
		    a[1] = y;
		    b[0] = bP.x;
		    b[1] = bP.y;
		    boolean always=false;
		    double tmp;
		    for(int i = 0; i < 2; i++) {
		        if (Math.abs(b[i])<EPS) c[i] = Double.MAX_VALUE;
		        if (Math.abs(b[i])<EPS && Math.abs(a[i])<EPS) always=true;
		        else c[i]=a[i]/b[i];
		    }
		    if (always) {
		        c[0] = c[1] = Math.min(c[0],c[1]);
		    }
		    return new Punto(c[0], c[1]);
		}
	}
	
	static class Evento implements Comparable <Evento>
	{
		double t;
		Flatlander a;
		Flatlander b;
		
		public Evento(Flatlander a2, Flatlander b2, double t2) {
			a = a2;
			b = b2;
			t = t2;
		}

		@Override
		public int compareTo(Evento o) 
		{
			if(isEqual(t, o.t))
				return 0;
			return Double.valueOf(t).compareTo(o.t);
		}
	}
	
	static class Flatlander implements Comparable<Flatlander>
	{
		String nombre;
		Punto posicionInicial;
		Punto direccion;
		double tiempoChoque;
		double tiempoMuerte = Double.MAX_VALUE;

		@Override
		public int compareTo(Flatlander o) 
		{
			return nombre.compareTo(o.nombre);
		}
	}
	
	
	static Evento generarEvento(Flatlander a, Flatlander b)
	{
		double uA = a.direccion.x;
		double uB = b.direccion.x;
		double xA = a.posicionInicial.x;
		double xB = b.posicionInicial.x;
		Punto dV = a.direccion.resta(b.direccion);
		Punto dP = b.posicionInicial.resta(a.posicionInicial);
		Punto t = dP.division(dV);
		if (t.x>0 && t.x != Double.MAX_VALUE && Math.abs(t.x-t.y) < EPS) {
	        return new Evento(a, b, t.x);
		}
		return null;
	}
	
	static double B, H;
	static double EPS = 1e-6;
	
	static boolean isEqual(double a, double b)
	{
		return Math.abs(a - b) < EPS;
	}
	
	static String LDO;
	static double ldoTime;
	
	static void tryLDO(double t, Flatlander cual)
	{
		if(isEqual(t, ldoTime))
		{
			if(LDO == null || LDO.compareTo(cual.nombre) < 0)
				LDO = cual.nombre;
		}
		else if(t > ldoTime)
		{
			LDO = cual.nombre;
			ldoTime = t;
		}
	}
	
	static void simular(Flatlander[] flatLanders)
	{
		LinkedList <Evento> todosEventos = new LinkedList <Evento> ();
		for(int i = 0; i < flatLanders.length; i++)
		{
			for(int j = i + 1; j < flatLanders.length; j++)
			{
				Evento e = generarEvento(flatLanders[i], flatLanders[j]);
				if(e != null && e.t < Math.min(flatLanders[i].tiempoChoque, flatLanders[j].tiempoChoque))
					todosEventos.add(e);
			}
		}
		Collections.sort(todosEventos);
		while(!todosEventos.isEmpty())
		{
			Evento e = todosEventos.poll();
			if(e.a.tiempoMuerte != Double.MAX_VALUE || e.b.tiempoMuerte != Double.MAX_VALUE)
				continue;
			ArrayList <Evento> eventosSimultaneos = new ArrayList <Evento> ();
			for(Evento x : todosEventos)
			{
				if(x.compareTo(e) == 0)
					eventosSimultaneos.add(x);
			}
			for(Evento f : eventosSimultaneos)
			{
				if(f.a == e.a || f.a == e.b || f.b == e.a || f.b == e.b)
					f.a.tiempoMuerte = f.b.tiempoMuerte = e.a.tiempoMuerte = e.b.tiempoMuerte = e.t;
			}
			if(e.a.tiempoMuerte == Double.MAX_VALUE && e.b.tiempoMuerte == Double.MAX_VALUE)
			{
				Evento x = e;
				String tmp = x.a.nombre;
				x.a.nombre = x.b.nombre;
				x.b.nombre = tmp;
			}
		}
		LDO = null;
		ldoTime = Double.NEGATIVE_INFINITY;
		for(Flatlander f : flatLanders)
			tryLDO(Math.min(f.tiempoChoque, f.tiempoMuerte), f);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int NC = sc.nextInt();
		for(int caso = 0; caso < NC; caso++)
		{
			int n = sc.nextInt();
			B = sc.nextDouble();
			H = sc.nextDouble();
			Flatlander[] flatlanders = new Flatlander[n];
			for(int i = 0; i < n; i++)
			{
				Flatlander este = new Flatlander();
				este.posicionInicial = new Punto(sc.nextDouble(), sc.nextDouble());
				este.direccion = new Punto(sc.nextDouble(), sc.nextDouble());
				este.direccion = este.direccion.resta(este.posicionInicial);
				este.tiempoChoque = este.direccion.norma();
				este.direccion = este.direccion.normalizar();
				este.nombre = sc.next();
				flatlanders[i] = este;
			}
			simular(flatlanders);
			System.out.println(LDO);
		}
	}
}
