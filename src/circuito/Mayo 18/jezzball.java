import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class jezzball 
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
	
	static class Muro
	{
		int id;
		double val;
		boolean enX;
		
		public Muro(int i, int v, boolean x) 
		{
			id = i;
			val = v;
			enX = x;
		}
	}

	static double EPS = 1e-10;
	static Muro[] muros = new Muro[]{new Muro(0, 0, true), new Muro(1, 1024, true), new Muro(2, 0, false), new Muro(3, 768, false), null};
	
	static double arreglar(double val, double limite)
	{
		if(val < 0 && val >= -EPS)
			return 0;
		if(val > limite && val <= limite + EPS)
			return limite;
		return val;
	}
	
	static class Particula implements Cloneable
	{
		double x;
		double y;
		double vx;
		double vy;
		double t;
		int muro = 5;
		
		Particula clonar()
		{
			try 
			{
				Particula nueva = (Particula) clone();
				return nueva;
			} 
			catch (CloneNotSupportedException e)
			{
				return null;
			}
		}
		
		void simular(double tiempo)
		{
			x += vx * tiempo;
			y += vy * tiempo;
			t += tiempo;
		}
		
		boolean esValida()
		{
			x = arreglar(x, 1024);
			y = arreglar(y, 768);
			return x >= 0 && x <= 1024 && y >= 0 && y <= 768;
		}
		
		
		Particula darSiguiente(Muro muro)
		{
			if(muro.enX)
			{
				double t = (muro.val - x) / vx;
				if(t < 0 || t > 10000000)
					return null;
				Particula siguiente = clonar();
				siguiente.muro = muro.id;
				siguiente.simular(t);
				if(muro.id != 4)
					siguiente.vx = -siguiente.vx;
				if(siguiente.esValida())
					return siguiente;
			}
			else
			{
				double t = (muro.val - y) / vy;
				if(t < 0 || t > 10000000)
					return null;
				Particula siguiente = clonar();
				siguiente.muro = muro.id;
				siguiente.simular(t);
				if(muro.id != 4)
					siguiente.vy = -siguiente.vy;
				if(siguiente.esValida())
					return siguiente;
			}
			return null;
		}
		
		Particula darSiguiente()
		{
			double menorTiempo = Double.POSITIVE_INFINITY;
			Particula mejorParticula = null;
			for(Muro m : muros)
			{
				if(m.id == muro)
					continue;
				Particula posible = darSiguiente(m);
				if(posible != null)
				{
					if(posible.t < menorTiempo || (posible.t == menorTiempo && m.id == 4))
					{
						if(t == posible.t && m.id == 4)
							continue;
						menorTiempo = posible.t;
						mejorParticula = posible;
					}
				}
			}
			return mejorParticula;
		}
	}
	
	static Particula[] clonar(Particula[] p)
	{
		Particula[] ans = new Particula[p.length];
		for(int i = 0; i < p.length; i++)
			ans[i] = p[i].clonar();
		return ans;
	}
	
	private static double darTiempoInicio(Particula escogida, int xM, int yM, boolean enX) 
	{
		if(enX)
		{
			double dist = Math.abs(escogida.y - yM);
			double t = escogida.t - dist / 200.0;
			return t;
		}
		else
		{
			double dist = Math.abs(escogida.x - xM);
			double t = escogida.t - dist / 200.0;
			return t;
		}
	}

	static double simularMejor(Particula[] particulas, int xM, int yM, boolean enX)
	{
		particulas = clonar(particulas);
		if(enX)
			muros[4] = new Muro(4, xM, true);
		else
			muros[4] = new Muro(4, yM, false);
		double tiempoCierre = Math.max((enX ? yM : xM), (enX ? (768 - yM) : (1024 - xM)));
		tiempoCierre /= 200.0;
		double tiempoActual = 0;
		while(tiempoActual <= 10000 + EPS)
		{
			double menor = Double.POSITIVE_INFINITY;
			int indiceMenor = -1;
			for(int i = 0; i < particulas.length; i++)
			{
				if(particulas[i].t < menor)
				{
					menor = particulas[i].t;
					indiceMenor = i;
				}
			}
			Particula escogida = particulas[indiceMenor];
			if(tiempoActual + tiempoCierre < escogida.t)
				return tiempoActual;
			if(escogida.muro == 4)
			{
				double tiempoInicio = darTiempoInicio(escogida, xM, yM, enX);
				if(tiempoInicio > tiempoActual)
					tiempoActual = tiempoInicio;
			}
			particulas[indiceMenor] = particulas[indiceMenor].darSiguiente();
		}
		return Double.POSITIVE_INFINITY;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				return;
			int xM = sc.nextInt();
			int yM = sc.nextInt();
			Particula[] particulas = new Particula[n];
			for(int i = 0; i < n; i++
					)
			{
				Particula nueva = new Particula();
				nueva.x = sc.nextDouble();
				nueva.y = sc.nextDouble(); 	
				nueva.vx = sc.nextDouble();
				nueva.vy = sc.nextDouble();
				particulas[i] = nueva;
			}
			double ans = Math.min(simularMejor(particulas, xM, yM, true), simularMejor(particulas, xM, yM, false));
			if(ans == Double.POSITIVE_INFINITY)
				System.out.println("Never");
			else
				System.out.println(String.format("%.5f", ans).replace(",", "."));
		}
	}
}
