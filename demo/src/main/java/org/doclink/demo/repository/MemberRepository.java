package org.doclink.demo.repository;

import java.util.List;
import java.util.UUID;

import org.doclink.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    @Query("SELECT DISTINCT b.member FROM Booking b WHERE b.practitioner.id = :practitionerId")
    List<Member> findMembersOfPractitioner(@Param("practitionerId") UUID practitionerId);

    List<Member> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

}
