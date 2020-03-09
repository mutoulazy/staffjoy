package std.staffjoy.company.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import std.staffjoy.company.model.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, String> {

  List<Team> findByCompanyId(String companyId);
}
