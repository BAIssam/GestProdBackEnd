package org.bai.produit.GestProd;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class GestProdApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GestProdApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GestProdApplication.class);
	}

	// Launch the browser after running the application
	@EventListener({ ApplicationReadyEvent.class })
	private void applicationReadyEvent() {

		String url = "http://localhost:8080";

		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		} else {
			Runtime runtime = Runtime.getRuntime();
			String[] command;

			String operatingSystemName = System.getProperty("os.name").toLowerCase();
			if (operatingSystemName.indexOf("nix") >= 0 || operatingSystemName.indexOf("nux") >= 0) {
				String[] browsers = { "opera", "google-chrome", "epiphany", "firefox", "mozilla", "konqueror",
						"netscape", "links", "lynx" };
				StringBuffer stringBuffer = new StringBuffer();

				for (int i = 0; i < browsers.length; i++) {
					if (i == 0)
						stringBuffer.append(String.format("%s \"%s\"", browsers[i], url));
					else
						stringBuffer.append(String.format(" || %s \"%s\"", browsers[i], url));
				}
				command = new String[] { "sh", "-c", stringBuffer.toString() };
			} else if (operatingSystemName.indexOf("win") >= 0) {
				command = new String[] { "rundll32 url.dll,FileProtocolHandler " + url };

			} else if (operatingSystemName.indexOf("mac") >= 0) {
				command = new String[] { "open " + url };
			} else {
				System.out.println("an unknown operating system!!");
				return;
			}

			try {
				if (command.length > 1)
					runtime.exec(command); // linux
				else
					runtime.exec(command[0]); // windows or mac
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Kill ONE task
	public static void taskKill(String strProcessName) {
		String strCmdLine = null;
		strCmdLine = String.format("TaskKill /F /IM " + strProcessName);
		try {
			Runtime.getRuntime().exec(strCmdLine);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
