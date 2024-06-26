package com.microservice.inventoryservice;

import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//@Bean
//public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//
//		return args -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("iphone_13");
//			inventory.setQuantity(10);
//
//			Inventory inventory2 = new Inventory();
//			inventory2.setSkuCode("iphone_13-red");
//			inventory2.setQuantity(10);
//
//			inventoryRepository.save(inventory2);
//			inventoryRepository.save(inventory);
//		};
//
//	}

}

