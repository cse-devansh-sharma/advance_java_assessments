package com.capg.code;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;




@SpringBootApplication
public class StorageServiceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageServiceSystemApplication.class, args);

				System.out.println("----------Container Starting-------------");
			
		   		ApplicationContext context=new AnnotationConfigApplicationContext("com.capg.code");
		   		
		   		System.out.println("----------Container Started-------------");

		        StorageService defaultStorage = context.getBean(StorageService.class);
		        defaultStorage.storeFile("report.pdf");

		        System.out.println();

		        
		        StorageService local1 = context.getBean("localStorage", StorageService.class);
		        local1.storeFile("photo.jpg");

		        System.out.println();

		        StorageService local2 = context.getBean("localStorage", StorageService.class);
		        local2.storeFile("video.mp4");

		        System.out.println();

		        System.out.println("Are local1 and local2 the same object? "+ (local1 == local2)); 

		        StorageService cloud1 = context.getBean("cloudStorage", StorageService.class);
		        StorageService cloud2 = context.getBean("cloudStorage", StorageService.class);
		        System.out.println("Are cloud1 and cloud2 the same object? "+ (cloud1 == cloud2));


		        ((AbstractApplicationContext) context).close();
		    }
		
}
