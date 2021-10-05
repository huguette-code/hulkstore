/**
 * 
 */
package com.example.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * @author hcastillo
 *
 */
@Entity
@Table(name="hs_role")
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1916473030169030338L;

	@Id
	@Column(name="id_rol")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name",nullable = false, length = 35)
	private String name;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
	private Set<User> users;

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
	 *
	 * @param users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 *
	 * @return
	 */
	public Set<User> getUsers() {
		return users;
	}

}