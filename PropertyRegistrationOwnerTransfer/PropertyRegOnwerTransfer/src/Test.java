import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Socket;

import javax.imageio.ImageIO;


public class Test {
public static void main(String[] args) {
	try{
		BufferedImage bi = ImageIO.read(new File("D:/0000.png"));
//		byte[] byteArray = bi.by
		System.out.println("sending to server");
	    Socket socket=new Socket("192.168.0.102",13085);
	    java.io.OutputStream os=socket.getOutputStream();
	    java.io.InputStream is=socket.getInputStream();
	    System.out.println("Sending started");
//	    os.write();
//	    os.flush();
	    ImageIO.write(bi, "a.jpg", os);
//	    os.flush();
	    System.out.println("flushed ");
//	    os.close();
	    byte[] reading = new byte[1024];
	    System.out.println("reading ");
	    is.read(reading);
	    String str = new String(reading,0,reading.length);
	    System.out.println("str "+str);
	    is.close();
	    os.close();
	    socket.close();	
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
}
}
