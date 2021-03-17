package com.antoinejonard.road2chall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Road2challApplication {

	/*
	Documentation :
		- JPA / Spring data -> Base de donnée
		- JAX-RS / Jersey -> Webservices
	lancement BDD :
		- java -cp hsqldb.jar org.hsqldb.server.Server --database.1 file:road2challdb --dbname.1 road2Chall
		- jdbc:hsqldb:hsql://localhost/road2Chall
	 */

	public static void main(String[] args) {
		SpringApplication.run(Road2challApplication.class, args);
	}

}
