package net.sukhdev.springreact.springreact;

import net.sukhdev.springreact.springreact.model.User;
import net.sukhdev.springreact.springreact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringreactApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringreactApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		this.userRepository.save(new User("Sukh","Singh","sukh@gmail.com" ));
		this.userRepository.save(new User("John","Cena","john@gmail.com" ));
		this.userRepository.save(new User("Adam","Ingram","Adam@gmail.com" ));

	}
}
