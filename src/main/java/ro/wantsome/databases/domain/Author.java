package ro.wantsome.databases.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Author {

	@Column(name = "author_name")
	private String name;

	@Column(name = "author_age")
	private Double age;

	public Author(String name, Double age) {
		this.name = name;
		this.age = age;
	}

	public Author() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	@Override
	public String toString()
	{
		return "Author{" + "name='" + name + '\'' + ", age=" + age + '}';
	}
}
