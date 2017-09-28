package cn.chinotan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cn.chinotan.dao.ClientDao;
import cn.chinotan.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public Client createClient(final Client client) {
        final String sql = "insert into oauth2_client(client_name, client_id, client_secret) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, client.getClientName());
                psst.setString(count++, client.getClientId());
                psst.setString(count++, client.getClientSecret());
                return psst;
            }
        }, keyHolder);

        client.setId(keyHolder.getKey().longValue());
        return client;
    }

    public Client updateClient(Client client) {
        String sql = "update oauth2_client set client_name=?, client_id=?, client_secret=? where id=?";
        jdbcTemplate.update(
                sql,
                client.getClientName(), client.getClientId(), client.getClientSecret(), client.getId());
        return client;
    }

    public void deleteClient(Long clientId) {
        String sql = "delete from oauth2_client where id=?";
        jdbcTemplate.update(sql, clientId);
    }

    public Client findOne(Long clientId) {
        String sql = "select id, client_name, client_id, client_secret from oauth2_client where id=?";
        List<Client> clientList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientId);
        if(clientList.size() == 0) {
            return null;
        }
        return clientList.get(0);
    }

    public List<Client> findAll() {
        String sql = "select id, client_name, client_id, client_secret from oauth2_client";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class));
    }


    public Client findByClientId(String clientId) {
        String sql = "select id, client_name, client_id, client_secret from oauth2_client where client_id=?";
        List<Client> clientList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientId);
        if(clientList.size() == 0) {
            return null;
        }
        return clientList.get(0);
    }


    public Client findByClientSecret(String clientSecret) {
        String sql = "select id, client_name, client_id, client_secret from oauth2_client where client_secret=?";
        List<Client> clientList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientSecret);
        if(clientList.size() == 0) {
            return null;
        }
        return clientList.get(0);
    }
}
