package dodomu.deliverymanager.price;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/price")
@Controller
public class PriceController {
    private final PriceService priceService;

    @GetMapping("/list")
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("price/list");
        result.addObject("priceList", priceService.getAll());
        return result;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("price", new Price());
        return "price/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Price price) {
        priceService.addOrUpdate(price);
        return "redirect:/price/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView result = new ModelAndView("price/edit");
        result.addObject("price", priceService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Price price) {
        priceService.addOrUpdate(price);
        return "redirect:/price/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        priceService.deleteById(id);
        return "redirect:/price/list";
    }
}
