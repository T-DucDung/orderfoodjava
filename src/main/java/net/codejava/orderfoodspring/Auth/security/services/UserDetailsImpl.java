package net.codejava.orderfoodspring.Auth.security.services;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.codejava.orderfoodspring.Auth.models.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails{
    private static final long serialVersionUID = 1L;

	private Integer mand;

	private String tendn;

	private String email;

	@JsonIgnore
	private String matkhau;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Integer id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.mand = id;
		this.tendn = username;
		this.email = email;
		this.matkhau = password;
		this.authorities = authorities;
    }
    public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
    }
    public Integer getId() {
		return mand;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return matkhau;
	}

	@Override
	public String getUsername() {
		return tendn;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
    @Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(mand, user.mand);
	}
}
