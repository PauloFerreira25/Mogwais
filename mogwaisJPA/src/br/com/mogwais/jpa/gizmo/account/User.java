package br.com.mogwais.jpa.gizmo.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -1965223765862567357L;

	// Private ID
	@Id
	private Long userId;

	// Public ID
	@Column(unique = true)
	private String hashUser;

	// Security
	@Column
	private String salt;

	// Auth
	
	@Column(unique = true)
	private String email;

	@Column
	private String password;

	// User Information
	@NotNull()
	@Column
	private String fullName;

	@Column
	@Temporal(TemporalType.DATE)
	private Date birthday;
}
