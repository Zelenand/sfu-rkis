package pr5.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pr5.model.Bag;
import pr5.repository.BagDao;

@Controller
@RequestMapping("/")
public class ControllerBag {

  private final BagDao bagDao;

  @Autowired
  public ControllerBag(BagDao bagDao) {
    this.bagDao = bagDao;
  }


  @GetMapping()
  public String main(@RequestParam(name = "price", required = false) Integer price, Model model) {
    model.addAttribute("maxPrice", price);
    if (price != null) {
      model.addAttribute("bags", bagDao.findAllWithCostLower(price));
    } else {
      model.addAttribute("bags", bagDao.findAll());
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

    bagDao.insert(bag);
    return "redirect:/bags";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") int id, Model model) {
    model.addAttribute("bag", bagDao.findById(id));
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
    System.out.println(bag);
    bagDao.update(id, bag);
    return "redirect:/bags";
  }

  @GetMapping("/add")
  public String addBag(@ModelAttribute("bag") Bag bag) {
    return "bags/add";
  }
  @PostMapping("/add-test-data")
  public String fillExample() {
    bagDao.insert(new Bag("AA", "aa", "aaaa", 100, 200.0));
    bagDao.insert(new Bag("BB", "bb", "bbbb", 200, 250.0));
    bagDao.insert(new Bag("CC", "cc", "cccc", 300, 300.0));
    return "redirect:/bags";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable("id") int id) {
    bagDao.remove(id);
    return "redirect:/bags";
  }
}