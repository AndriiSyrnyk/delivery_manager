package dodomu.deliverymanager.expense;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    public Expense addOrUpdate(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteById(Integer id) {
        expenseRepository.deleteById(id);
    }

    public Expense getById(Integer id) {
        return expenseRepository.findById(id).get();
    }
}
