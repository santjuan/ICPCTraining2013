import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Scanner;


public class PromedioUTP 
{
	URLConnection connection;
	OutputStream os = null;

	protected void connect() throws IOException {
		if (os == null) os = connection.getOutputStream();
	}

	protected void write(char c) throws IOException {
		connect();
		os.write(c);
	}

	protected void write(String s) throws IOException {
		connect();
		os.write(s.getBytes());
	}

	protected void newline() throws IOException {
		connect();
		write("\r\n");
	}

	protected void writeln(String s) throws IOException {
		connect();
		write(s);
		newline();
	}

	private static Random random = new Random();

	protected static String randomString() {
		return Long.toString(random.nextLong(), 36);
	}

	String boundary = "---------------------------" + randomString() + randomString() + randomString();

	private void boundary() throws IOException {
		write("--");
		write(boundary);
	}

	public PromedioUTP(URLConnection connection) throws IOException {
		this.connection = connection;
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + boundary);
	}

	public PromedioUTP(URL url) throws IOException {
		this(url.openConnection());
	}

	public PromedioUTP(String urlString) throws IOException {
		this(new URL(urlString));
	}

	public InputStream post() throws IOException {
		boundary();
		writeln("--");
		os.close();
		return connection.getInputStream();
	}


	static StringBuilder sb = new StringBuilder();

	public static String trimInterno(String a)
	{
		sb.setLength(0);
		boolean espacio = false;
		for(char c : a.toCharArray())
		{
			if(c == ' ')
			{
				if(espacio)
					continue;
				sb.append(' ');
				espacio = true;
			}
			else
			{
				espacio = false;
				sb.append(c);
			}
		}
		return sb.toString();
	}

	static String nombreS = "<td colspan=\"3\" align=\"center\" class=\"style1\"><span class=\"style6\">";
	static String periodoS = "<td width=\"64\" height=\"15\" bgcolor=\"#FFFFFF\" class=\"style4\">Periodo</td>";
	static String periodoS1 = "<td width=\"424\" valign=\"middle\" bgcolor=\"#FFFFFF\" class=\"style6\">";
	static String materiaS = "<tr class=\"style6\">";
	static String tablaS = "</table>";

	public static void leer(String entrada)
	{

		int indice = entrada.indexOf(nombreS);
		entrada = entrada.substring(indice + nombreS.length());
		indice = entrada.indexOf("<");
		String nombre = trimInterno(entrada.substring(0, indice).trim());
		entrada = entrada.substring(indice);
		System.out.println(nombre);
		int creditosCarrera = 0;
		double promedioCarrera = 0;
		while((indice = entrada.indexOf(periodoS)) != -1)
		{
			entrada = entrada.substring(indice + periodoS.length());
			indice = entrada.indexOf(periodoS1);
			if(indice == -1)
				break;
			entrada = entrada.substring(indice + periodoS1.length());
			String periodo = trimInterno(entrada.substring(0, indice = entrada.indexOf("<")));
			System.out.println(periodo);
			entrada = entrada.substring(indice);
			double promedio = 0;
			int creditosSuma = 0;
			while((indice = entrada.indexOf(materiaS)) < entrada.indexOf(tablaS) && indice != -1)
			{
				entrada = entrada.substring(indice + materiaS.length());
				indice = entrada.indexOf("\">");
				entrada = entrada.substring(indice + 2);
				String codigo = trimInterno(entrada.substring(0, entrada.indexOf("<")).trim());
				entrada = entrada.substring(entrada.indexOf("<"));
				indice = entrada.indexOf("\">");
				entrada = entrada.substring(indice + 2);
				String nombreMateria = trimInterno(entrada.substring(0, entrada.indexOf("<")).trim());
				entrada = entrada.substring(entrada.indexOf("<"));
				indice = entrada.indexOf("\">");
				entrada = entrada.substring(indice + 2);
				String creditos1 = trimInterno(entrada.substring(0, entrada.indexOf("<")).trim());
				entrada = entrada.substring(entrada.indexOf("<"));
				indice = entrada.indexOf("\">");
				entrada = entrada.substring(indice + 2);
				String grupo1 = trimInterno(entrada.substring(0, entrada.indexOf("<")).trim());
				entrada = entrada.substring(entrada.indexOf("<"));
				indice = entrada.indexOf("\">");
				entrada = entrada.substring(indice + 2);
				String nota1 = trimInterno(entrada.substring(0, entrada.indexOf("<")).trim());
				entrada = entrada.substring(entrada.indexOf("<"));
				double nota;
				try
				{
					nota = Double.parseDouble(nota1);
					if(nota == 0) // Se presume que si saco 0 es porque es una materia de este semestre que esta cursando
						continue;
				}
				catch(Exception e)
				{
					continue;
				}
				int creditos = Integer.parseInt(creditos1);
				int grupo = Integer.parseInt(grupo1);
				promedio += nota * creditos;
				creditosSuma += creditos;
				System.out.println(codigo + " " + nombreMateria + " " + nota + " " + creditos + " " + grupo);
			}
			promedioCarrera += promedio;
			creditosCarrera += creditosSuma;
			System.out.println("Promedio semestre: " + promedio / creditosSuma);
		}
		System.out.println("Promedio carrera " + promedioCarrera / creditosCarrera);
	}

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			PromedioUTP cht = new PromedioUTP("http://app4.utp.edu.co/MatAcad/verificacion/oracle.php?codigo2=" + sc.nextLong() + "&cmbprogramas=28");
			InputStream is = cht.post();
			int lec;
			sb.setLength(0);
			while((lec = is.read()) != -1)
				sb.append((char)lec);
			leer(sb.toString().replace("&nbsp;", ""));
		}
	}
}