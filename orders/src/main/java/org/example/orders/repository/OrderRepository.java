package org.example.orders.repository;

import org.example.orders.models.entity.base.BaseOrderEntity;
import org.example.orders.repository.mapper.OrderRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    private final Map<String, RowMapper<? extends BaseOrderEntity>> tableNames = Map.of(
            "orders", new OrderRowMapper()
    );

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveOrder(Long sum,
                             Long scale,
                             String recipient,
                             String address,
                             String payment,
                             String delivery,
                             Long item_id,
                             String number
    ) {
        String sql = "insert into orders (sum, scale, recipient, address, payment, delivery, item_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, sum, scale, recipient, address, payment, delivery, number, item_id);
    }

    public Optional<BaseOrderEntity> getOrderById(String table, Long id) {
        if (tableNames.containsKey(table)) {
            String sql = String.format("select * from %s where id = ?", table);
                return Optional.of(jdbcTemplate.queryForObject(sql, tableNames.get(table), table, id));
            }
        return Optional.empty();
    }


    public Optional<List<BaseOrderEntity>> getOrderByDateAndSum(String table, Date createDate, Long sum, Long scale) {
        if (tableNames.containsKey(table)) {
            String sql = String.format("select * from %s where creation_date == ? and sum >= ? and scale >= ?", table);
            return Optional.of(jdbcTemplate.queryForList(sql, new Object[]{createDate, sum, scale}, BaseOrderEntity.class));
        }
        return Optional.empty();
    }
}
