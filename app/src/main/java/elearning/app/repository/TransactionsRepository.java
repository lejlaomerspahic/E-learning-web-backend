package elearning.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elearning.app.model.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
