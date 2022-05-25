package entity;

import java.security.Timestamp;

public class Categories {
	private int id;
    private String category_name;
	private Timestamp created_at;
    private Timestamp updated_at;
    
    public Categories(int id, String category_name, Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.category_name = category_name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
    
	public Categories(int id, String category_name) {
		super();
		this.id = id;
		this.category_name = category_name;
	}

	public Categories(String category_name) {
		super();
		this.category_name = category_name;
	}

	public Categories() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getcategory_name() {
		return category_name;
	}
	public void setcategory_name(String category_name) {
		this.category_name = category_name;
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
}

