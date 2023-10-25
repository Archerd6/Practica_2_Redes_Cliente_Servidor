import java.io.*;
import java.net.*;

public class tcp_cliente_eco
{
	static final int serverPort = 6543;

	public static void main(String[] args) throws IOException
	{
		if ((args.length < 1) || (args.length > 2))
		{
			throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
		}

		try
		{
			InetAddress serverAddr = InetAddress.getByName(args[0]);
			Socket sockfd = new Socket(serverAddr, serverPort);
			System.out.println("Conexion local" + serverAddr);

			BufferedReader in = new BufferedReader(new InputStreamReader(sockfd.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(sockfd.getOutputStream())),true);
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String userInput; // Entrada por teclado
			while ((userInput = stdIn.readLine()) != null)
			{
				if (userInput.equals("."))
					break; // finaliza con el punto
				out.println(userInput); // escribo socket
				System.out.println("echo: " + in.readLine()); // leo socket
			}
			out.close();
			in.close();
			stdIn.close();
			sockfd.close();
		}
		catch (UnknownHostException e)
		{
			System.err.println("Unknown: " + args[0]);
			System.exit(1);
		}
		catch (IOException e)
		{
			System.err.println("Error I/O for " + args[0]);
			System.exit(1);
		}
	}
}
