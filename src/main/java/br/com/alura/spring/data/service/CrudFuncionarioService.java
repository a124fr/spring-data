package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {
	
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, 
			CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial(Scanner scan) {
		while(system) {
			System.out.println("Qual ação de Funcionario deseja executar?");
			System.out.println("0 - sair");
			System.out.println("1 - salvar");
			System.out.println("2 - atualizar");
			System.out.println("3 - visualisar");
			System.out.println("4 - deletar");
			Integer acao = scan.nextInt();
			
			switch(acao) {
			case 1:
				salvar(scan);
				break;
			case 2:
				atualizar(scan);
				break;
			case 3:
				visualizar(scan);
				break;
			case 4:
				deletar(scan);
				break;
			default:
				system = false;
				break;
			}
		}
		
		system = true;
	}
	
	private void salvar(Scanner scan) {
		System.out.println("Nome do funcionário?");
		String nome = scan.next();
		
		System.out.println("Cpf do funcionário?");
		String cpf = scan.next();
		
		System.out.println("Salario do funcionario?");
		BigDecimal salario = new BigDecimal( scan.next() );
		
		System.out.println("Data contratação do funcionario?");
		String dataContratacao = scan.next();
		
		System.out.println("Digite o cargoId do funcionário?");
		Integer cargoId = scan.nextInt();
		
		List<UnidadeTrabalho> unidades = unidade(scan);
		
		Funcionario funcionario = new Funcionario();		
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		Optional<Cargo> cargo = this.cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadesTrabalhos(unidades);		
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		
		this.funcionarioRepository.save(funcionario);
		
		System.out.println("Cadastrado");
	}
	
	public void atualizar(Scanner scan) {
		System.out.println("Id");
		int id = scan.nextInt();
		
		System.out.println("Nome do funcionário?");
		String nome = scan.next();
		
		System.out.println("Cpf do funcionário?");
		String cpf = scan.next();
		
		System.out.println("Salario do funcionario?");
		BigDecimal salario = new BigDecimal( scan.next() );
		
		System.out.println("Data contratação do funcionario?");
		String dataContratacao = scan.next();
		
		System.out.println("Digite o cargoId do funcionário?");
		Integer cargoId = scan.nextInt();
		
		List<UnidadeTrabalho> unidades = unidade(scan);
						
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		Optional<Cargo> cargo = this.cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadesTrabalhos(unidades);		
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
				
		this.funcionarioRepository.save(funcionario);
		System.out.println("Atualizado");
	}
	
	private void visualizar(Scanner scan) {
		System.out.println("Qual pagina você deseja visualizar");
		Integer pag = scan.nextInt();
		
		Pageable pageable = PageRequest.of(pag, 2, Sort.unsorted());		
		Page<Funcionario> funcionarios = this.funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Página atual: " + funcionarios.getNumber());
		System.out.println("Total elementos " + funcionarios.getTotalElements());
		funcionarios.forEach(System.out::println);
	}
	
	private void deletar(Scanner scan) {
		System.out.println("Id");
		int id = scan.nextInt();
		
		this.funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
	private List<UnidadeTrabalho> unidade(Scanner scan) {
		Boolean ehVerdadeiro = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();
		
		while(ehVerdadeiro) {
			System.out.println("Digite o unidadeId (Para sair digite 0");
			Integer unidadeId = scan.nextInt();
			
			if(unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				ehVerdadeiro = false;
			}			
		}		
		
		return unidades;
	}
	
}
