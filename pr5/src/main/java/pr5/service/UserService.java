package pr5.service;

import pr5.model.Role;
import pr5.model.User;
import pr5.repository.RoleRepository;
import pr5.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с моделью User
 */
@Service
public class UserService implements UserDetailsService {

  /**
   * Репозиторий пользователя
   */
  @Autowired
  UserRepository userRepository;

  /**
   * Репозиторий роли
   */
  @Autowired
  RoleRepository roleRepository;

  /**
   * Интерфейс преобразования пароля
   */
  @Autowired
  PasswordEncoder passwordEncoder;

  /**
   * Загрузка пользователя по имени
   * @param username имя
   * @return Пользователь
   * @throws UsernameNotFoundException Исключение о том, что пользователь не найден
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return user;
  }

  /**
   * Нахождение пользователя по id
   * @param userId id пользователя
   * @return Пользователь
   */
  public User findUserById(Integer userId) {
    Optional<User> userFromDb = userRepository.findById(userId);
    return userFromDb.orElse(new User());
  }

  /**
   * Вывод всех пользователей
   * @return Все пользователи
   */
  public List<User> allUsers() {
    return userRepository.findAll();
  }

  /**
   * Сохранение пользователя
   * @param user Пользователь
   * @return Добавлен пользователь или нет
   */
  public boolean saveUser(User user) {
    User userFromDB = userRepository.findByUsername(user.getUsername());

    if (userFromDB != null) {
      return false;
    }
    user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return true;
  }

  /**
   * Удаление пользователя по id
   * @param userId id пользователя
   * @return Удален пользователь или нет
   */
  public boolean deleteUser(Integer userId) {
    if (userRepository.findById(userId).isPresent()) {
      userRepository.deleteById(userId);
      return true;
    }
    return false;
  }
}

