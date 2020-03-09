package std.staffjoy.company.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import std.staffjoy.company.model.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, String> {

  List<Job> findJobByTeamId(String teamId);

  Job findJobById(String id);
}
