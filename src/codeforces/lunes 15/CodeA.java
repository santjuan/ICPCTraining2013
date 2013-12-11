import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;


public class CodeA  
{	
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		BigDecimal bd = sc.nextBigDecimal();
		BigInteger b = bd.toBigInteger();
		bd = bd.subtract(new BigDecimal(b));
		if(b.mod(BigInteger.valueOf(10)).intValue() == 9)
			System.out.println("GOTO Vasilisa.");
		else if(bd.compareTo(BigDecimal.valueOf(0.5)) >= 0)
		{
			b = b.add(BigInteger.ONE);
			System.out.println(b);
		}
		else
			System.out.println(b);
	}
}
