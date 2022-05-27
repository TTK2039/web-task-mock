package entity;

import java.security.Timestamp;

public class Categories {
	private int id;
    private String name;
	private Timestamp created_at;
    private Timestamp updated_at;
    
    public Categories(int id, String name, Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
    
	public Categories(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Categories(String name) {
		super();
		this.name = name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

