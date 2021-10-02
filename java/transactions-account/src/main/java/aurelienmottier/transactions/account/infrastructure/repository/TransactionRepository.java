package aurelienmottier.transactions.account.infrastructure.repository;

import aurelienmottier.transactions.account.domain.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAllByAccountCodeIn(final List<String> accountCodes, final Pageable pageable);

    Page<Transaction> findAllByStatusCodeIn(final List<String> statusCodes, final Pageable pageable);

    Page<Transaction> findAllByTypeCodeIn(final List<String> typeCodes, final Pageable pageable);

    Page<Transaction> findAllByAccountCodeInAndStatusCodeInAndTypeCodeIn(final List<String> accountCodes, final List<String> statusCodes, final List<String> typeCodes, final Pageable pageable);

}