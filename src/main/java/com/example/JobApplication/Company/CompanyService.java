package com.example.JobApplication.Company;

import java.util.List;

public interface CompanyService {

    boolean updatedata(Long id, Company updatecompany);

    List<Company>   getAll();

    void createCompany(Company company);

    Company getCompanyById(Long id);

    boolean removeCompany(Long id);
}
