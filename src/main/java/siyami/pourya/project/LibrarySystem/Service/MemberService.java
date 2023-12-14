package siyami.pourya.project.LibrarySystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyami.pourya.project.LibrarySystem.Model.Member;
import siyami.pourya.project.LibrarySystem.Repositories.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member save(Member member) {
        if (member.getNationalCode() == null) {
            throw new RuntimeException("کد ملی حتما باید وارد شود");
        }
        return memberRepository.save(member);
    }

    public List<Member> findByFirstName(String title) {
        return memberRepository.findByFirstName(title);
    }

    public List<Member> findByLastName(String name) {
        return memberRepository.findByLastName(name);
    }

    public List<Member> findByRegisterDate(LocalDate date) {
        return memberRepository.findByRegisterDate(date);
    }
}
