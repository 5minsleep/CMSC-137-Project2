import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

  protected void start() {
    ServerSocket s;
    System.out.println("Webserver starting up on port 8000");
    try {
      // create the main server socket
      s = new ServerSocket(8000);
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    System.out.println("Waiting for connection");
    for (;;) {
      
      Socket remote = s.accept();
      
      System.out.println("Connection ... sending data.");
      BufferedReader in = new BufferedReader(new InputStreamReader(
          remote.getInputStream()));
      PrintWriter out = new PrintWriter(remote.getOutputStream());

      // Ok
      try {

        String str = ".";
        while (!str.equals(""))
          str = in.readLine();

        out.println("HTTP/1.0 200 OK");
        out.println("Content-Type: text/html");
        out.println("Server: Bot");
        out.println("");

        out.println("<h2>Welcome to the Ultra Mini-WebServer</h2>");
        out.flush();
        remote.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      FileInputStream requestedFile = null;

      try {
        String str = ".";
        while (!str.equals(""))
          str = in.readLine();
        requestedFile = new FileInputStream(str);
         
      }catch (Exception e){
        // not found
        try {         
          out.println("HTTP/1.0 404 NOT FOUND");
          out.println("Content-Type: text/html");
          out.println("Server: Bot");
          
          out.println("");
          out.flush();
          remote.close();
        }
        catch (Exception e2) {     
          e2.printStackTrace();    
        }
      }      
    }
  }

  public static void main(String args[]) {
    WebServer ws = new WebServer();
    ws.start();
  }
}

// Source: http://www.java2s.com/Code/Java/Network-Protocol/ASimpleWebServer.htm