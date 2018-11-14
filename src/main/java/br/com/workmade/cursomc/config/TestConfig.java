package br.com.workmade.cursomc.config;

import java.text.ParseException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.workmade.cursomc.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	private Logger LOGGER = Logger.getLogger(TestConfig.class);

	@Autowired
	private DBService dBService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
@Bean
	public boolean instantiateDataBase() throws ParseException {
			LOGGER.info("Modo "+strategy+" ativo");
			dBService.instantiateDataBase();
		return false;
	}

}