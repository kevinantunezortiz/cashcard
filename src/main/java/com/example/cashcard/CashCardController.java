package com.example.cashcard;

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

    //indica que este metodo se ejecuta cuando se hace una peticion get a /cashcards y se pasa un id
    @GetMapping("/{id}")
    private ResponseEntity<CashCard> findById(@PathVariable Long id) {
        //crea un objeto CashCard con el id y el amount especificados
        if(id.equals(99L)){
            CashCard cashCard = new CashCard(99L, 123.45);
            return ResponseEntity.ok(cashCard);
        }
        return ResponseEntity.notFound().build();
    }
}
