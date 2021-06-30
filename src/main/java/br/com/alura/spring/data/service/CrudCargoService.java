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
	
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scan) {
		salvar(scan);
	}
	
	private void salvar(Scanner scan) {
		System.out.println("Descricao: do cargo");
		String descricao = scan.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		
		this.cargoRepository.save(cargo);
		System.out.println("Salvo");
	}
	
	public List<Cargo> listar() {
		List<Cargo> lista = new ArrayList<Cargo>();
		Iterable<Cargo> cargos = this.cargoRepository.findAll();
		cargos.forEach(c-> lista.add(c));
		
		return lista;
	}

	public void atualizar(Cargo cargo) {
		Optional<Cargo> existeCargo = this.cargoRepository.findById(cargo.getId());
		
		if(existeCargo.isPresent()) {
			this.cargoRepository.save(cargo);
		}
	}
		
}
