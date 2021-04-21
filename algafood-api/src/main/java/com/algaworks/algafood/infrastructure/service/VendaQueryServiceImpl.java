package com.algaworks.algafood.infrastructure.service;

import com.algaworks.algafood.domain.dto.VendaDiaria;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import com.algaworks.algafood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @Autowired
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<VendaDiaria> query = builder.createQuery(VendaDiaria.class);
        Root<Pedido> root = query.from(Pedido.class);

        Expression<Date> convertTzDataOffset = builder.function("convert_tz", Date.class,
                root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeOffset));

        Expression<Date> dataCriacaoExpression = builder.function("date", Date.class, convertTzDataOffset);

        CompoundSelection<VendaDiaria> selection = builder.construct(VendaDiaria.class,
                dataCriacaoExpression,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal"))
        );

        ArrayList<Predicate> predicates = new ArrayList<>();
        if (filtro.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
        }

        if (filtro.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                    filtro.getDataCriacaoInicio()));
        }

        if (filtro.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                    filtro.getDataCriacaoFim()));
        }

//        predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(dataCriacaoExpression);

        return manager.createQuery(query).getResultList();
    }
}
