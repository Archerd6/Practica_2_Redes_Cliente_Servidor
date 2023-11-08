package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientUDP {
	public static void main(String[] args) throws IOException {
		String serverName = ""; //direccion local
		int serverPort = ""; //direccion puerto
		DatagramSocket serviceSocket = null;
		InetAddress serverAddress = null;
		DatagramPacket recivePacket = null;
		
		try	{
			/*crear socket*/
			serviceSocket = new DatagramSocket();
			serverAddress = InetAddress.getByName(serverName);
		}
		catch (SocketException e) {  // Se ha importado socket exception
			System.err.println("Unknown: " + serverName);
			System.exit(1);
		}
		/* INICIALIZA ENTRADA POR TECLADO*/
		BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
		System.out.println("Introduce el puerto del Servidor: ");
		String puertoN = stdIn.readLine();
		serverPort = Integer.parseInt(puertoN);
		System.out.println("Introduzca un texto a enviar (END-UDP para acabar)");
		String userInput;
		userInput = stdIn.readLine(); /*CADENA ALMACENADA EN userInput*/
		/* Comprobar si el usuario quiere terminar servicio*/
		while (userInput.compareTo("END-UDP") != 0)
		{
			/*Crear datagrama con la cadena escrito en el cuerpo */
			byte[] bytesToSend = userInput.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, serverPort);
			// Se han incluido ambas en el mismo try cach 
			try
			{
				/*crear socket*/
				serviceSocket = new DatagramSocket();
				/*Enviar datagrama a trav s del socket */
				serviceSocket.send(sendPacket); 			
			}
			catch (UnknownHostException e)
			{
				System.err.println("Unknown: " + serverName);
				System.exit(1);
			}
			catch (IOException iOException) {	}
			System.out.println("STATUS: Waiting for the reply");
			/*Crear e inicializar un datagrama VACIO para recibir la respuesta*/
			recivePacket  = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length); 
			/*Recibir datagrama de respuesta*/
			serviceSocket.receive(recivePacket);
			/*Extraer contenido del cuerpo del datagrama en variable line*/
			byte[] contenido = recivePacket.getData();
			String line = new String(contenido);
			System.out.println("echo: " + line);
			System.out.println("Introduzca un texto a enviar (END-UDP para acabar)");
			userInput = stdIn.readLine();
		}
		System.out.println("STATUS: Closing lient");
		//Cerrar socket cliente*/
		serviceSocket.close();
		System.out.println("STATUS: closed");
	}
}
