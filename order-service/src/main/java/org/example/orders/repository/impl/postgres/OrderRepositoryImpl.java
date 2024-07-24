package org.example.orders.repository.impl.postgres;

import lombok.extern.slf4j.Slf4j;
import org.example.orders.exceptions.RepositoryException;
import org.example.orders.models.entity.OrderEntity;
import org.example.orders.repository.OrderRepository;
import org.example.orders.repository.mapper.OrderRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(OrderEntity order) throws RepositoryException {
        String sql = "insert into orders (number, sum, scale, recipient, address, payment, delivery) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, order.getNumber(), order.getSum(), order.getScale(), order.getRecipient(), order.getAddress(), order.getPayment().toString(), order.getDelivery().toString());
        } catch (DataAccessException exception) {
            log.error("Can't save order");
            throw new RepositoryException("Can't save order: " + exception.getMessage());
        }
    }

    @Override
    public OrderEntity findById(Integer id) throws RepositoryException {
            String sql = "select * from orders where id = ?";
            try {
                return jdbcTemplate.queryForObject(sql, new OrderRowMapper(), id);
            } catch (DataAccessException exception) {
                log.error("Can't find order by id: {}", id);
                throw new RepositoryException("Can't find order by id: " + exception.getMessage());
            }

    }

    @Override
    public List<OrderEntity> findByDateAndSum(Date createDate, Long sum, Long scale) throws RepositoryException {
        String sql = "select * from orders where order_date = ? and sum >= ? and scale >= ?";
        try {
            return jdbcTemplate.query(sql, new OrderRowMapper(), createDate, sum, scale);
        } catch (DataAccessException exception) {
            log.error("Can't find orders by date: {} and sum: {}", createDate, sum);
            throw new RepositoryException("Can't find orders by date and sum: " + exception.getMessage());
        }
    }

    @Override
    public List<OrderEntity> findWithoutOrderAndBetweenDate(String number, Date dateBefore, Date dateAfter) throws RepositoryException {
        String sql = "select * from orders where order_date >= ? and order_date <= ? and number != ? ";
        try {
            return jdbcTemplate.query(sql, new OrderRowMapper(), dateBefore, dateAfter, number);
        } catch (DataAccessException exception) {
            log.error("Can't find orders without order with number: {}", number) ;
            throw new RepositoryException("Can't find orders without order with number: " + exception.getMessage());
        }
    }
}
