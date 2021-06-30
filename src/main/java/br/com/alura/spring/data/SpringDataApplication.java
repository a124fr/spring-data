package br.com.alura.spring.data;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.service.CrudCargoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;
	
	private final CrudCargoService cargoService;
	
	public SpringDataApplication(CrudCargoService cargoService) {
		this.cargoService = cargoService;
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
			System.out.println("2 - atualizar cargo");
			
			int acao = scan.nextInt();
			if(acao == 1) {
				this.cargoService.inicial(scan);
			} else if(acao == 2) {
				System.out.println("Lista de Registro: ");
				List<Cargo> cargos = this.cargoService.listar();
				cargos.forEach(c->System.out.println(c.getId() + " - " + c.getDescricao()));
				System.out.println("---------------------------------------------");
				System.out.println("Digite um código de cargo?");
				Integer codigoCargo = scan.nextInt();
				System.out.println("Digite a nova descricao?");
				String descricaoCargo = scan.next();
				
				Cargo cargo = new Cargo();
				cargo.setId(codigoCargo);
				cargo.setDescricao(descricaoCargo);
				
				this.cargoService.atualizar(cargo);				
			} else {
				system = false;
			}
			
		}
	}
}
