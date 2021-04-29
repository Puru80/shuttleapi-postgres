package com.example.shuttleapi.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TicketRepository extends JpaRepository<Ticket, Long>
{
    @Override
    Optional<Ticket> findById(Long id);

    Ticket findTicketById(Long id);

//    void deleteById(Long id);

    @Transactional
    @Query("update Ticket t "
            + "set t.paymentStatus = TRUE where t.id=?1")
    void updatePaymentStatus(Long id);

    @Transactional
    @Query("select t from Ticket t where t.userEmail = ?1")
    List<Ticket> getTicketsByEmail(String email);
}
