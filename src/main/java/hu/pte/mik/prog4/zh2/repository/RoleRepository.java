package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.RoleEntity;
import hu.pte.mik.prog4.zh2.entity.UserEntity;
import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {

    public List<RoleEntity> findRolesByUserId(Long userId) {
        String sql = "SELECT r.id, r.code, r.description " +
                "FROM user_role ur " +
                "JOIN role r ON ur.role_id = r.id " +
                "WHERE ur.user_id = ?";
        List<RoleEntity> roles = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RoleEntity role = new RoleEntity();
                    role.setId(rs.getLong("id"));
                    role.setCode(rs.getString("code"));
                    role.setDescription(rs.getString("description"));
                    roles.add(role);
                }
            }
        } catch (SQLException | NamingException e) {
            throw new RuntimeException("a szerepkörök lekérdezésekor hiba történt", e);
        }
        return roles;
    }
}
