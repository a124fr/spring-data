package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}	 
	
	public void inicial(Scanner scan) {
		while(system) {
			System.out.println("Qual ação de cargo deseja executar?");
			System.out.println("0 - sair");
			System.out.println("1 - busca funcionário nome");
			System.out.println("2 - busca funcionário nome, data contratação e salário maior");
			
			Integer acao = scan.nextInt();
			
			switch(acao) {
			case 1:
				buscaFuncionarioNome(scan);
				break;		
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scan);
			default:
				system = false;
				break;
			}
		}
		
		this.system = true;
	}
	
	private void buscaFuncionarioNome(Scanner scan) {
		System.out.println("Qual o nome deseja pesquisar");
		String nome = scan.next();		
		List<Funcionario> lista = this.funcionarioRepository.findByNome(nome);
		
		lista.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scan) {
		System.out.println("Qual o nome deseja pesquisar");
		String nome = scan.next();	
		
		System.out.println("Qual o data contratação deseja pesquisar");
		String data = scan.next();	
		LocalDate dataContratacao = LocalDate.parse(data, formatter);
		
		System.out.println("Qual o salário deseja pesquisar");
		BigDecimal salario = new BigDecimal(scan.next());
		
		List<Funcionario> lista = this.funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, dataContratacao);
		lista.forEach(System.out::println);
	}
	
}
