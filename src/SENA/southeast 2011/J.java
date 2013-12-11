import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class J
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
	
	public static boolean intentar(int n)
	{
		if(dp[n] != null)
			return dp[n];
		int[] vals = new int[10];
		for(char c : (n + "").toCharArray())
			vals[c - '0']++;
		int raiz = (int) Math.min(n, Math.ceil(Math.sqrt(n)));
		outer:
		for(int i = 2; i <= raiz; i++)
		{
			if(n % i == 0)
			{
				int complemento = n / i;
				int[] current = vals.clone();
				for(char c : (i + "").toCharArray())
					current[c - '0']--;
				for(char c : (complemento + "").toCharArray())
					current[c - '0']--;
				for(int j = 0; j < 10; j++)
					if(current[j] != 0)
						continue outer;
				return dp[n] = true;
			}
		}
		return dp[n] = false;
	}
	
	static Boolean[] dp = new Boolean[1200000];
	static int[] vals = new int[] {126, 153, 688, 1206, 1255, 1260, 1395, 1435, 1503, 1530, 1827, 2187, 3159, 3784, 6880, 10251, 10255, 10426, 10521, 10525, 10575, 11259, 11844, 11848, 12006, 12060, 12384, 12505, 12546, 12550, 12595, 12600, 12762, 12843, 12955, 12964, 13243, 13545, 13950, 14035, 14350, 15003, 15030, 15246, 15300, 15435, 15624, 15795, 16272, 17325, 17428, 17437, 17482, 18225, 18265, 18270, 19026, 19215, 21375, 21586, 21753, 21870, 25105, 25375, 25474, 25510, 28476, 29632, 31509, 31590, 33655, 33696, 36855, 37840, 37845, 39784, 41665, 42898, 44676, 45684, 45760, 45864, 47538, 48672, 49855, 51759, 52168, 53865, 56295, 56875, 62968, 63895, 67149, 67392, 67950, 68800, 71199, 78975, 100255, 100525, 101299, 102051, 102505, 102510, 102541, 102550, 102595, 102942, 102955, 103968, 104026, 104260, 104368, 105021, 105025, 105075, 105210, 105250, 105264, 105295, 105723, 105750, 107163, 107329, 108126, 108135, 108216, 108612, 108864, 109525, 110758, 112468, 112509, 112590, 114268, 115672, 115699, 116478, 116496, 116725, 116928, 117067, 118408, 118440, 118480, 118575, 118926, 119848, 120006, 120060, 120384, 120600, 120762, 120843, 121086, 121576, 121815, 122746, 122764, 123084, 123354, 123538, 123840, 123894, 124483, 124488, 124542, 124978, 125005, 125050, 125095, 125248, 125433, 125460, 125500, 125950, 125995, 126000, 126027, 126108, 126846, 127417, 127620, 128403, 128430, 128943, 129438, 129505, 129514, 129550, 129564, 129595, 129640, 129775, 129955, 131242, 132430, 132565, 132615, 132655, 133245, 134275, 134725, 135045, 135450, 135828, 135837, 136525, 136854, 136948, 138784, 139500, 139824, 140035, 140350, 141345, 142978, 143500, 143739, 143793, 145273, 145314, 145345, 145683, 146137, 146520, 146952, 149364, 149782, 150003, 150030, 150246, 150300, 150435, 150624, 150826, 152271, 152406, 152460, 152608, 152685, 152946, 153000, 153436, 154350, 155277, 156024, 156240, 156289, 156325, 156915, 157950, 158193, 162072, 162526, 162720, 162976, 163255, 163795, 163854, 163944, 164583, 165208, 168520, 171598, 172246, 172386, 172822, 173250, 173925, 174028, 174082, 174208, 174280, 174298, 174370, 174793, 174802, 174820, 174982, 175329, 176215, 178294, 178942, 179325, 179428, 179482, 180225, 180297, 180621, 182065, 182250, 182650, 182700, 182974, 184126, 186624, 187029, 189702, 189742, 190260, 190827, 191205, 192150, 192375, 192685, 192717, 193257, 193945, 194229, 197428, 197482, 197725, 201852, 205785, 207391, 208624, 210375, 210681, 210753, 211896, 212868, 213075, 213466, 213750, 213759, 214506, 215086, 215424, 215455, 215860, 216733, 217503, 217530, 217638, 217854, 218488, 218700, 223524, 226498, 226872, 226876, 227448, 229648, 231579, 231673, 233896, 236754, 236758, 236925, 238968, 241506, 241564, 243175, 245182, 245448, 246150, 246928, 250105, 250510, 251005, 251050, 251095, 251896, 253750, 254740, 255010, 255100, 256315, 256410, 256414, 258795, 259510, 260338, 261378, 261783, 262984, 263074, 263155, 263736, 267034, 268398, 279328, 281736, 283198, 283648, 284598, 284760, 285376, 286416, 286974, 287356, 289674, 291375, 291753, 293625, 295105, 295510, 296320, 297463, 297832, 304717, 307183, 312475, 312565, 312655, 312975, 314199, 314743, 315009, 315090, 315490, 315594, 315625, 315900, 316255, 319059, 319536, 325615, 326155, 326452, 328419, 328864, 329346, 329656, 336195, 336550, 336960, 338296, 341284, 341653, 342688, 346288, 346725, 346968, 347913, 352966, 355995, 361989, 362992, 365638, 368104, 368550, 368784, 369189, 371893, 373864, 375156, 375615, 376992, 378400, 378418, 378450, 381429, 384912, 384925, 386415, 390847, 392566, 393246, 393417, 394875, 397840, 399784, 404932, 404968, 414895, 415575, 416065, 416259, 416650, 416988, 419287, 428980, 429664, 435784, 439582, 442975, 446760, 446976, 447916, 449676, 449955, 450688, 451768, 456840, 457168, 457600, 458640, 462672, 465088, 465984, 468535, 475380, 475893, 476892, 486720, 488592, 489159, 489955, 490176, 491688, 493857, 495328, 497682, 498550, 515907, 516879, 517509, 517590, 519745, 520168, 520816, 521608, 521680, 526792, 529672, 530379, 531297, 535968, 536539, 538650, 549765, 559188, 562950, 564912, 567648, 568750, 571648, 573768, 588676, 611793, 611878, 612598, 614965, 617728, 618759, 623758, 629680, 632875, 638950, 649638, 661288, 665919, 667876, 671409, 671490, 671944, 673920, 678892, 679500, 687919, 688000, 692712, 697248, 702189, 702918, 710496, 711099, 711909, 711990, 715959, 719199, 729688, 736695, 738468, 741928, 769792, 773896, 778936, 782896, 785295, 789250, 789525, 789750, 791289, 792585, 794088, 798682, 809919, 809937, 809964, 815958, 829696, 841995, 859968, 899019, 936985, 939658, 960988, 1000255};

	
	public static void main(String[] args)
	{
		TreeSet <Integer> valSet = new TreeSet <Integer> ();
		for(int i : vals)
			valSet.add(i);
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				return;
			System.out.println(valSet.ceiling(n));
		}
	}
}