package dev1.alexkjam64.SpringBootProject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ClientPhoneMapper implements RowMapper<ClientPhone>{
    
    @Override
    public ClientPhone mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
        return new ClientPhone(rs.getInt("id"),
                            rs.getInt("countryCode"),
                            rs.getInt("areaCode"),
                            rs.getInt("localNumber"));
    }
}
