import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class G {
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
		
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		String mio = sc.next();
		char []cad = mio.toCharArray();
		if(cad.length>26){
			System.out.println("IMPOSSIBLE");
		}else{
			boolean []used = new boolean [26];
			boolean []first = new boolean [cad.length];
			Arrays.fill(used, false);
			for(int i=0;i<cad.length;++i){
				first[i] = (used[(int)(cad[i]-'a')])? false:true;
				used[(int)(cad[i]-'a')] = true;
				
			}
			for(int i=0;i<cad.length;++i){
				if(used[(int)(cad[i]-'a')] && !first[i])
					cad[i] = next(used);				
			}
			System.out.println(cad);
		}
		
	}

	private static char next(boolean[] used) {
		for(int i=0;i<used.length;++i){
			if(!used[i]){
				used[i]=true;
				return (char)(i+'a');
			}
		}
		return 'z';
	}
}
