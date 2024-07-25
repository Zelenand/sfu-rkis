package pr7_rest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pr7_rest.model.Bag;
import pr7_rest.model.BagService;

import java.util.List;

@Controller
@RequestMapping("/bags")
public class ControllerBag {

    private final BagService bagService;

    @Autowired
    public ControllerBag(BagService bagService) {
        this.bagService = bagService;
    }

    @GetMapping(value="", headers = {"Accept=text/html"})
    public String main(@RequestParam(name = "price", required = false) Float price, Model model) {
        model.addAttribute("minPrice", price);
        if (price != null) {
            model.addAttribute("bags", bagService.filterByCost(price));
        } else {
            model.addAttribute("bags", bagService.displayAllBags());
        }
        return "bags/main";
    }

    @ResponseBody
    @GetMapping(value="", headers = {"Accept=application/json"})
    public List<Bag> main(@RequestParam(name = "price", required = false) Float price) {
        List<Bag> bags;
        if (price != null) {
            bags = bagService.filterByCost(price);
        } else {
            bags = bagService.displayAllBags();
        }
        return bags;
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

    @PostMapping(value="/edit/{id}", headers = {"Accept=text/html"})
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("bag", bagService.findById(id));
        return "bags/edit";
    }

    @PutMapping(value="/{id}", headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public void edit(@PathVariable("id") int id, @RequestBody Bag bag) {
        bagService.update(id, bag);
    }

    @PostMapping(value="/add", headers = {"Accept=text/html"})
    public String addBag(@ModelAttribute("bag") Bag bag, Model model) {
        return "bags/add";
    }

    @PostMapping(value="", headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public void addBag(@RequestBody Bag bag) {
        bagService.save(bag);
    }

    @PostMapping(value="/add-test-data", headers = {"Accept=text/html"})
    public String fillExample() {
        bagService.addTest();
        return "redirect:/bags";
    }

    //@PostMapping("/delete/{id}")
    //@DeleteMapping(value = "/delete/{id}", headers = {"Accept=text/html"})
    @PostMapping(value = "/delete/{id}", headers = {"Accept=text/html"})
    public String delete(@PathVariable("id") int id, Model model) {
        bagService.delete(id);
        return "redirect:/bags";
    }

    @DeleteMapping(value="/{id}", headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") int id) {
        bagService.delete(id);
    }

    @ResponseBody
    @GetMapping(value="/last_id", headers = {"Accept=application/json"})
    public Integer getLastId() {
        System.out.println("adadasda");
        return bagService.getLastId();
    }

    @ResponseBody
    @GetMapping(value="/{id}", headers = {"Accept=application/json"})
    public Bag getBag(@PathVariable("id") int id) {
        return bagService.findById(id);
    }

    @GetMapping(value="/{id}", headers = {"Accept=text/html"})
    @ResponseStatus(HttpStatus.OK)
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
        return "redirect:/bags";
    }
}
