package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.FoodEntity;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository extends Repository {

    public FoodEntity save(FoodEntity food) {
        String sql = "INSERT INTO food (restaurant_name, food_name, price) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, food.getRestaurantName());
            stmt.setString(2, food.getFoodName());
            stmt.setString(3, food.getPrice());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    food.setId(rs.getLong(1));
                }
            }
            return food;
        } catch (SQLException | NamingException e) {
            throw new RuntimeException("az adatbázis művelet során hiba történt", e);
        }
    }

    public FoodEntity update(FoodEntity food) {
        String sql = "UPDATE food SET restaurant_name = ?, food_name = ?, price = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, food.getRestaurantName());
            stmt.setString(2, food.getFoodName());
            stmt.setString(3, food.getPrice());
            stmt.setLong(4, food.getId());
            stmt.executeUpdate();
            return food;
        } catch (SQLException | NamingException e) {
            throw new RuntimeException("a frissítés során hiba lépett fel", e);
        }
    }

    public FoodEntity findById(Long id) {
        String sql = "SELECT id, restaurant_name, food_name, price FROM food WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FoodEntity(
                            rs.getLong("id"),
                            rs.getString("restaurant_name"),
                            rs.getString("food_name"),
                            rs.getString("price")
                    );
                }
                return null;
            }
        } catch (SQLException | NamingException e) {
            throw new RuntimeException("a lekérdezés során hiba lepett fel", e);
        }
    }

    public List<FoodEntity> listAll() {
        String sql = "SELECT id, restaurant_name, food_name, price FROM food";
        List<FoodEntity> result = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                result.add(new FoodEntity(
                        rs.getLong("id"),
                        rs.getString("restaurant_name"),
                        rs.getString("food_name"),
                        rs.getString("price")
                ));
            }
        } catch (SQLException | NamingException e) {
            throw new RuntimeException("a listázás során hiba lépett fel", e);
        }
        return result;
    }
}
