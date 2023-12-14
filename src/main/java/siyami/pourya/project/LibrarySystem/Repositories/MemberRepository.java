package siyami.pourya.project.LibrarySystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import siyami.pourya.project.LibrarySystem.Model.Member;

import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByFirstName(String title);

    List<Member> findByLastName(String name);

    List<Member> findByRegisterDate(LocalDate date);
}
