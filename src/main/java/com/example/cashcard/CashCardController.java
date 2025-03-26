package com.example.cashcard;

import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//indica que esta clase es un controlador rest

@RestController
//indica que la url base para acceder a los metodos de esta clase es /cashcards
@RequestMapping("/cashcards")
public class CashCardController {

    private final CashCardRepository cashCardRepository;
    //inyecta el repositorio de CashCard
    public CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }
    //indica que este metodo se ejecuta cuando se hace una peticion get a /cashcards y se pasa un id
    @GetMapping("/{id}")
    private ResponseEntity<CashCard> findById(@PathVariable Long id) {
        //crea un objeto CashCard con el id y el amount especificados
        //optional es un contenedor que puede o no contener un valor
        Optional<CashCard> cashCardOptional = cashCardRepository.findById(id);
        //si el objeto existe, lo retorna   
        if(cashCardOptional.isPresent()){
            return ResponseEntity.ok(cashCardOptional.get());
        }
        //si no, retorna un error 404
        return ResponseEntity.notFound().build();
    }
}
