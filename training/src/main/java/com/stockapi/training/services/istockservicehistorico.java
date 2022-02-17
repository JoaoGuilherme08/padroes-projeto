package com.stockapi.training.services;

import java.util.List;
import java.util.Map;

public interface istockservicehistorico {
    List<Map<String, Object>> buscarHistorico(long Id);
}
