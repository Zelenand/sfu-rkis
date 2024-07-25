package pr5.model;



import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Представление таблицы users
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {

  /**
   * Поле id таблицы users
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  /**
   * Поле username таблицы roles
   */
  @NonNull
  @NotEmpty(message = "Username should not be empty")
  private String username;

  /**
   * Поле password таблицы roles
   */
  @NonNull
  @NotEmpty(message = "Password should not be empty")
  private String password;

  /**
   * Список ролей
   */
  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Role> roles;

  /**
   * Получение роли
   * @return Роль
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
  }

  /**
   * Проверка срока действия учетной записи
   * @return true/false
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Является ли учётная запись заблокированной
   * @return true/false
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Проверка срока действия реквезитов учетной записи
   * @return true/false
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Включена ли учётная запись
   * @return true/false
   */
  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }
}
