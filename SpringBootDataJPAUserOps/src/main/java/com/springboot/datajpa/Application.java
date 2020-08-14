package com.springboot.datajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.datajpa.dao.UserInfoDAO;
import com.springboot.datajpa.model.UserInfo;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserInfoDAO repository) {
		return (args) -> {
			// save a few users
			UserInfo userInfo = new UserInfo();
			userInfo.setUserID("Abhijeet24");
			userInfo.setFirstName("Abhijeet");
			userInfo.setLastName("Kulkarni");
			userInfo.setMiddleInitial("R");
			userInfo.setDob("27/11/1989");
			userInfo.setEmailID("kulkarniabhijeet17@gmail.com");

			repository.save(userInfo);

			// fetch all users
			log.info("Users found with findAll():");
			log.info("-------------------------------");
			for (UserInfo ui : repository.findAll()) {
				System.out.println(ui.toString());
			}
			log.info("");

			// fetch an individual user by ID
			UserInfo user = repository.findByUserID("Abhijeet24");
			log.info("User found with findByUserID('Abhijeet24'):");
			log.info("--------------------------------");
			log.info(user.toString());
			log.info("");

			// fetch users by last name
			log.info("User found with findByLastName('Kulkarni'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Kulkarni").forEach(kulkarni -> {
				System.out.println(kulkarni.toString());
			});
			log.info("");
		};
	}
}