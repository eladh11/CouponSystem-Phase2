package com.johnbryce.couponsystemphase2.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String first;

	@Column(nullable = false)
	private String last;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Coupon> coupons;
}
