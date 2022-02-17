package com.stockapi.training.controller;

import java.util.List;
import java.util.Map;
import com.stockapi.training.services.istockservicehistorico;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/stockhistorico")
public class StockHistoricoController {
    istockservicehistorico service;

    public StockHistoricoController(istockservicehistorico service) {
        super();
        this.service = service;
    }

    @GetMapping("/{idStock}")
    public List<Map<String, Object>> buscarHistorico(@PathVariable Long idStock) {
        return service.buscarHistorico(idStock);
    }
}
