package ua.nure.soprunov.SummaryTask.dao.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.Provides id field and get/set methods
 * to him.
 * 
 @authors Soprunov Igor
 * 
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 8466257860808346236L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
