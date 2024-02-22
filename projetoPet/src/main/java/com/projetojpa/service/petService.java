package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.pet;
import com.projetojpa.repository.petRepository;
import com.projetojpaDTO.petDTO;

@Service
public class petService {
	
	private final petRepository petRepository;
	
	@Autowired
	public petService(petRepository petRepository) {
		this.petRepository = petRepository;
	}
	
	//Método modificador para ultilizar o DTO
	public petDTO salvar(petDTO petDTO) {
		//Convert DTO to entity
		pet pet = new pet(petDTO.nome(),petDTO.documento(),petDTO.nascimento(),petDTO.cuidador());
		pet salvarpet = petRepository.save(pet);
		return new petDTO(salvarpet.getId(), salvarpet.getNome(), salvarpet.getDocumento(), salvarpet.getNascimento(), salvarpet.getCuidador());
		
	}
	//Método modificado para ultilizar o DTO 
	public petDTO atualizar(Long id, petDTO petDTO) {
		pet existepet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
		
		existepet.setNome(petDTO.nome());
		existepet.setCuidador(petDTO.cuidador());
		
		pet updatepet = petRepository.save(existepet);
		return new petDTO(updatepet.getId(), updatepet.getNome(), updatepet.getDocumento(), updatepet.getNascimento(), updatepet.getCuidador());
	}
	public boolean deletar(Long id) {
		Optional <pet> existepet = petRepository.findById(id);
		if (existepet.isPresent()) {
			petRepository.deleteById(id);
			return true;
		}
		return false;
	}
	public List<pet> buscarTodos(){
		return petRepository.findAll();
	}
	public pet buscarPorId(Long id) {
		Optional <pet> pet = petRepository.findById(id);
		return pet.orElse(null);
	}
}