package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Categories;

public class CategoriesDao {

	private static final String SQL_SELECT_CATEGORY ="SELECT id, name from categories";

	private Connection connection;
	public CategoriesDao(Connection connection) {
		this.connection = connection;
	}

	public List<Categories> selectCategory() {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_CATEGORY)) {
			List<Categories> list = new ArrayList<>();

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Categories cd = new Categories(rs.getInt("id"),rs.getString("name"));
				list.add(cd);
			}	
			return list;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}