package com.example.inventario;

import com.example.inventario.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventarioApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}



    @Autowired
    private ProductoRepositorio repositorio;
    @Override
    public void run(String... args) throws Exception {

     //   Productos productos1 = new Productos("Producto1", 25, 3,5, 1, 2, true);
      //  repositorio.save(productos1);

    }
}
