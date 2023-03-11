<h1>FlightServicesBackend</h1>
<p>FlightServicesBackend is a Spring Boot application that provides an API for finding flights, making reservations, and checking in. This project is written in Java and can be used as a back-end for various travel-related applications.</p>
<h2>Technical Overview</h2>
<p>The project uses the following technologies:</p>
<ul><li>Spring Boot 3.0.3</li><li>Java 17</li><li>Maven 3.8.3</li><li>MySql 8.0</li></ul>
<p>The project is structured using the following packages:</p>
<ul><li><code>com.kyrxtz.flightservices</code>: Contains the main application class and configuration files.</li><li><code>com.kyrxtz.flightservices.api</code>: Contains the REST controller class that handle incoming HTTP requests.</li><li><code>com.kyrxtz.flightservices.dto</code>: Contains the model classes for the application.</li><li><code>com.kyrxtz.flightservices.repositories</code>: Contains the repository classes that are used to access the database.</li><li><code>com.kyrxtz.flightservices.entities</code>: Contains the entity classes.</li></ul>
<h2>How to Install</h2>
<p>To install the application, follow these steps:</p>
<ol><li>Clone the repository to your local machine using the following command:</li></ol>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code class="!whitespace-pre hljs language-java">https://github.com/&lt;username&gt;/FlightServiceBackend.git<span class="hljs-keyword">package</span>
</code>
<ol start="2"><li><p>Navigate to the project root directory.</p></li>
<li>Run the following command to start the application:</li>
<code class="!whitespace-pre hljs language-java">mvn spring-boot:run</code>
&nbsp;&nbsp;&nbsp;&nbsp;
<li><p>The application will start running on <code>http://localhost:8080</code>.</p></li>
