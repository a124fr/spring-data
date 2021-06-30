package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;
	
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudCargoService cargoService;
	
	public SpringDataApplication(CrudCargoService cargoService, 
			CrudFuncionarioService funcionarioService, 
			CrudUnidadeTrabalhoService unidadeTrabalhoService) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual ação você quer executar");
			System.out.println("0 - sair");
			System.out.println("1 - cargo");
			System.out.println("2 - unidade de trabalho");
			System.out.println("3 - funcionario");
						
			int acao = scan.nextInt();
			if(acao == 1) {
				this.cargoService.inicial(scan);						
			} else if(acao == 2) {
				this.unidadeTrabalhoService.inicial(scan);						
			} else if(acao == 3) {
				this.funcionarioService.inicial(scan);				
			}else {
				system = false;
			}
			
		}
	}
}
