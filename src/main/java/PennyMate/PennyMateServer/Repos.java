package PennyMate.PennyMateServer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class Repos {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Metodo per ottenere tutti gli utenti
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email")
        ));
    }

    // Metodo per ottenere un singolo utente per ID
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email")
        ));
    }

    // Metodo per aggiungere un nuovo utente
    public int addUser(User user) {
        String sql = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
    }

    // Metodo per aggiornare un utente esistente
    public int updateUser(User user) {
        String sql = "UPDATE users SET username = ?, email = ?, password = ?, WHERE id = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getId(), user.getPassword());
    }

    // Metodo per eliminare un utente
    public int deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
