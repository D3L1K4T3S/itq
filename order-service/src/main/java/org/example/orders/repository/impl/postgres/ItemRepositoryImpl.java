package org.example.orders.repository.impl.postgres;

import org.example.orders.repository.ItemRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isExistItemById(Long id){
        String sql = String.format("select count(*) from items where id = %s", id);
        Integer isPresent = jdbcTemplate.queryForObject(sql, Integer.class);
        return isPresent != null && isPresent > 0;
    }
}
