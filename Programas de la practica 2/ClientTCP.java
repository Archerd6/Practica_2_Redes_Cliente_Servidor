import java.io.*;
import java.net.*;

public class ClientTCP
{
	public static void main(String[] args) throws IOException
	{
		String serverName = "localhost";
		int portNumber = 12345;
		Socket serviceSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			/* COMPLETAR Crear socket y conectar con servidor */
			System.out.println("Dame la IP del servidor:");
			serverName = stdIn.readLine();
			System.out.println("Dame el numero del puerto del servidor:");
			portNumber = Integer.parseInt(stdIn.readLine());			
			InetAddress serverAddr = InetAddress.getByName(serverName);
	        serviceSocket = new Socket(serverAddr, portNumber);
	        System.out.println("Conexion local"+serverAddr);
			
			/*COMPLETAR Inicializar los flujos de entrada/salida del socket conectado en las variables PrintWriter y BufferedReader*/
			in = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));
	        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(serviceSocket.getOutputStream())), true);
			/**/
		}
		catch (UnknownHostException e)
		{
			System.err.println("Unknown: " + serverName);
			System.exit(1);
		}
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O for " + "the connection to: " + serverName);
			System.exit(1);
		}
		System.out.println("STATUS: Conectado al servidor ");
		
		/* Obtener texto por teclado */
		String userInput;
		System.out.println("Introduzca un texto a enviar (END para acabar)");
		
		/* Enviar texto leido y almacenado en userInput al servidor a traves del socket */
		userInput = stdIn.readLine();
		
		/* COMPLETAR Comprobar si el usuario ha iniciado el fin de la interaccion */
		while (userInput.compareTo("END") != 0)
		{
			/* COMPLETAR Enviar texto en userInput al servidor a traves del flujo de salida del socket conectado*/
			out.println(userInput);
			
			System.out.println("STATUS: Enviando " + userInput); // Muestra por pantalla el texto enviado
			
			System.out.println("STATUS: Esperando eco"); // Muestra por pantalla estado
			
			/* COMPLETAR Recibir texto en echo enviado por el servidor a traves del flujo de entrada del socket conectado */
			String echo = null;
			
			echo = in.readLine(); /* Leer linea de socket conectado en variable echo */
			
			System.out.println("echo: " + echo); // Muestra por pantalla el eco recibido
			
			/* Leer texto de usuario por teclado */
			System.out.println("Introduzca un texto a enviar (END para acabar)");
			userInput = stdIn.readLine();
		} /* Fin del bucle de servicio en cliente */
		
		// Salimos porque el cliente quiere terminar la interaccion, ha introducido END
		System.out.println("STATUS: El cliente quiere terminar el servicio");
		
		/* COMPLETAR Enviar END al servidor para indicar el fin deL SERVICIO */
		out.println(userInput);
		
		System.out.println("STATUS: Sending " + userInput); // Muestra por pantalla el texto enviado
		System.out.println("STATUS: Waiting for the reply"); // Muestra por pantalla estado
		
		/* COMPLETAR Recibir OK enviado por el servidor confirmando EL FIN DEL SERVICIO */
		String ok = in.readLine(); // Leer linea de socket conectado en variable ok y comparar con "OK"
		
		System.out.println("STATUS: Cerrando conexion " + ok); // Muestra por pantalla el eco recibido
		/* COMPLETAR Cerrar flujos y socket */
		out.close();
	    in.close();
	    stdIn.close();
	    serviceSocket.close();
		
		System.out.println("STATUS: Conexion cerrada");
		
	}
}
