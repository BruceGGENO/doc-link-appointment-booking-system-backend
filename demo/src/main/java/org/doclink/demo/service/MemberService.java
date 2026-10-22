package org.doclink.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.doclink.demo.model.Member;
import org.doclink.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repository;

    public String saveMember(Member member) {
        member.setId(null);
        repository.save(member);
        return "Member saved successfully";
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Optional<Member> getMember(UUID id) {
        return repository.findById(id);
    }

    public String updateMember(UUID id, Member member) {
        Optional<Member> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return "The member with that id does not exist";
        }
        Member saved = existing.get();
        saved.setFirstName(member.getFirstName());
        saved.setLastName(member.getLastName());
        saved.setMemberNumber(member.getMemberNumber());
        saved.setPhone(member.getPhone());
        saved.setEmail(member.getEmail());
        repository.save(saved);
        return "Member updated successfully";
    }

    public String deleteMember(UUID id) {
        if (!repository.existsById(id)) {
            return "The member with that id does not exist";
        }
        repository.deleteById(id);
        return "Member deleted successfully";
    }

    public List<Member> findByLastName(String lastName) {
        return repository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public List<Member> findMembersOfPractitioner(UUID practitionerId) {
        return repository.findMembersOfPractitioner(practitionerId);
    }
}
