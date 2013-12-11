import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class twokings 
{
	static boolean adentro(int x, int y)
	{
		return x >= 0 && x < 100 && y >= 0 && y < 100;
	}
	
	Rey[][] dpRey = new Rey[101][101];
	
	Rey darRey(int x, int y)
	{
		if(dpRey[x][y] == null)
			dpRey[x][y] = new Rey(x, y);
		return dpRey[x][y];
	}
	
	class Posicion
	{
		int x;
		int y;
	}
	
	class Rey extends Posicion
	{
		Rey[] adjacentes;
		
		public Rey(int xx, int yy) 
		{
			x = xx;
			y = yy;
		}

		Rey[] darAdjacentes()
		{
			if(adjacentes != null)
				return adjacentes;
			ArrayList <Rey> tmp = new ArrayList <Rey> ();
			for(int i = -1; i <= 1; i++)
				for(int j = -1; j <= 1; j++)
				{
					if(i == 0 && j == 0)
						continue;
					int xN = x + i;
					int yN = y + j;
					if(!adentro(xN, yN))
						continue;
					tmp.add(darRey(xN, yN));
				}
			return adjacentes = tmp.toArray(new Rey[0]);
		}
		
		Iterable <Rey> darAdjacentes(Posicion p1, Posicion p2)
		{
			return new IteradorRey(p1, p2, darAdjacentes());
		}
		
		class IteradorRey implements Iterable <Rey>, Iterator <Rey>
		{
			final Posicion a;
			final Posicion b;
			final Rey[] adjacentes;
			int currentI = 0;
			
			IteradorRey(Posicion p1, Posicion p2, Rey[] ad)
			{
				a = p1;
				b = p2;
				adjacentes = ad;
			}
			
			
			boolean iguales(Rey r, Posicion p)
			{
				return (r.x == p.x) && (r.y == p.y);
			}
			
			boolean inexistente()
			{
				return currentI < adjacentes.length && (iguales(adjacentes[currentI], a) || iguales(adjacentes[currentI], b));
			}
			
			@Override
			public boolean hasNext()
			{
				while(inexistente())
					currentI++;
				return currentI != adjacentes.length;
			}

			@Override
			public Rey next()
			{
				while(inexistente())
					currentI++;
				return adjacentes[currentI++];
			}

			@Override
			public void remove() 
			{
			}

			@Override
			public Iterator<Rey> iterator() 
			{
				return this;
			}
		}
		
		boolean enJaque(Reina reina)
		{
			return (x == reina.x || y == reina.y || (reina.x - x == reina.y - y) || (reina.x + reina.y == x + y));
		}
		
		boolean enJaqueMate(Reina reina, Rey otro)
		{
			if(!enJaque(reina))
				return false;
			for(Rey r : darAdjacentes(reina, otro))
				if(!r.enJaque(reina))
					return false;
			return true;
		}
	}
	
	Reina[][] dpReina = new Reina[101][101];
	
	Reina darReina(int x, int y)
	{
		if(dpReina[x][y] == null)
			dpReina[x][y] = new Reina(x, y);
		return dpReina[x][y];
	}
	
	static final int[][] deltasReina = new int[][] {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

	class Reina extends Posicion
	{
		Reina[] adjacentes;
		
		public Reina(int xx, int yy)
		{
			x = xx;
			y = yy;
		}
		
		Reina[] darAdjacentes()
		{
			if(adjacentes != null)
				return adjacentes;
			ArrayList <Reina> tmp = new ArrayList <Reina> ();
			for(int i = 0; i < 100; i++)
			{
				if(i == y)
					continue;
				int xN = x;
				int yN = i;
				tmp.add(darReina(xN, yN));
			}
			for(int i = 0; i < 100; i++)
			{
				if(i == x)
					continue;
				int xN = i;
				int yN = y;
				tmp.add(darReina(xN, yN));
			}
			for(int[] d : deltasReina)
			{
				int xN = x + d[0];
				int yN = y + d[1];
				while(adentro(xN, yN))
				{
					tmp.add(darReina(xN, yN));
					xN += d[0];
					yN += d[1];
				}
			}
			return adjacentes = tmp.toArray(new Reina[0]);
		}
	}

	boolean mateEnUno(Reina reina, Rey rey1, Rey rey2)
	{
		return rey1.enJaque(reina) || rey2.enJaque(reina);
	}
	
	boolean mateEnDos(Reina reina, Rey rey1, Rey rey2)
	{
		for(Reina a : reina.darAdjacentes())
		{
			if(rey1.enJaque(a) && rey2.enJaque(a))
				return true;
			if(rey1.enJaqueMate(a, rey2) || rey2.enJaqueMate(a, rey1))
				return true;
		}
		return false;
	}
	
	boolean mateEnTres(Reina reina, Rey rey1, Rey rey2)
	{
		outer:
		for(Reina a : reina.darAdjacentes())
		{
			for(Rey r : rey1.darAdjacentes(a, rey2))
			{
				if(!(mateEnUno(a, r, rey2) || mateEnDos(a, r, rey2)))
					continue outer;
			}
			for(Rey r : rey2.darAdjacentes(a, rey1))
			{
				if(!(mateEnUno(a, rey1, r) || mateEnDos(a, rey1, r)))
					continue outer;
			}
			return true;
		}
		return false;
	}
	
	public static void main(String[] args)
	{
		twokings tk = new twokings();
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt())
		{
			Reina reina = tk.darReina(sc.nextInt(), sc.nextInt());
			Rey rey1 = tk.darRey(sc.nextInt(), sc.nextInt());
			Rey rey2 = tk.darRey(sc.nextInt(), sc.nextInt());
			if(tk.mateEnUno(reina, rey1, rey2))
				System.out.println("1");
			else if(tk.mateEnDos(reina, rey1, rey2))
				System.out.println("2");
			else if(tk.mateEnTres(reina, rey1, rey2))
				System.out.println("3");
			else
				System.out.println("4");		
		}
		sc.close();
	}
}