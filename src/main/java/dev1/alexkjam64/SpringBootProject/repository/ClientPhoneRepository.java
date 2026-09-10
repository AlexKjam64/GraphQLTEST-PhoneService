package dev1.alexkjam64.SpringBootProject.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class ClientPhoneRepository {
    private final NamedParameterJdbcTemplate template;

    public ClientPhoneRepository(NamedParameterJdbcTemplate template){
        this.template = template;
    }

    private static final String batchQuery = """
            SELECT "id", "countryCode", "areaCode", "localNumber"
	        FROM "Nintendo"."Phone"
            WHERE "id" IN (:ID)
            """;

    public List<ClientPhone> getBatchPhones(List<Integer> ids){
        return template.query(batchQuery, new MapSqlParameterSource("ID", ids), new ClientPhoneMapper());
    }

    private static final String query = """
            SELECT "id", "countryCode", "areaCode", "localNumber"
	        FROM "Nintendo"."Phone"
            WHERE "id" = :ID
            """;

    public ClientPhone getPhone(int id){
        try{
            return template.queryForObject(query, new MapSqlParameterSource("ID", id), new ClientPhoneMapper());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private static final String insertQuery = """
            INSERT INTO "Nintendo"."Phone" ("id", "countryCode", "areaCode", "localNumber")
            VALUES (:ID, :COUNTRY, :AREA, :LOCAL)
            """;

    // Inserting new data into database
    public void addClient(ClientPhone newClient, int id){
        template.update(insertQuery, newClient.mapInfo(id));
    }

    // Updating data into database
    private static final String updateQuery = """
            UPDATE "Nintendo"."Phone"
            SET "countryCode" = :COUNTRY, "areaCode" = :AREA, "localNumber" = :LOCAL
            WHERE "id" = :ID
            """;

    public void updateClient(ClientPhone updateClient, int id){
        template.update(updateQuery, updateClient.mapInfo(id));
    }

    // Deleting data from database
    private static final String deleteQuery = """
            DELETE FROM "Nintendo"."Phone"
            WHERE "id" = :ID
            """;

    public void deleteClient(int id){
        template.update(deleteQuery, new MapSqlParameterSource("ID", id));
    }
}
