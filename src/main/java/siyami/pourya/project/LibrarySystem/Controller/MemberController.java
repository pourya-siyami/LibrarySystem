package siyami.pourya.project.LibrarySystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siyami.pourya.project.LibrarySystem.Model.Member;
import siyami.pourya.project.LibrarySystem.Service.MemberService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/getAllMembers")
    public List<Member> getAllMembers() {
        return memberService.findAll();
    }

    @PostMapping("/addMember")
    public Member addMember(@RequestBody Member member) {
        return memberService.save(member);
    }

    @GetMapping("/getMembersByFirstName")
    public ResponseEntity<List<Member>> findByFirstName(@RequestParam String name) {
        return new ResponseEntity<>(memberService.findByFirstName(name), HttpStatus.OK);
    }

    @GetMapping("/getMembersByLastName")
    public ResponseEntity<List<Member>> findByLastName(@RequestParam String name) {
        return new ResponseEntity<>(memberService.findByLastName(name), HttpStatus.OK);
    }

    @GetMapping("/getMembersByRegisterDate")
    public ResponseEntity<List<Member>> findByRegisterDate(@RequestParam LocalDate date) {
        return new ResponseEntity<>(memberService.findByRegisterDate(date), HttpStatus.OK);
    }
}
