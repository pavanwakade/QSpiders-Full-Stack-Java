package com.qsp.springbootCompany.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qsp.springbootCompany.dto.Company;
import com.qsp.springbootCompany.repository.CompanyRepository;

@Component
public class CompanyDao {
	
    @Autowired
    private CompanyRepository repository;

    public Company saveCompany(Company company) {
        return repository.save(company);
    }
    
    public Company updateCompany(Company company) {
        return repository.save(company);
    }
    
    public void deleteCompany(Company company) {
         repository.delete(company);
    }
    
    
   public Optional<Company> findCompanyById(int id){
	   return repository.findById(id);
   }
   
   public List<Company> findAll() {
	   return repository.findAll();
   }
   
   
   public List<Company>findByLocation(String location){
	   return repository.findByLocation(location);
   }
}
