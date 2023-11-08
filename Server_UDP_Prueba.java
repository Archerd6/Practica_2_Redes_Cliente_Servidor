package server;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException 
	{
		DatagramSocket server = null;
		String line;
		int port = ""; //puerto del servidor
		try
		{	/*
			 * Crear e inicalizar el socket del servidor
			 */
			server = new DatagramSocket(port);
		}
		catch (IOException e) 
		{
			System.out.println("Could not listen on port "+port);                  
			System.exit(-1);
		}
		while (true) //funcion PRINCIPAL del servidor:
		{
			System.out.println("Waiting for a new UDP client");
			/* Crear e inicializar un datagrama VACIO para recibir */
			DatagramPacket packet = new DatagramPacket(new byte[255], 255);
			/* Recibir datagrama*/
			server.receive(packet);
			/* Mostrar por pantalla la direccion socket del cliente que solicito el servicio de eco*/
			System.out.println("IP cliente: " + packet.getAddress().getHostAddress() + " Puerto cliente: " + packet.getPort());
			/* revertir y poner en mayúscula la cadena y Crear datagrama de respuesta*/
			String lectura = new String(packet.getData(), 0, packet.getLength());
			String cadenaMayuscula = lectura.toUpperCase();
			String cadenaRevertida = (new StringBuffer(cadenaMayuscula)).reverse().toString();
			line=cadenaRevertida;
			System.out.println("STATUS: Echo created: "+line); 
			/* Enviar datagrama de respuesta*/
			byte[] lineBytes = line.getBytes();
			DatagramPacket newPacket = new DatagramPacket(lineBytes, lineBytes.length,packet.getAddress(), packet.getPort());
			server.send(newPacket);
			System.out.println("STATUS: Echo sent"); 
			System.out.println("STATUS: Waiting for new echo");
		}
	} 
}
