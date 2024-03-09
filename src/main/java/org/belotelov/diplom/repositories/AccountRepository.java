package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
