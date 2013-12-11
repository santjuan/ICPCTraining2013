import java.util.ArrayList;
import java.util.Scanner;


public class CodeG
{
	static int insert = 1;
	static int delete = 2;
	static int replace = 3;
	static int match = 4;
	static int insertMatch = 5;
	
	static class Respuesta
	{
		int operacion;
		int distancia;
		Respuesta hija;
		
		public Respuesta(int op, int dis)
		{
			operacion = op;
			distancia = dis;
		}
	}
	
	static char[] aS;
	static char[] bS;
	
	static Respuesta[][] respuesta = new Respuesta[1011][1011];
	
	static Respuesta editDistance(int a, int b)
	{
		if(respuesta[a][b] != null)
			return respuesta[a][b];
		if(a == 0)
		{
			if(b == 0)
				return respuesta[a][b] = new Respuesta(0, 0);
			else
			{
				Respuesta ans = new Respuesta(1, b);
				ans.hija = editDistance(a, b - 1);
				return respuesta[a][b] = ans;
			}
		}
		if(b == 0)
		{
			Respuesta ans = new Respuesta(2, a);
			ans.hija = editDistance(a - 1, b);
			return respuesta[a][b] = ans;
		}
		Respuesta matchR = editDistance(a - 1, b - 1);
		Respuesta mejor = new Respuesta(aS[a - 1] == bS[b - 1] ? match : replace, matchR.distancia + (aS[a - 1] == bS[b - 1] ? 0 : 1));
		mejor.hija = matchR;
		Respuesta del = editDistance(a - 1, b);
		Respuesta posible = new Respuesta(delete, del.distancia + 1);
		posible.hija = del;
		if(mejor.distancia > posible.distancia)
			mejor = posible;
		Respuesta ins = editDistance(a, b - 1);
		posible = new Respuesta(insertMatch, ins.distancia + 1);
		posible.hija = ins;
		if(mejor.distancia > posible.distancia)
			mejor = posible;
		return respuesta[a][b] = mejor;
	}
	
	
	static ArrayList <String> valores = new ArrayList <String> ();
	
	static void simulate(int a, int b, Respuesta r)
	{
		if(a == 0 && b == 0)
			return;
		if(r.operacion == match)
			simulate(a - 1, b - 1, r.hija);
		if(r.operacion == insertMatch)
		{
			valores.add("INSERT " + (a + 1) + " " + bS[b - 1]);
			simulate(a, b - 1, r.hija);
		}
		if(r.operacion == delete)
		{

			valores.add("DELETE " + a);
			simulate(a - 1, b, r.hija);
		}
		if(r.operacion == replace)
		{
			valores.add("REPLACE " + a + " " + bS[b - 1]);
			simulate(a - 1, b - 1, r.hija);
		}
		if(r.operacion == insert)
		{
			valores.add("INSERT 1 " + bS[b - 1]);
			simulate(a, b - 1, r.hija);
		}
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		aS = sc.next().toCharArray();
		bS = sc.next().toCharArray();
		Respuesta r = editDistance(aS.length, bS.length);
		simulate(aS.length, bS.length, r);
		System.out.println(valores.size());
		for(String s : valores)
			System.out.println(s);
	}
}
