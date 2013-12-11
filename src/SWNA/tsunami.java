import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class tsunami
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
	}
	
	static class Entrada implements Comparable <Entrada>
	{
		Nodo n;
		double prioridad;
		
		public Entrada(Nodo inicial, double i) 
		{
			n = inicial;
			prioridad = i;
		}

		@Override
		public int compareTo(Entrada o) 
		{
			return Double.valueOf(prioridad).compareTo(o.prioridad);
		}
	}
	
	static class Nodo
	{
		int id;
		int x;
		int y;
		double mejor = Integer.MAX_VALUE;
		boolean visitado = false;
		LinkedList <Nodo> vecinos = new LinkedList <Nodo> ();
		
		public Nodo(int xx, int yy) 
		{
			x = xx;
			y = yy;
		}

		void generarVecinos(Nodo[] todos)
		{
			for(Nodo n : todos)
			{
				if(n == this)
					continue;
				if(n.y >= y)
					vecinos.add(n);	
			}
		}

		public double distancia(Nodo vecino) {
			int distanciaX = x - vecino.x;
			int distanciaY = y - vecino.y;
			return Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
		}
	}
	
	
	static double prim(Nodo[] nodos)
	{
		int menorY = Integer.MAX_VALUE;
		for(Nodo n : nodos)
			menorY = Math.min(n.y, menorY);
		Nodo inicial = null;
		for(Nodo n : nodos)
			if(n.y == menorY)
				inicial = n;
		inicial.mejor = 0;
		int cuentaVisitados = 0;
		int menorVisitado = inicial.y;
		double total = 0;
		while(cuentaVisitados < nodos.length)
		{
			Nodo actual = null;
			for(Nodo n : nodos)
				if(!n.visitado && n.y <= menorVisitado && (actual == null || actual.mejor > n.mejor))
					actual = n;
			if(actual == null)
			{
				Nodo menor = null;
				for(Nodo n : nodos)
				{
					if(!n.visitado && (menor == null || menor.y > n.y))
						menor = n;
				}
				menorVisitado = menor.y;
				continue;
			}
			total += actual.mejor;
			actual.visitado = true;
			cuentaVisitados++;
			for(Nodo vecino : actual.vecinos)
			{
				if(vecino.visitado)
					continue;
				double distancia = actual.distancia(vecino);
				if(distancia < vecino.mejor)
					vecino.mejor = distancia;
			}
		}
		return total;
	}
	
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				return;
			Nodo[] nodos = new Nodo[n];
			for(int i = 0; i < n; i++)
				nodos[i] = new Nodo(sc.nextInt(), sc.nextInt());
			for(int i = 0; i < n; i++)
				nodos[i].generarVecinos(nodos);
			String val = String.format("%.2f", prim(nodos));
			System.out.println(val.replace(",", "."));
		}
	}
}