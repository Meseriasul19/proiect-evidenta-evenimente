package com.proiect.evidenta_evenimente_api.repository;

import com.proiect.evidenta_evenimente_api.model.Client;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    // Injectăm conexiunea la baza de date
    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metoda care aduce toți clienții
    public List<Client> findAll() {
        String sql = "SELECT * FROM Clienti";

        // RowMapper: Îi spune Java-ului cum să transforme un rând din SQL într-un obiect Client
        RowMapper<Client> mapper = (rs, rowNum) -> {
            Client c = new Client();
            c.setId(rs.getLong("id"));
            c.setNume(rs.getString("nume"));
            c.setEmail(rs.getString("email"));
            c.setTelefon(rs.getString("telefon"));
            return c;
        };

        return jdbcTemplate.query(sql, mapper);
    }

    public int save(Client client) {
        String sql = "INSERT INTO Clienti (nume, email, telefon) VALUES (?, ?, ?)";
        // Returneaza 1 daca s-a inserat cu succes
        return jdbcTemplate.update(sql, client.getNume(), client.getEmail(), client.getTelefon());
    }

    // 2. Metoda pentru ȘTERGERE (DELETE)
    public int deleteById(Long id) {
        String sql = "DELETE FROM Clienti WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}