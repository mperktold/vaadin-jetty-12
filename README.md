# Vaadin Jetty 12 README

This is a reproducer for issues with DonwnloadHandler together with Jetty 12.
It programmatically starts a Jetty server that runs a Vaadin app.
The Vaadin app provides a single view with a download link that fails because of the filename.

1. Start the application using the `JettyMain` main class.
2. Go to http://localhost:8080.
3. Click on Download.
4. Search for `org.eclipse.jetty.http.BadMessageException: 400: Ambiguous URI path encoding` in your IDEs console.
