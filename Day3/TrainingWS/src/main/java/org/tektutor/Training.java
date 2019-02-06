package org.tektutor;

public class Training {

	private int id;
	private String name;
	private String duration;

	public Training() {
		this.id = 0;
		this.name = "";
		this.duration = "";
	}

	public void setId( int id ) {
		this.id = id;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public void setDuration ( String duration ) {
		this.duration = duration;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDuration() {
		return this.duration;
	}

}
