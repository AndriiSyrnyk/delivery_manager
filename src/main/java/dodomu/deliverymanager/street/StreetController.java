package dodomu.deliverymanager.street;

import dodomu.deliverymanager.locality.Locality;
import dodomu.deliverymanager.locality.LocalityService;
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
@RequestMapping("/street")
@Controller
public class StreetController {
    private final StreetService streetService;
    private final LocalityService localityService;

    @GetMapping("/list")
    public ModelAndView getAllByLocality(@RequestParam(name = "localityId", required = false) Integer localityId) {
        ModelAndView result = new ModelAndView("street/list");
        final List<Locality> allLocalities = localityService.getAll();
        List<Street> allStreets = Collections.emptyList();
        Integer selectedLocalityId = 0;

        if (localityId == null) {
            if (allLocalities.size() > 0) {
                selectedLocalityId = allLocalities.get(0).getId();
            }
        }
        else {
            selectedLocalityId = localityId;
        }

        if (selectedLocalityId != 0) {
            allStreets = localityService.getById(selectedLocalityId).getStreets();
            Collections.sort(allStreets, Comparator.comparing(Street::getName));
        }

        result.addObject("streetList", allStreets);
        result.addObject("localityList", allLocalities);
        result.addObject("selectedLocalityId", selectedLocalityId);

        return result;
    }

    @GetMapping("/add")
    public String add(Model model, @RequestParam("localityId") Integer localityId) {
        final Street street = new Street();
        street.setLocality(localityService.getById(localityId));
        model.addAttribute("street", street);
        model.addAttribute("selectedLocalityId", localityId);
        return "street/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Street street, @RequestParam("localityId") Integer localityId) {
        street.setLocality(localityService.getById(localityId));
        streetService.addOrUpdate(street);
        return "redirect:/street/list?localityId=" + localityId;
    }

    @GetMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView result = new ModelAndView("street/edit");
        result.addObject("street", streetService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Street street, @RequestParam("localityId") Integer localityId) {
        street.setLocality(localityService.getById(localityId));
        streetService.addOrUpdate(street);
        return "redirect:/street/list?localityId=" + localityId;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id, @RequestParam("localityId") Integer localityId) {
        streetService.deleteById(id);
        return "redirect:/street/list?localityId=" + localityId;
    }
}
