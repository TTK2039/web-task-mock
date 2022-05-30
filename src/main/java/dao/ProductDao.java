package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Categories;
import entity.Product;

public class ProductDao {

	//    private static final String SQL_SELECT_PRODUCTID = "SELECT * FROM products WHERE product_id = ?";
	//    private static final String SQL_SELECT_PRODUCTNAME = "SELECT * FROM products WHERE product_name = ?";
	//    private static final String SQL_SELECT_PRODUCTIDNAME ="SELECT * FROM products WHERE product_id = ? AND product_name = ?";
	//    private static final String SQL_FIND_ALL = "SELECT * FROM products ORDER BY product_id";
	//    private static final String SQL_SUM_PRICE = "SELECT sum(price)  FROM products";
	//    private static final String SQL_INSERT = "INSERT INTO products (product_name, price) VALUES(?, ?)";
	//    private static final String SQL_DELETE_FROM_ID = "DELETE FROM products WHERE product_id = ?";
	//    private static final String SQL_DELETE_FROM_NAME = "DELETE FROM products WHERE product_name=?";

	private static final String SQL_ALL_SELECT_TABLE = "SELECT p.id id, p.product_id 商品ID, p.name 商品名, p.price 単価, c.name カテゴリ, p.created_at FROM products p JOIN categories c ON p.category_id = c.id";
	private static final String SQL_SELECT_BY_KEYWORD ="SELECT p.id id, p.product_id 商品ID, p.name 商品名, p.price 単価, c.name カテゴリ, p.created_at  FROM products p JOIN categories c ON p.category_id = c.id WHERE p.name LIKE ? OR c.name LIKE ?";
	private static final String SQL_INSERT = "INSERT INTO products (product_id, name, price, category_id, description, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_BY_PDID = "DELETE FROM products WHERE product_id = ?";
	private static final String SQL_SELECT_BY_ID ="SELECT p.id id, p.product_id 商品ID, p.name 商品名, p.price 単価, c.name カテゴリ, p.description 詳細 FROM products p JOIN categories c ON p.category_id = c.id WHERE p.id = ?";
	private static final String SQL_UPDATE_BY_PDID ="UPDATE Products SET product_id = ?, name = ?, price = ?, category_id = ?, description = ?, updated_at = ? WHERE id = ?";
	private Connection connection;
	public ProductDao(Connection connection) {
		this.connection = connection;
	}

	public List<Categories> selectCategory() {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_BY_PDID)) {
			List<Categories> list = new ArrayList<>();

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Categories cd = new Categories(rs.getInt("id"),rs.getString("name"));
				list.add(cd);
			}	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	//UPDATE Products SET product_id = ?, name = ?, price = ?, category_id = ?, description = ? WHERE product_id = ?
	public int updateById(Product pd) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_BY_PDID)) {
			stmt.setString(1, pd.getProduct_id());
			stmt.setString(2, pd.getName());
			stmt.setInt(3, pd.getPrice());
			stmt.setInt(4, pd.getCategory_id());
			stmt.setString(5, pd.getDescription());
			stmt.setTimestamp(6, pd.getUpdated_at());
			stmt.setInt(7, pd.getId()); 
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	//UPDATE Products SET product_id = ?, name = ?, price = ?, category_id = ?, description = ? WHERE product_id = ?  
	public Product findById(int id) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return new Product(rs.getInt("id"),rs.getString("商品id"), rs.getString("商品名"), rs.getInt("単価"), rs.getString("カテゴリ"), rs.getString("詳細"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int deleteByPdId(String pdId) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_BY_PDID)) {
			stmt.setString(1, pdId);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int productInsert(Product pd) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
			stmt.setString(1, pd.getProduct_id());
			stmt.setString(2, pd.getName());
			stmt.setInt(3, pd.getPrice());
			stmt.setInt(4, pd.getCategory_id());
			stmt.setString(5, pd.getDescription());
			stmt.setTimestamp(6, pd.getCreated_at());
			stmt.setTimestamp(7, pd.getUpdated_at());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}


	public List<Product> allProducts( ) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_ALL_SELECT_TABLE)) {
			List<Product> list = new ArrayList<>();

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Product pd = new Product(rs.getInt("id"),rs.getString("商品id"), rs.getString("商品名"), rs.getInt("単価"),rs.getString("カテゴリ"), rs.getTimestamp("created_at"));
				list.add(pd);
			}	
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> selectByKeyword(String keyword) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_KEYWORD)) {
			List<Product> list = new ArrayList<>();

			stmt.setString(1, "%"+ keyword + "%");
			stmt.setString(2, "%"+ keyword + "%");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Product pd = new Product(rs.getInt("id"),rs.getString("商品id"), rs.getString("商品名"), rs.getInt("単価"), rs.getString("カテゴリ"), rs.getTimestamp("created_at"));
				list.add(pd);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

