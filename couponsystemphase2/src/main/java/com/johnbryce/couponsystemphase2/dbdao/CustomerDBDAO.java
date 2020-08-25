package com.johnbryce.couponsystemphase2.dbdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.johnbryce.couponsystemphase2.beans.Customer;
import com.johnbryce.couponsystemphase2.repo.CustomerRepository;

@Repository
public class CustomerDBDAO {

	@Autowired
	private CustomerRepository customerRepository;

	public boolean isCustomerExist(int customerID) {
		return customerRepository.existsById(customerID);
	}

	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void updateCustomer(Customer customer) {
		customerRepository.saveAndFlush(customer);
	}

	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}

	public void deleteCustomerById(int id) {
		customerRepository.deleteById(id);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getOneCustomer(int customerID) {
		return customerRepository.getOne(customerID);
	}

	public int getCustomerID(String email) {
		return customerRepository.findByEmail(email).getId();
	}
}
