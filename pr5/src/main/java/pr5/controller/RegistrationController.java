package pr5.controller;

import pr5.model.User;
import pr5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Контроллер регистрации
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

  @Autowired
  private UserService userService;

  /**
   * Вывод страницы регистрации
   * @param model Модель
   * @return Страница регистрации
   */
  @GetMapping()
  public String registration(Model model) {
    model.addAttribute("userForm", new User());

    return "registration";
  }

  /**
   *  Добавление пользователя
   * @param userForm Форма пользователя
   * @param bindingResult Ошибки валидации
   * @param model Модель
   * @return Страница регистрации или переход на страницу логина
   */
  @PostMapping()
  public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      return "registration";
    }

    if (!userService.saveUser(userForm)){
      model.addAttribute("usernameError", "A user with the same name already exists");
      return "registration";
    }

    return "redirect:/login";
  }
}
