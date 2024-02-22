package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.pet;
import com.projetojpa.service.petService;
import com.projetojpaDTO.petDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pet")
public class petController {
	
	private final petService petService;
	
	@Autowired
	public petController(petService petService) {
		this.petService = petService;
	}
	
	@PostMapping
	public ResponseEntity<petDTO> criar(@RequestBody @Valid petDTO petDTO){
		petDTO salvarpet = petService.salvar(petDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvarpet);
	}
	@PutMapping("/{id}")
	public ResponseEntity<petDTO> alterar(@PathVariable Long id, @RequestBody @Valid petDTO petDTO) {
		petDTO alterarpetDTO = petService.atualizar(id,petDTO);
		if(alterarpetDTO != null) {
			return ResponseEntity.ok(alterarpetDTO);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<pet> apagar(@PathVariable Long id) {
		boolean apagar = petService.deletar(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<pet> buscarPorId(@PathVariable Long id){
		pet pet = petService.buscarPorId(id);
		if(pet != null) {
			return ResponseEntity.ok(pet);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	public ResponseEntity<List<pet>>buscaTodos(){
		List<pet>pet = petService.buscarTodos();
		return ResponseEntity.ok(pet);
	}
	
}