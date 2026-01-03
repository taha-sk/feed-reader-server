# feed-reader-server

This is the server side of the Feed Reader application. 

The Feed Reader application is a secure web application where you manage feeds in widgets. Widgets are the short summaries of the parsed feeds. They display linked titles of the blog posts/news. This application is influenced from the old iGoogle implementation.

This application is using a PostgreSQL database.

Spring, JPA, JWT, JAXB technologies are used.

You can use Docker to preview this application. Clone the project and switch to the "feed-reader-server" directory in terminal. Then, execute "mvnw clean install" and observe a successful build. Next, execute "docker compose up -d". After containers are up and running, you can reach this application on:
http://localhost:8080/

After starting this image, you can also build and start the client application.
