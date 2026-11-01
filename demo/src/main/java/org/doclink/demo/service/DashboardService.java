package org.doclink.demo.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.doclink.demo.model.BookingStatus;
import org.doclink.demo.repository.BookingRepository;
import org.doclink.demo.repository.ConsultationNoteRepository;
import org.doclink.demo.repository.MemberRepository;
import org.doclink.demo.repository.PractitionerRepository;

@Service
public class DashboardService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private ConsultationRoomService consultationRoomService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ConsultationNoteRepository consultationNoteRepository;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("members", memberRepository.count());
        summary.put("practitioners", practitionerRepository.count());
        summary.put("freeRooms", (long) consultationRoomService.findFreeRooms().size());
        summary.put("bookingsToday", bookingRepository.countByBookingDate(LocalDate.now()));
        summary.put("requested", bookingRepository.countByStatus(BookingStatus.REQUESTED));
        summary.put("consultationNotes", consultationNoteRepository.count());
        return summary;
    }
}
