package org.example.orders.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isExistItemById(Long id){
        String sql = String.format("select count(*) from items where id = %s", id);
        Integer isPresent = jdbcTemplate.queryForObject(sql, Integer.class);
        return isPresent != null && isPresent > 0;
    }
}
