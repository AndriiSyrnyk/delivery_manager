package dodomu.deliverymanager.salary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SalaryService {
    private final SalaryRepository salaryRepository;

    public List<Salary> getAll() {
        return salaryRepository.findAll();
    }

    public Salary addOrUpdate(Salary salary) {
        return salaryRepository.save(salary);
    }

    public void deleteById(Integer id) {
        salaryRepository.deleteById(id);
    }

    public Salary getById(Integer id) {
        return salaryRepository.findById(id).get();
    }
}
