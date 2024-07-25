package pr8.controller;

import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pr8.model.MyMessage;
import pr8.model.Bag;
import pr8.model.BagService;

@Controller
@RequestMapping("/bags")
public class ControllerBag {


    private final RabbitTemplate rabbitTemplate;

    private final BagService bagService;


    @Autowired
    public ControllerBag(BagService bagService, RabbitTemplate rabbitTemplate) {
        this.bagService = bagService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping()
    public String main(@RequestParam(name = "price", required = false) Float price, Model model) {
        model.addAttribute("minPrice", price);
        if (price != null) {
            model.addAttribute("bags", bagService.filterByCost(price));
        } else {
            model.addAttribute("bags", bagService.displayAllBags());
        }
        return "bags/main";
    }

    @PostMapping()
    public String create(
            @ModelAttribute("bag") @Valid Bag bag,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "bags/add";
        }

        bagService.save(bag);
        rabbitTemplate.convertAndSend("bag-queue", new MyMessage("add", "Was added bag:", bag));
        return "redirect:/bags";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("bag", bagService.findById(id));
        return "bags/edit";
    }

    @GetMapping("/add")
    public String addBag(@ModelAttribute("bag") Bag bag, Model model) {
        return "bags/add";
    }

    @PostMapping("/add-test-data")
    public String fillExample() {
        bagService.addTest();
        return "redirect:/bags";
    }

    //@PostMapping("/delete/{id}")
    //@DeleteMapping(value = "/delete/{id}", headers = {"Accept=text/html"})
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        bagService.delete(id);
        rabbitTemplate.convertAndSend("bag-queue", new MyMessage("delete", String.format("Bag with id = %d was deleted", id)));
        return "redirect:/bags";
    }


    @PostMapping("/buy/{id}")
    public String buy(@PathVariable("id") int id, Model model) {
        Bag bag = bagService.findById(id);
        bagService.delete(id);
        rabbitTemplate.convertAndSend("bag-queue", new MyMessage("buy", "Request to buy a bag (deleted from database), bag:", bag));
        return "redirect:/bags";
    }

    @GetMapping("/last_id")
    public Integer getLastId() {
        return bagService.getLastId();
    }


    @GetMapping("/{id}")
    public String getBag(@PathVariable("id") int id, Model model) {
        if (bagService.findById(id) != null) {
            System.out.println(bagService.findById(id));
            model.addAttribute("bags", bagService.findById(id));
        }
        return "bags/main";
    }

    @PostMapping("/{id}")
    public String update(
            @ModelAttribute("bags") @Valid Bag bag,
            BindingResult bindingResult,
            @PathVariable("id") int id
    ) {
        if (bindingResult.hasErrors()) {
            return "bags/edit";
        }
        bagService.update(id, bag);
        rabbitTemplate.convertAndSend("bag-queue", new MyMessage("edit", String.format("Bag with id = %d was updated to:", id), bag));
        return "redirect:/bags";
    }
}
