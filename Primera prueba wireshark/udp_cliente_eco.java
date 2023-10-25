import java.io.*;
import java.net.*;

public class udp_cliente_eco
{
	public static void main(String[] args) throws IOException
	{

		if ((args.length < 1) || (args.length > 2))
		{
			throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
		}
		InetAddress serverAddress = InetAddress.getByName(args[0]); // IP Servidor

		int servPort = (args.length == 2) ? Integer.parseInt(args[1]) : 6789;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

		String mensaje = stdIn.readLine();
		byte[] bytesToSend = mensaje.getBytes();

		DatagramSocket socket = new DatagramSocket();
		DatagramPacket sendPacket = new DatagramPacket(bytesToSend, // Datagrama a enviar
				bytesToSend.length, serverAddress, servPort);
		socket.send(sendPacket);
		DatagramPacket receivePacket = // Datagrama a recibir
				new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
		socket.receive(receivePacket); // Podria no llegar nunca el datagrama de ECO
		System.out.println("ECO:" + new String(receivePacket.getData()));
		socket.close();
	}
}
