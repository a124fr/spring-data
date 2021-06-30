package br.com.alura.spring.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private Boolean system = true;
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scan) {
		while(system) {
			System.out.println("Qual ação de cargo deseja executar?");
			System.out.println("0 - sair");
			System.out.println("1 - salvar");
			System.out.println("2 - atualizar");
			Integer acao = scan.nextInt();
			
			switch(acao) {
			case 1:
				salvar(scan);
				break;
			case 2:
				atualizar(scan);
				break;				
			default:
				system = false;
				break;
			}
		}
	}
	
	private void salvar(Scanner scan) {
		System.out.println("Descricao: do cargo");
		String descricao = scan.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		
		this.cargoRepository.save(cargo);
		System.out.println("Salvo");
	}
	
	public void atualizar(Scanner scan) {
		System.out.println("Id");
		int id = scan.nextInt();
		
		System.out.println("Descrição do Cargo");
		String descricao = scan.next();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);		
		cargo.setDescricao(descricao);
		
		this.cargoRepository.save(cargo);
		System.out.println("Atualizado");;
	}
		
}
