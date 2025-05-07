package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.UserEntity;
import hu.pte.mik.prog4.zh2.entity.RoleEntity;
import javax.naming.NamingException;
import java.sql.*;

public class UserRepository extends Repository {

    public UserEntity findByUsername(String username) {
        String sql = "SELECT id, username, password FROM user WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UserEntity user = new UserEntity();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
        } catch (SQLException | NamingException e) {
            throw new RuntimeException("felhasználó lekérdezésekor hiba lépett fel", e);
        }
        return null;
    }
}
