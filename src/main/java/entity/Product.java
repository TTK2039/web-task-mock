package entity;

import java.sql.Timestamp;

public class Product {

    private int id;
    private String product_id;
    private int category_id;
    private String name;
    private int price;
    private String image_path;
    private String description;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    private Categories category_name;
    
    public Product() {
    }
    
    public Product(int id, String name, int price, String category_name) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category_name = new Categories(category_name);
	}

	public Product(int id, String product_id, String name, int price, String category_name, Timestamp created) {
		this.id = id;
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.category_name = new Categories(category_name);
		this.created_at = created;
	}
	
	
	
	public Product(int id, String product_id, String name, int price, int category_id, String description, Timestamp updated_time) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.category_id = category_id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.updated_at = updated_time;
	}

	public Product(int id, String product_id, String name, int price, String category_name, String description) {
		this.id = id;
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.category_name = new Categories(category_name);
		this.description = description;
	}
	
	public Product(int id, String product_id, String name, int price, String category_name, String description, Timestamp timestamp) {
		this.id = id;
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.category_name = new Categories(category_name);
		this.description = description;
		this.updated_at = timestamp;
	}
    

	public Product(String product_id, String name, int price, int category_id,  String description) {
		super();
		this.product_id = product_id;
		this.category_id = category_id;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public Product(String product_id, String name, int price, int roleId, String description, Timestamp created_at, Timestamp updated_at) {
		this.product_id = product_id;
		this.category_id = roleId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}


	public Categories getCategory_name() {
		return category_name;
	}


	public void setCategory_name(Categories category_name) {
		this.category_name = category_name;
	}

	
}
