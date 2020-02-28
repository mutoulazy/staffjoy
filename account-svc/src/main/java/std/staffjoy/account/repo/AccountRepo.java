package std.staffjoy.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import std.staffjoy.account.model.Account;

public interface AccountRepo extends JpaRepository<Account, String> {

  Account findAccountById(String id);

  Account findAccountByEmail(String emil);

  Account findAccountByPhoneNumber(String phoneNumber);

  @Modifying(clearAutomatically = true)
  @Query("update Account account set account.email = :email, account.confirmedAndActive = true where account.id = :id")
  @Transactional
  int updateEmailAndActivateById(@Param("email") String email, @Param("id") String id);
}
