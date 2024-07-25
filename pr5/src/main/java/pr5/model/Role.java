package pr5.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Представление таблицы roles
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Role implements GrantedAuthority {

  /**
   * Поле id таблицы roles
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NonNull
  private Integer id;

  /**
   * Поле name таблицы roles
   */
  @NonNull
  private String name;

  /**
   * Список пользователей
   */
  @Transient
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

  /**
   * Получение роли
   * @return Роль
   */
  @Override
  public String getAuthority() {
    return getName();
  }
}
