# Vaadin Jetty 12 README

This is a reproducer for issues with DonwnloadHandler together with Jetty 12.

1. Start the application using the `JettyMain` main class.
2. Go to http://localhost:8080.
3. Click on Download.
4. Search for `org.eclipse.jetty.http.BadMessageException: 400: Ambiguous URI path encoding` in your IDEs console.
