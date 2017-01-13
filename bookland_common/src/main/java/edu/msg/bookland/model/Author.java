package edu.msg.bookland.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

	@Transient
	private static final long serialVersionUID = 3106992709617480273L;

	@Column(name = "name", nullable = true, unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Author name=" + name;
	}
	
	
	

}
