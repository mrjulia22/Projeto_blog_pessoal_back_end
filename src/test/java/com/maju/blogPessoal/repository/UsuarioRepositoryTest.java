package com.maju.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.maju.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {

		usuarioRepository.deleteAll();

		usuarioRepository
				.save(new Usuario(0L, "João da silva", "http://imgur.com/111", "joao@dasilva.com", "123456789"));

		usuarioRepository
				.save(new Usuario(0L, "Manuela da silva", "http://imgur.com/111", "manuela@dasilva.com", "123456789"));

		usuarioRepository
				.save(new Usuario(0L, "Adriana da Silva", "http://imgur.com/111", "adriana@dasilva.com", "123456789"));

		usuarioRepository
				.save(new Usuario(0L, "Paulo Antunes", "http://imgur.com/111", "paulo@dasilva.com", "123456789"));

	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@dasilva.com");
		assertTrue(usuario.get().getUsuario().equals("joao@dasilva.com"));
	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");

		assertEquals(3, listaDeUsuarios.size());

		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da silva"));

		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da silva"));

		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));

	}

}