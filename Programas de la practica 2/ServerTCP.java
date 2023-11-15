import java.io.*;
import java.net.*;
import java.util.Scanner;

class ServerTCP
{
    @SuppressWarnings("resource")
	public static void main(String[] args) throws IOException
    {
        ServerSocket server = null;
        Socket client = null;
        //BufferedReader in = null;
        PrintWriter out = null;
        Scanner in = null;
        String line;
        int port = 12345; //puerto del servidor
        int count = 0;
        
        /*
         * COMPLETAR Crear e inicalizar el socket del servidor
         */
        try
        {
          server = new ServerSocket(port);
        }
        catch (IOException e)
        {
            System.out.println("Could not listen on port "+port);
            System.exit(-1);
        }
        /**/
        while (true) //funcion del servidor:
        {
            try
            {
                /*COMPLETAR Esperar conexiones entrantes */
            	client=server.accept();
            	System.out.println("Connecting with: "+client.getInetAddress().getHostAddress());
            	System.out.println("port: "+client.getPort());
            	

            }
            catch(IOException e)
            {
                System.out.println("Accept failed: "+ port);
                System.exit(-1);
            }

            try
            {

            	/*COMPLETAR Una vez aceptada una conexion, inicializar flujos de entrada/salida del socket conectado */

            	in = new Scanner(client.getInputStream());
            	out = new PrintWriter(client.getOutputStream());


/* */


            }
            catch (IOException e)
            {
                System.out.println("Exception "+e);
                System.exit(-1);
            }

            boolean salir = false;
            /* inicio bucle del servicio de Eco de 1 cliente */

	    while(!salir)
            {
                try
                {
                   /*COMPLETAR Recibir texto en variable String line enviado por el cliente a trav s del flujo de entrada del socket conectado */
                	line = in.nextLine();
                	
                	System.out.println("Received from client " + line);
                	
              		/*COMPLETAR Comprueba si es fin de conexion - SUSTITUIR POR LA CADENA DE FIN enunciado*/
                    if (line.compareTo("CADENA DE FIN ") != 0) 
                    {
                    	// Aquí tenemos que revertir la cadena introducida que será algo así:
                    	// (new StringBuffer(line.toUpperCase()).reverse().toString();
                        /* COMPLETAR Revertir y poner en mayúscula la cadena y Enviar texto al cliente a traves del flujo de salida del socket conectado*/
                    	
                    	String revertedLine=new StringBuffer(line.toUpperCase()).reverse().toString();
                    	out.println(revertedLine);
                    	System.out.println("Sending to client " + revertedLine);
                    	out.flush();
                    }

                    else // El cliente quiere cerrar conexion, ha enviado END
                    {
                        /* COMPLETAR Envia OK al cliente */

                    	out.print("OK");
                    	salir=true;
                    	out.flush();

                        /**/


                        salir = true;
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Read failed");
                    System.exit(-1);
                }
            } // fin del servicio

            System.out.println("Closing connection with the client");
            /* COMPLETAR Cerrar flujos y socket */
            in.close();
            out.close();
            client.close();
            
            
            
            System.out.println("Waiting for a new client");
        } // fin del bucle
        // server.close();
        
    }
} // fin del metodo y la clase