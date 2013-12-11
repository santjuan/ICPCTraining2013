import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	
	static class Estado
	{
		long tiempo;
		long tiempoOn;
		boolean prendido;
		
		Estado(long t, long tO, boolean p)
		{
			tiempo = t;
			tiempoOn = tO;
			prendido = p;
		}
	}
	
	static interface PatronBase
	{
		Estado simular(boolean prendido);
		Estado simular(boolean prendido, long restante);
	}
	
	static class Numero implements PatronBase
	{
		int tiempoMillis;
		
		@Override
		public Estado simular(boolean prendido)
		{
			return new Estado(tiempoMillis, prendido ? tiempoMillis : 0, !prendido);
		}

		@Override
		public Estado simular(boolean prendido, long restante)
		{
			return new Estado(restante, prendido ? restante : 0, !prendido);
		}
		
	}
	
	static class Patron implements PatronBase
	{
		PatronBase[] internos;
		Estado dpOn;
		Estado dpOff;
		int veces;
		
		@Override
		public Estado simular(boolean prendido) 
		{
			if(prendido && dpOn != null)
				return dpOn;
			else if(!prendido && dpOff != null)
				return dpOff;
			Estado actual = new Estado(0, 0, prendido);
			for(int i = 0; i < veces; i++)
				for(PatronBase p : internos)
				{
					Estado interno = p.simular(actual.prendido);
					actual.prendido = interno.prendido;
					actual.tiempo += interno.tiempo;
					actual.tiempoOn += interno.tiempoOn;
				}
			return prendido ? (dpOn = actual) : (dpOff = actual);
		}

		@Override
		public Estado simular(boolean prendido, long restante) 
		{
			Estado actual = new Estado(0, 0, prendido);
			for(int i = 0; i < veces; i++)
				for(PatronBase p : internos)
				{
					Estado interno = p.simular(actual.prendido);
					if(interno.tiempoOn + actual.tiempoOn >= restante)
					{
						interno = p.simular(actual.prendido, restante - actual.tiempoOn);
						actual.prendido = interno.prendido;
						actual.tiempo += interno.tiempo;
						actual.tiempoOn += interno.tiempoOn;
						return actual;
					}
					else
					{
						actual.prendido = interno.prendido;
						actual.tiempo += interno.tiempo;
						actual.tiempoOn += interno.tiempoOn;
					}
				}
			return null;
		}
	}
	
	static PatronBase leer(LinkedList <Character> actual)
	{
		while(actual.peek() == ' ')
			actual.poll();
		if(actual.peek() >= '0' && actual.peek() <= '9')
		{
			StringBuilder sb = new StringBuilder();
			while(actual.peek() >= '0' && actual.peek() <= '9')
				sb.append(actual.poll());
			Numero n = new Numero();
			n.tiempoMillis = Integer.parseInt(sb.toString());
			return n;
		}
		else
		{
			actual.poll();
			while(actual.peek() == ' ')
				actual.poll();
			ArrayList <PatronBase> internos = new ArrayList <PatronBase> ();
			while(actual.peek() != ')')
			{
				internos.add(leer(actual));
				while(actual.peek() == ' ')
					actual.poll();
			}
			actual.poll();
			while(actual.peek() == ' ')
				actual.poll();
			actual.poll();
			StringBuilder sb = new StringBuilder();
			while(!actual.isEmpty() && actual.peek() >= '0' && actual.peek() <= '9')
				sb.append(actual.poll());
			Patron p = new Patron();
			p.internos = internos.toArray(new PatronBase[0]);
			p.veces = Integer.parseInt(sb.toString());
			if(p.veces == Integer.MAX_VALUE)
				p.veces = 1;
			return p;
		}
	}
	
	static long simularPrimero(PatronBase primero, long time)
	{
		Estado primeroE = primero.simular(true);
		if(primeroE.tiempoOn >= time)
		{
			Estado resultado = primero.simular(true, time);
			return resultado.tiempo;
		}
		else
		{
			Estado segundoE = primero.simular(primeroE.prendido);
			if(primeroE.tiempoOn + segundoE.tiempoOn >= time)
			{
				segundoE = primero.simular(primeroE.prendido, time - primeroE.tiempoOn);
				return primeroE.tiempo + segundoE.tiempo;
			}
			else
			{
				long veces = time / (primeroE.tiempoOn + segundoE.tiempoOn);
				long restante;
				if((time % (primeroE.tiempoOn + segundoE.tiempoOn)) == 0)
				{
					restante = simularPrimero(primero, primeroE.tiempoOn + segundoE.tiempoOn);
					veces--;
				}
				else
					restante = simularPrimero(primero, time % (primeroE.tiempoOn + segundoE.tiempoOn));
				return (primeroE.tiempo + segundoE.tiempo) * veces + restante;
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int time = sc.nextInt();
			if(time == 0)
				return;
			String txt = "(" + sc.nextLine() + ")*" + Integer.MAX_VALUE;
			LinkedList <Character> actual = new LinkedList <Character> ();
			for(char c : txt.toCharArray())
				actual.add(c);
			PatronBase primero = leer(actual);
			System.out.println(simularPrimero(primero, time));
		}
	}
}