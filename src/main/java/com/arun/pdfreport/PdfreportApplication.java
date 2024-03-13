package com.arun.pdfreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdfreportApplication {

  public static void main(String[] args) {
    SpringApplication.run(PdfreportApplication.class, args);
    System.out.println("http://localhost:8082/generatePdf run to load");
  }
}
//http://localhost:8080/jasper/SG04258000 run to load
