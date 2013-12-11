import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class E
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
		char[][] tablero = new char[4][4];
		int estado;
		
		void unpack()
		{
			int vacios = estado >>> 16;
			int cuales = estado & 65535;
			int currentBit = 0;
			for(int i = 0; i < 4; i++)
				for(int j = 0; j < 4; j++)
				{
					if((vacios & (1 << currentBit)) != 0)
						tablero[i][j] = 2;
					else if((cuales & (1 << currentBit)) != 0)
						tablero[i][j] = 1;
					else
						tablero[i][j] = 0;
					currentBit++;
				}
		}
		
		int pack()
		{
			int vacios = 0;
			int cuales = 0;
			int currentBit = 0;
			for(int i = 0; i < 4; i++)
				for(int j = 0; j < 4; j++)
				{
					if(tablero[i][j] == 2)
						vacios |= (1 << currentBit);
					else if(tablero[i][j] == 1)
						cuales |= (1 << currentBit);
					currentBit++;
				}
			return cuales | (vacios << 16);
		}
		
		void gravity()
		{
			for(int columna = 0; columna < 4; columna++)
			{
				for(int fila = 2; fila >= 0; fila--)
				{
					int filaActual = fila;
					while(filaActual != 3)
					{
						if(tablero[filaActual][columna] != 2 && tablero[filaActual + 1][columna] == 2)
						{
							tablero[filaActual + 1][columna] = tablero[filaActual][columna];
							tablero[filaActual][columna] = 2;
							filaActual++;
						}
						else
							break;
					}
				}
			}
			estado = pack();
		}
		
		void flood_fill(int i, int j, boolean[][] visitados, char color)
		{
			if(i < 0 || i > 3 || j < 0 || j > 3 || visitados[i][j] || tablero[i][j] != color)
				return;
			visitados[i][j] = true;
			tablero[i][j] = 2;
			flood_fill(i + 1, j, visitados, color);
			flood_fill(i - 1, j, visitados, color);
			flood_fill(i, j + 1, visitados, color);
			flood_fill(i, j - 1, visitados, color);
		}
		
		boolean termino()
		{
			for(int i = 0; i < 4; i++)
				for(int j = 0; j < 4; j++)
					if(tablero[i][j] != 2)
						return false;
			return true;
		}
		
		Estado clonar()
		{
			Estado nuevo = new Estado();
			nuevo.estado = estado;
			nuevo.unpack();
			return nuevo;
		}
	}

	static HashMap <Integer, Integer> dp = new HashMap <Integer, Integer> ();
	
	static int dp(Estado e)
	{
		if(dp.containsKey(e.estado))
			return dp.get(e.estado);
		boolean[][] visitados = new boolean[4][4];
		int mejor = Integer.MIN_VALUE;
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
			{
				if(!visitados[i][j] && e.tablero[i][j] != 2)
				{
					Estado nuevo = e.clonar();
					nuevo.flood_fill(i, j, visitados, e.tablero[i][j]);
					nuevo.gravity();
					if(nuevo.termino())
					{
						dp.put(e.estado, 1);
						return 1;
					}
					int respuesta = dp(nuevo);
					respuesta = -(Integer.signum(respuesta) * (Math.abs(respuesta) + 1));
					if(respuesta > 0)
					{
						if(mejor < 0)
							mejor = respuesta;
						else
							mejor = Math.min(mejor, respuesta);
					}
					else if(respuesta < 0)
					{
						if(mejor > 0)
							continue;
						if(mejor == Integer.MIN_VALUE)
							mejor = respuesta;
						else
							mejor = Math.min(mejor, respuesta);
					}
				}
			}
		dp.put(e.estado, mejor);
		return mejor;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int casos = sc.nextInt();
		for(int caso = 1; caso <= casos; caso++)
		{
			char[][] tablero = new char[4][4];
			for(int i = 0; i < 4; i++)
			{
				char[] act = sc.next().toCharArray();
				for(int j = 0; j < 4; j++)
					tablero[i][j] = (char) (act[j] == 'B' ? 1 : 0);
			}
			Estado inicial = new Estado();
			inicial.tablero = tablero;
			inicial.estado = inicial.pack();
			int res = dp(inicial);
			System.out.println("Case " + caso + ": " + (res < 0 ? "loss " : "win ") + Math.abs(res));
		}
	}
}