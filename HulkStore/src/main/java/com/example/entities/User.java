/**
 * 
 */
package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author hcastillo
 *
 */
@Entity
@Table(name = "hs_user")
public class User implements Serializable {

	private static final long serialVersionUID = -4020533874170227294L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 35)
	private String name;

	@Column(name = "surname", nullable = false, length = 35)
	private String surname;

	@Column(name = "Address", nullable = false, length = 100)
	private String address;

	@Column(name = "email", nullable = false, length = 100, unique = true)
	private String email;

	@Column(name = "username", nullable = false, length = 100, unique = true)
	private String username;

	@Column(name = "password", nullable = false, length = 128)
	private String password;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_register")
	private Date dateRegister;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
	private Collection<Role> roles;

	@Column(name = "state", nullable = false)
	private int state;


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the dateRegister
	 */
	public Date getDateRegister() {
		return dateRegister;
	}

	/**
	 * @param dateRegister the dateRegister to set
	 */
	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 *
	 * @return
	 */
	public Collection<Role> getRoles() {
		return roles;
	}

	/**
	 *
	 * @param roles
	 */
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	/**
	 *
	 * @return
	 */
	public int getState() {
		return state;
	}

	/**
	 *
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}
}

