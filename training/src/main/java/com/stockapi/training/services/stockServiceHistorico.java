package com.stockapi.training.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.stockapi.training.repository.StockHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class stockServiceHistorico implements istockservicehistorico {

    @Autowired
    StockHistoricoRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> buscarHistorico(long idStock) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idStock", idStock);

        List<Map<String, Object>> retorno = namedParameterJdbcTemplate.queryForList(
                "with data as ( "
                        + "    select ask_min, created_on, date_trunc('minute', created_on) minuto "
                        + "    from historystock h2 "
                        + "    where id_stock = :idStock "
                        + "), min_max as ( "
                        + "    select minuto, min(ask_min) minimo, max(ask_min) maximo "
                        + "    from data "
                        + "    group by minuto "
                        + "),  first_last as ( "
                        + "    select (array_agg(ask_min order by created_on asc))[1] abertura, "
                        + "        (array_agg (ask_min order by created_on desc))[1] fechamento, "
                        + "        minuto "
                        + "        from data "
                        + "        group by  minuto "
                        + ")select f.abertura, f.fechamento, m.minimo, m.maximo, m.minuto "
                        + "	from first_last f join min_max m on f.minuto = m.minuto",
                parameters);
        return retorno;
    }
}
