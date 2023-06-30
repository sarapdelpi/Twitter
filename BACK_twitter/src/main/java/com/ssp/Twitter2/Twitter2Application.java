package com.ssp.Twitter2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Twitter2Application {

	public static void main(String[] args) {

		ArrayList<Tweet> Datas = new ArrayList<>();
		LectorJson lector = new LectorJson();
		Datas = lector.LecturaJSONsimple();
		System.out.println(Datas);

		SpringApplication.run(Twitter2Application.class, args);
	}

}
