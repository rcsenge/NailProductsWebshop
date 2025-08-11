package dev.webshop.nailstore.repository;

import dev.webshop.nailstore.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

}
