import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class test {
	public static void main(String[] args) throws URISyntaxException, IOException{
		java.net.URI uri = new java.net.URI("http://www.baidu.com");
        java.awt.Desktop.getDesktop().browse(uri);
	}

	
}
