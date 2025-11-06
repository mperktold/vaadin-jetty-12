/*
 * JettyMain  2021-05-14
 *
 * Copyright (c) Pro Data GmbH & ASA KG. All rights reserved.
 */

package com.example;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.theme.Theme;

import java.net.URI;
import java.net.URL;

import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.ee10.webapp.MetaInfConfiguration;
import org.eclipse.jetty.ee10.webapp.WebAppContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.URLResourceFactory;

/**
 * JettyMain
 *
 * @author Matthias Perktold
 * @since 2021-05-14
 */
@Theme("default")
public class JettyMain implements AppShellConfigurator {

	public static void main(String[] args) throws Exception {
		var webApp = new WebAppContext();
		webApp.setBaseResource(getFrontendBaseResource());
		webApp.setAttribute(MetaInfConfiguration.CONTAINER_JAR_PATTERN, ".*");
		webApp.setConfigurationDiscovered(true);
		webApp.getContext().setExtendedListenerTypes(true);
		webApp.setThrowUnavailableOnStartupException(true);

		ServletHolder holder = webApp.addServlet(VaadinServlet.class, "/*");
		holder.setAsyncSupported(true);

		var server = new Server(8080);
		server.setHandler(webApp);

		server.start();
		server.join();
	}

	private static Resource getFrontendBaseResource() {
		// Directories cannot be found reliably on classpath
		// Looking for a contained file ROOT.txt instead.
		// See:
		// https://github.com/mvysny/vaadin14-embedded-jetty/blob/master/src/main/java/com/vaadin/starter/skeleton/ManualJetty.java
		// https://github.com/eclipse/jetty.project/issues/4173#issuecomment-539769734
		final String rootName = "ROOT.txt";
		final String rootPath = "/META-INF/" + rootName;
		URL rootURL = JettyMain.class.getResource(rootPath);
		if (rootURL == null) {
			throw new IllegalStateException(
				rootPath + " doesn't exist, has webapp been packaged in as a resource?"
			);
		}
		final String rootUrlStr = rootURL.toString();
		return new URLResourceFactory().newResource(
			URI.create(rootUrlStr.substring(0, rootUrlStr.length() - rootName.length()))
		);
	}
}
