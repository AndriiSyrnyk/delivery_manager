package dodomu.deliverymanager.locality;

import dodomu.deliverymanager.street.Street;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/locality")
@Controller
public class LocalityController {
    private final LocalityService localityService;

    @GetMapping("/list")
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("locality/list");
        // need to sort by deliveries count in future
        final List<Locality> allLocalities = localityService.getAll();
        Collections.sort(allLocalities, Comparator.comparing(Locality::getId));
        // .
        result.addObject("localityList", allLocalities);
        return result;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("locality", new Locality());
        return "locality/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Locality locality) {
        localityService.addOrUpdate(locality);
        return "redirect:/locality/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView result = new ModelAndView("locality/edit");
        result.addObject("locality", localityService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Locality locality) {
        localityService.addOrUpdate(locality);
        return "redirect:/locality/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        localityService.deleteById(id);
        return "redirect:/locality/list";
    }
}
