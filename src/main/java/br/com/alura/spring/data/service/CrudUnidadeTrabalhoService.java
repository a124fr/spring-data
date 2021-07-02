package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private Boolean system = true;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial(Scanner scan) {
		while(system) {
			System.out.println("Qual ação de Unidade de Trabalho deseja executar?");
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
				visualizar();
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
		System.out.println("Descrição da Unidade de Trabalho?");
		String descricao = scan.next();
		
		System.out.println("Endereço da Unidade de Traabalho?");
		String endereco = scan.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		this.unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Salvo");
	}
	
	public void atualizar(Scanner scan) {
		System.out.println("Id");
		int id = scan.nextInt();
		
		System.out.println("Descrição da Unidade de Trabalho?");
		String descricao = scan.next();
		
		System.out.println("Endereço da Unidade de Traabalho?");
		String endereco = scan.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		this.unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Atualizado");;
	}
	
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidadesTrabalhos = this.unidadeTrabalhoRepository.findAll();
		unidadesTrabalhos.forEach(System.out::println);
	}
	
	private void deletar(Scanner scan) {
		System.out.println("Id");
		int id = scan.nextInt();
		
		this.unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado");
	}	
}
