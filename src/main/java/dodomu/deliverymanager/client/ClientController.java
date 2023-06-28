package dodomu.deliverymanager.client;

import dodomu.deliverymanager.client_price.ClientPrice;
import dodomu.deliverymanager.client_price.ClientPriceId;
import dodomu.deliverymanager.client_price.ClientPriceService;
import dodomu.deliverymanager.price.Price;
import dodomu.deliverymanager.price.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/client")
@Controller
public class ClientController {
    private final ClientService clientService;

    private final PriceService priceService;

    private final ClientPriceService clientPriceService;

    private final ClientConverter clientConverter;

    @GetMapping("/list")
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("client/list");
        // need to sort by deliveries count in future
        final List<Client> allClients = clientService.getAll();
        Collections.sort(allClients, Comparator.comparing(Client::getId));
        // .
        result.addObject("clientList", clientConverter
                .convertClientListToClientDTOList(allClients));
        return result;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("priceList", priceService.getAll());
        return "client/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Client client, @ModelAttribute Price price) {
        final Client createdClient = clientService.addOrUpdate(client);
        final ClientPrice clientPrice = new ClientPrice(createdClient, price, new java.sql.Date(System.currentTimeMillis()));
        clientPriceService.addOrUpdate(clientPrice);
        return "redirect:/client/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(Integer clientId) {
        ModelAndView result = new ModelAndView("client/edit");
        final ClientDTO clientDTO  = clientConverter.convertClientToClientDTO(clientService.getById(clientId));
        result.addObject("client", clientDTO);
        result.addObject("priceList", priceService.getAll());
        result.addObject("currentPrice", clientDTO.getCurrentPrice());
        return result;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Client client, @ModelAttribute Price price, Integer clientId) {
        client.setId(clientId);
        final Date today = new Date(System.currentTimeMillis());
        final Client updatedClient = clientService.addOrUpdate(client);
        final Price oldPrice = clientPriceService.findClientPriceByClientAndDate(client, today).getPrice();

        if (!oldPrice.equals(price)) {
            final ClientPrice clientPrice = new ClientPrice(updatedClient, price, today);
            final ClientPrice todayClintPrice = clientPriceService
                    .findByClientIdAndPriceIdAndDate(clientId, oldPrice.getId(), today);

            if (todayClintPrice != null) {
                clientPriceService.deleteById(new ClientPriceId(clientId, oldPrice.getId(), today));
            }

            clientPriceService.addOrUpdate(clientPrice);
        }
        return "redirect:/client/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        clientService.deleteById(id);
        return "redirect:/client/list";
    }
}
