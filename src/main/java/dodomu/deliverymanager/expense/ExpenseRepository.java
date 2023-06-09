package dodomu.deliverymanager.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query("SELECT e FROM Expense e WHERE e.date BETWEEN :startDate AND :endDate")
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
