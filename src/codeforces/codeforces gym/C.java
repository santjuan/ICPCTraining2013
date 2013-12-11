import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class C 
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
	}
	
	void floyd(int [][]g,int n){
	  
	 
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int [][]mat = new int [n][n];
		int [][]g = new int [n][n];
		for(int i=0;i<n;++i)
			for(int j=0;j<n;++j)
				g[i][j]=mat[i][j]=sc.nextInt();
				
		for (int k=0; k<n; ++k)
			for (int i=0; i<n; ++i)
				for (int j=0; j<n; ++j)
					g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
		
		boolean flag = true;
		for(int i=0;i<n && flag;++i)
			for(int j=0;j<n && flag;++j)
				flag=g[i][j]==mat[i][j];
		
		if(flag){
			for(int i=0;i<n && flag;++i){
				for(int j=0;j<n && flag;++j){
					System.out.print(g[i][j]+" ");
				}
				System.out.println("");
			}
			
		}else{
			System.out.println("-1");
		}
	}
	

}
