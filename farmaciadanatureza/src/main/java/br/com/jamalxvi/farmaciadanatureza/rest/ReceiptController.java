package br.com.jamalxvi.farmaciadanatureza.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/receipt", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReceiptController {

}
