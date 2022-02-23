package com.stockapi.training.services;

import java.util.List;
import java.util.Map;

public interface Istockservicehistorico {
    List<Map<String, Object>> buscarHistorico(long id);
}
