import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class IteratedDifference {
	
	static class Scanner{
		BufferedReader br=null;
		StringTokenizer tk=null;
		public Scanner(){
			br=new BufferedReader(new InputStreamReader(System.in));
		}
		public String next() throws IOException{
			while(tk==null || !tk.hasMoreTokens())
				tk=new StringTokenizer(br.readLine());
			return tk.nextToken();
		}
		public int nextInt() throws NumberFormatException, IOException{
			return Integer.valueOf(next());
		}
		public double nextDouble() throws NumberFormatException, IOException{
			return Double.valueOf(next());
		}
	}
	
	
	static int[] op(int[] a){
		int[] ret = new int[a.length];
		for(int i = 0; i < a.length; i++){
			ret[i] = Math.abs(a[i] - a[(i + 1) % a.length]);
		}
		return ret;
	}
	
	static boolean test(int[] a){
		int val = a[0];
		for(int i = 0; i < a.length; i++)
			if (val != a[i])
				return false;
		return true;
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		Scanner sc = new Scanner();
		int caso = 0;
		while(true){
			int N = sc.nextInt();
			if (N == 0) break;
			int[] a = new int[N];
			for(int i = 0; i < N; i++)
				a[i] = sc.nextInt();
			int turn = -1;
			int op = 0;
			while(op <= 1000){
				if (test(a)){
					turn = op;
					break;
				}
				a = op(a);
				op++;
			}
			System.out.print("Case "+ (++caso) +": ");
			if (turn == -1)
				System.out.println("not attained");
			else
				System.out.println(turn+" iterations");
		}
	}

}
