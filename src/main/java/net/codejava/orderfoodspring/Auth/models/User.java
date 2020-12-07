package net.codejava.orderfoodspring.Auth.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User{
    private Integer id;

    @NotBlank
    private String username;
    
    @NotBlank
	@Size(max = 255)
    private String password;

    @NotBlank
	@Size(max = 250)
	@Email
    private String email;

    // @NotBlank
    // private String gt;

    // @NotBlank
    // private String sdt;

    // @NotBlank
    // private Date ngaytao;

    // @NotBlank
    // private boolean trangthai;
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    

    // public String getGt() {
    //     return this.gt;
    // }

    // public void setGt(String gt) {
    //     this.gt = gt;
    // }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // public String getSdt() {
    //     return sdt;
    // }

    // public void setSdt(String sdt) {
    //     this.sdt = sdt;
    // }

    // public Date getNgaytao() {
    //     return ngaytao;
    // }

    // public void setNgaytao(Date ngaytao) {
    //     this.ngaytao = ngaytao;
    // }


    // public boolean isTrangthai() {
    //     return this.trangthai;
    // }

    // public boolean getTrangthai() {
    //     return this.trangthai;
    // }

    // public void setTrangthai(boolean trangthai) {
    //     this.trangthai = trangthai;
    // }

    @ManyToMany
    public Set<Role> getRoles() {
		return roles;
	}
    @ManyToMany
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
    }

    public User(String username, String matkhau, String email){
        this.username = username;
        this.password = matkhau;
        this.email = email;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", id=" + id + ", password=" + password + ", roles=" + roles + ", username="
                + username + "]";
    }

    
}