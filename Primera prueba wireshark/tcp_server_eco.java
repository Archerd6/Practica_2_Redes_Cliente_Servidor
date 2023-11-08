import java.io.*;
import java.net.*;

class tcp_server_eco
{
	public static void main(String args[])
	{
		String line;
		int numero_puerto = 6543;
		try
		{
			ServerSocket sockfd = new ServerSocket(numero_puerto);
			System.out.println("Inicio servidor " + sockfd);

			while (true)
			{
				Socket newsockfd = sockfd.accept();
				System.out.println("Nuevo cliente, socket " + newsockfd);

				BufferedReader in = new BufferedReader(new InputStreamReader(newsockfd.getInputStream()));
				PrintWriter out = new PrintWriter(
						new BufferedWriter(new OutputStreamWriter(newsockfd.getOutputStream())), true);
				boolean salir = false;
				while (!salir)
				{
					line = in.readLine(); // lectura socket cliente
					if (line != null)
					{
						out.println(line);
					} // escritura socket cliente
					else
					{
						salir = true;
					} // cierre socket cliente
				}
				newsockfd.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
