package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scan) {
		System.out.println("Digite o nome: ");
		String nome = scan.next();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.println("Digite o cpf: ");
		String cpf = scan.next();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Digite o salário: ");
		BigDecimal salario = new BigDecimal(scan.next());
		
		if(salario.equals(BigDecimal.ZERO)) {
			salario = null;
		}
		
		System.out.println("Digite a data contratação: ");
		String data = scan.next();
		
		LocalDate dataContratacao = null;
		if(data.equalsIgnoreCase("NULL")) {
			data = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> lista = this.funcionarioRepository
				.findAll(Specification.where(
							SpecificationFuncionario.nome(nome))
							.or(SpecificationFuncionario.cpf(cpf))
							.or(SpecificationFuncionario.salario(salario))
							.or(SpecificationFuncionario.dataContratacao(dataContratacao))						
						);		
		
		lista.forEach(System.out::println);
	}
}
