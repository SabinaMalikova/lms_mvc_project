package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;
import peaksoft.exceptions.MyException;
import peaksoft.repository.CompanyRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Transactional
public class CompanyRepoImpl implements CompanyRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCompany(Company company) {
        try {
            if (company != null) {
                entityManager.persist(company);
            } else throw new MyException("not found");
        } catch (MyException e) {
            e.getMessage();
        }
    }

    @Override
    public Company getById(Long id) {
        return entityManager.find(Company.class, id);
    }

    @Override
    public List<Company> getAllCompany() {
        return entityManager.createQuery("select c from Company  c", Company.class).getResultList();
    }

    @Override
    public void updateById(Long id, Company newCompany) {
        Company company = getById(id);
        company.setCompanyName(newCompany.getCompanyName());
        company.setCountry(newCompany.getCountry());
        company.setAddress(newCompany.getAddress());
        company.setPhoneNumber(newCompany.getPhoneNumber());
        entityManager.merge(company);
    }

    @Override
    public void deleteCompanyById(Long id) {
        entityManager.remove(entityManager.find(Company.class, id));
    }
}
