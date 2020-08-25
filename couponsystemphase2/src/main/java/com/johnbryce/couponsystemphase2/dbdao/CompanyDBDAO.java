package com.johnbryce.couponsystemphase2.dbdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.johnbryce.couponsystemphase2.beans.Company;
import com.johnbryce.couponsystemphase2.repo.CompanyRepository;

@Repository
public class CompanyDBDAO {

	@Autowired
	private CompanyRepository companyRepository;

	public boolean isCompanyExist(int companyID) {
		return companyRepository.existsById(companyID);
	}

	public void addCompany(Company company) {
		companyRepository.save(company);
	}

	public void updateCompany(Company company) {
		companyRepository.saveAndFlush(company);
	}

	public void deleteCompany(Company company) {
		companyRepository.delete(company);
	}

	public void deleteCompanyById(int id) {
		companyRepository.deleteById(id);
	}

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	public Company getOneCompany(int companyID) {
		return companyRepository.getOne(companyID);
	}

	public int getCompanyID(String email) {
		return companyRepository.findByEmail(email).getId();
	}
}
