package dodomu.deliverymanager.expense;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    public List<Expense> getExpensesByMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        return expenseRepository.findByDateBetween(startOfMonth, endOfMonth);
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
