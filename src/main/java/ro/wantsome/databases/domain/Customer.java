package ro.wantsome.databases.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_table")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Embedded
	private Address address;

	@Transient
	private String CNP;

	@Version
	private Long version;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCNP() {
		return CNP;
	}

	public void setCNP(String CNP) {
		this.CNP = CNP;
	}

	@Override
	public String toString() {
		return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", address=" + address + ", CNP='"
					 + CNP + '\'' + ", version=" + version + '}';
	}
}
