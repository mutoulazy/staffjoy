package std.staffjoy.account.service;

import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import std.staffjoy.account.props.AppProps;
import std.staffjoy.account.repo.AccountRepo;
import std.staffjoy.account.repo.AccountSecretRepo;
import std.staffjoy.account.service.helper.ServiceHelper;
import std.staffjoy.common.env.EnvConfig;
import std.staffjoy.mail.client.MailClient;

@Service
@RequiredArgsConstructor
public class AccountService {
  static final ILogger logger = SLoggerFactory.getLogger(AccountService.class);

  private final AccountRepo accountRepo;

  private final AccountSecretRepo accountSecretRepo;

  private final AppProps appProps;

  private final EnvConfig envConfig;

  private final MailClient mailClient;

  private final ServiceHelper serviceHelper;

  private final PasswordEncoder passwordEncoder;

  private final ModelMapper modelMapper;

  @PersistenceContext
  private EntityManager entityManager;
}
