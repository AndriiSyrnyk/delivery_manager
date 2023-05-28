package dodomu.deliverymanager.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/client")
@Controller
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/list")
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("client/list");
        // need to sort by deliveries count in future
        final List<Client> allClients = clientService.getAll();
        Collections.sort(allClients, Comparator.comparing(Client::getId));
        // .
        result.addObject("clientList", allClients);
        return result;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("client", new Client());
        return "client/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Client client) {
        clientService.addOrUpdate(client);
        return "redirect:/client/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView result = new ModelAndView("client/edit");
        result.addObject("client", clientService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Client client) {
        clientService.addOrUpdate(client);
        return "redirect:/client/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        clientService.deleteById(id);
        return "redirect:/client/list";
    }
}
