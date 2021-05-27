package br.bandtec.com.projetofrancescolly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableScheduling
@SpringBootApplication
public class ProjetoFrancescollyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFrancescollyApplication.class, args);
	}

}
