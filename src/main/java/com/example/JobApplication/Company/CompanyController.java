package com.example.JobApplication.Company;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController// this is imp for java obj serialization it to JSON(to know we give java obj and with help of Jpa it get in db and when we fetch it is fetched as java obj which then serialize to JSON)
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }


    @GetMapping
    public  ResponseEntity<List<Company>> findAllCompany(){
        List<Company> companyList = companyService.getAll();
        if(companyList !=null){
            return new ResponseEntity<>(companyList ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Company> getById(@PathVariable Long id){
        Company company= companyService.getCompanyById(id);
        if(company !=null){
            return new ResponseEntity<>(company ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<String> postCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("new Company Posted successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id , @RequestBody Company updatecompany){
        boolean update = companyService.updatedata(id,updatecompany);
        if(update){
            return new ResponseEntity<>("Company updated",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Company not found to update ",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean deleted = companyService.removeCompany(id);
        if(deleted){
            return new ResponseEntity<>("Company deleted",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Company Not found",HttpStatus.NOT_FOUND);
    }
}
