package ai.nanos.fullstack;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ai.nanos.fullstack.service.DbInitService;

@Component
public class DbBootstrapRunner implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(DbBootstrapRunner.class);
	
	@Autowired
	private DbInitService dbInitService;

	@Override
	public void run(String... args) throws Exception {
		log.info("Application started with command-line arguments: {}", Arrays.toString(args));
		try {
			log.info("Start to bootstrap db with initial data");
			dbInitService.initDB();
			log.info("Bootstraped DB done successfully");
		}
		catch(Exception ex) {
			log.error("Error when bootstraping DB: " + ex.getMessage(), ex);
		}
	}
}