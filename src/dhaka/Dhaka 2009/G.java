import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class G 
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
	}
	
	static class point
	{
		double x;
		double y;
		boolean visible;
		
		public point(double x, double y) 
		{
			this.x = x;
			this.y = y;
		}
		
		point sub(point a){
			return new point(x - a.x,y - a.y);
		}
		point add(point a){
			return new point(x + a.x,y + a.y);
		}
		double cross(point a){
			return x*a.y - a.x*y;
		}
		double norm(){
			return Math.sqrt(x*x + y*y);
		}
		public point multbyscalar(double u) {
			return new point(u*x,u*y);
		}
		public point rotate(double alpha){
			double nx = x * Math.cos(alpha) - y * Math.sin(alpha);
			double ny = x * Math.sin(alpha) + y * Math.cos(alpha);
			return new point(nx,ny);
		}
	}
	
	static Double intersectionbtwlines(point p1,point p2,point p3,point p4)
	{
		point p2_p1=p2.sub(p1);
		point p4_p3=p4.sub(p3);
		double den=p2_p1.cross(p4_p3);
		if (Math.abs(den)<Evento.EPS)//the lines are parallel or coincident
			return null;
		point p1_p3=p1.sub(p3);
		double num=p4_p3.cross(p1_p3);
		double ua=num/den;
		num = p2_p1.cross(p1_p3);
		double ub=num/den;
		if(Evento.comparar(ua, 0) < 0 || Evento.comparar(1, ua) < 0)
			return null;
		if(Evento.comparar(ub, 0) < 0)
			return null;
		return ua;
	}
	
	static class Evento implements Comparable <Evento>
	{
		double tiempo;
		point punto;
		static double EPS = 1e-8;
		
		
		public Evento(double tiempo, point punto)
		{
			this.tiempo = tiempo;
			this.punto = punto;
		}

		static int comparar(double a, double b)
		{
			if(Math.abs(a - b) < EPS)
				return 0;
			return a < b ? -1 : 1;
		}
		
		@Override
		public int compareTo(Evento o) 
		{
			return comparar(tiempo, o.tiempo);
		}
	}
	
	static double H, W;
	static point centroCirculo;
	static double R;
	static point[] puntos;
	static point[] lineas;
	
	static boolean esVisible(point p, int linea)
	{
		point esta = lineas[linea];
		double dist = Line2D.ptLineDist(p.x, p.y, esta.x, esta.y, centroCirculo.x, centroCirculo.y);
		return Evento.comparar(dist, R) >= 0;
	}
	
	static ArrayList <Evento> generarEventos(point p, int linea)
	{
		point p1 = lineas[linea];
		point p2 = lineas[(linea + 1) % 4];
		point v = centroCirculo.sub(p);
		double alpha = Math.asin(R / v.norm());
		point v1 = v.rotate(alpha);
		point v2 = v.rotate(-alpha);
		Double primero = intersectionbtwlines(p1, p2, p, p.add(v1));
		Double segundo = intersectionbtwlines(p1, p2, p, p.add(v2));
		ArrayList <Evento> salida = new ArrayList <Evento> ();
		if(primero != null)
			salida.add(new Evento(primero, p));
		if(segundo != null)
			salida.add(new Evento(segundo, p));
		return salida;
	}
	
	static int simular(int linea)
	{
		int visibles = 0;
		int mejorVisibles = 0;
		LinkedList <Evento> eventos = new LinkedList <Evento> ();
		for(point p : puntos)
		{
			p.visible = esVisible(p, linea);
			visibles += p.visible ? 1 : 0;
			for(Evento e : generarEventos(p, linea))
				eventos.add(e);
		}
		Collections.sort(eventos);
		mejorVisibles = visibles;
		if(eventos.size() == 0)
			return visibles;
		ArrayList <Evento> actuales = new ArrayList <Evento> ();
		actuales.add(eventos.poll());
		while(!eventos.isEmpty())
		{
			mejorVisibles = Math.max(mejorVisibles, visibles);
			Evento nuevo = eventos.poll();
			if(actuales.size() == 0 || Evento.comparar(actuales.get(0).tiempo, nuevo.tiempo) != 0)
			{		
				Collections.sort(actuales, new Comparator <Evento>() 
				{
					@Override
					public int compare(Evento o1, Evento o2) 
					{
						if(!o1.punto.visible)
							return -1;
						else
							return 1;
					}
				});
				for(Evento e : actuales)
				{
					mejorVisibles = Math.max(mejorVisibles, visibles);
					if(e.punto.visible)
					{
						e.punto.visible = false;
						visibles--;
					}
					else
					{
						e.punto.visible = true;
						visibles++;
					}
				}
				actuales.clear();
				actuales.add(nuevo);
			}
			else
				actuales.add(nuevo);
		}
		Collections.sort(actuales, new Comparator<Evento>() 
		{
			@Override
			public int compare(Evento o1, Evento o2) 
			{
				if(!o1.punto.visible)
					return -1;
				else
					return 1;
			}
		});
		for(Evento e : actuales)
		{
			mejorVisibles = Math.max(mejorVisibles, visibles);
			if(e.punto.visible)
			{
				e.punto.visible = false;
				visibles--;
			}
			else
			{
				e.punto.visible = true;
				visibles++;
			}
		}
		return mejorVisibles;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int caso = 1;
		while(true)
		{
			H = sc.nextDouble();
			W = sc.nextDouble();
			R = sc.nextDouble();
			centroCirculo = new point(sc.nextDouble(), sc.nextDouble());
			int n = sc.nextInt();
			if(H == 0 && W == 0 && R == 0 && centroCirculo.x == 0 && centroCirculo.y == 0 && n == 0)
				return;
			lineas = new point[]{new point(0, 0), new point(0, H), new point(W, H), new point(W, 0)};
			puntos = new point[n];
			for(int i = 0; i < puntos.length; i++)
				puntos[i] = new point(sc.nextDouble(), sc.nextDouble());
			int mejor = 0;
			for(int i = 0; i < 4; i++)
				mejor = Math.max(mejor, simular(i));
			System.out.println("Case " + caso++ + ": " + mejor);
		}
	}
}