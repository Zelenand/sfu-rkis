package pr6.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pr6.model.Bag;
import pr6.model.BagService;

@Controller
@RequestMapping("/bags")
public class ControllerBag {

    private final BagService bagService;

    @Autowired
    public ControllerBag(BagService bagService) {
        this.bagService = bagService;
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
        return "redirect:/bags";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("bag", bagService.findById(id));
        return "bags/edit";
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
        return "redirect:/bags";
    }

    @GetMapping("/add")
    public String addBad(@ModelAttribute("bag") Bag bag) {
        return "bags/add";
    }

    @PostMapping("/add-test-data")
    public String fillExample() {
        bagService.addTest();
        return "redirect:/bags";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        bagService.delete(id);
        return "redirect:/bags";
    }
}
