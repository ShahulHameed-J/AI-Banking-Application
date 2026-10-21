package Banking_app.Banking_backend.dto;

public class AuthResponse {
	private Long userId;
	private String name;
	private String email;
	private String token;
	
	// Constructor
	public AuthResponse(Long userId, String name, String email, String token) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.token = token;
	}
	
	// Getters (no need of setters because during respond there is no need to setting a value)
	public Long getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}