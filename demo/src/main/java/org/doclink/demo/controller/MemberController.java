package org.doclink.demo.controller;


import org.doclink.demo.service.PractitionerService;


import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.doclink.demo.model.Member;
import org.doclink.demo.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    
    @Autowired
    private MemberService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveMember(@RequestBody Member member) {
        String message = service.saveMember(member);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllMembers() {
        return new ResponseEntity<>(service.getAllMembers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMember(@PathVariable UUID id) {
        Optional<Member> member = service.getMember(id);
        if (member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The member with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMember(@PathVariable UUID id, @RequestBody Member member) {
        String message = service.updateMember(id, member);
        if (message.equals("Member updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable UUID id) {
        String message = service.deleteMember(id);
        if (message.equals("Member deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
