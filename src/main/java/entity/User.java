package entity;

public class User {
	private int userId;
    private String loginId;
    private String password;
    private String name;
    private int role;
    private int created_at;
    private int updated_at;
    
    public User() {
    }
    
    public User(String loginId, String password) {
    	this.loginId = loginId;
    	this.password = password;
    }
    
    public User(int userId, String loginId, String password) {
        this.userId = userId;
        this.loginId = loginId;
        this.password = password;
    }
    
    public User(int id, String logId, String password, String name, int role, int cr, int up) {
    	this.userId = id;
    	this.loginId = logId;
    	this.password = password;
    	this.name = name;
    	this.role = role;
    	this.created_at = cr;
    	this.updated_at = up;
    }
//    public User(ResultSet rs) {
//		try {
//			this.userId = rs.getInt("userId");
//			this.loginId = rs.getString("loginId");
//			this.password = rs.getString("password");
//			this.name = rs.getString("name");
//			this.role = rs.getInt("role");
//			this.created_at = rs.getInt("created_at");
//			this.updated_at = rs.getInt("updated_at");
//		} catch (SQLException e) {}
//	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return name;
	}

	public void setUserName(String name) {
		this.name = name;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getCreated_at() {
		return created_at;
	}

	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}

	public int getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(int updated_at) {
		this.updated_at = updated_at;
	}


}

