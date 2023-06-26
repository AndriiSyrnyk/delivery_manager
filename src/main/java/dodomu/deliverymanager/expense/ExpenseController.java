package dodomu.deliverymanager.expense;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/expense")
@Controller
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping("/list")
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("expense/list");
        result.addObject("expenseList", expenseService.getAll());
        return result;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("expense", new Expense());
        return "expense/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Expense expense) {
        expenseService.addOrUpdate(expense);
        return "redirect:/expense/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView result = new ModelAndView("expense/edit");
        result.addObject("expense", expenseService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Expense expense) {
        expenseService.addOrUpdate(expense);
        return "redirect:/expense/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        expenseService.deleteById(id);
        return "redirect:/expense/list";
    }
}
