package com.edivancarvalho.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.edivancarvalho.workshopmongo.domain.Post;
import com.edivancarvalho.workshopmongo.domain.User;
import com.edivancarvalho.workshopmongo.dto.AuthorDTO;
import com.edivancarvalho.workshopmongo.dto.CommentDTO;
import com.edivancarvalho.workshopmongo.repository.PostRepository;
import com.edivancarvalho.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		userRepository.deleteAll();
		postRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		
		Post post1 = new Post(null, sdf.parse("21/03/2024"), "Partiu Viagem", "Vou viajar para o Rio de Janeiro", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("18/05/2024"), "Bom dia!", "Desejo a todos um bom dia", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem!", sdf.parse("21/03/2024"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("21/03/2024"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("21/03/2024"), new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
