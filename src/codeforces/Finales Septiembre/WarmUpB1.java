
public class WarmUpB1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		for(long n = 0; n < 20000; n++)
		{
			{
				double m = n +  Math.sqrt(n * n + (n * n - 1));
				if(isPerfectSquare(n * n + (n * n - 1)))
				{
					long mR =  Math.round(m);
					long x = 2 * mR * n;
					long y = mR * mR - n * n;
					System.out.println(Math.min(x, y));
				}
			}
			{
				double m = n +  Math.sqrt(n * n + (n * n + 1));
				if(isPerfectSquare(n * n + (n * n + 1)))
				{
					long mR = Math.round(m);
					long x = 2 * mR * n;
					long y = mR * mR - n * n;
					System.out.println(Math.min(x, y));
				}
			}
		}
	}
	
	static boolean isPerfectSquare(long val)
	{
		long x = Math.round(Math.sqrt(val));
		return x * x == val;
	}

}
