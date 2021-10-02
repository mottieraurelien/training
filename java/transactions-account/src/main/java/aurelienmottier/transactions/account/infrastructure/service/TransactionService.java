package aurelienmottier.transactions.account.infrastructure.service;

import aurelienmottier.transactions.account.domain.entity.Transaction;
import aurelienmottier.transactions.account.infrastructure.mapping.AppPageRequest;
import org.springframework.data.domain.Page;

public interface TransactionService {

    Page<Transaction> findByAccountCodes(final AppPageRequest request);

    Page<Transaction> findByStatusCodes(final AppPageRequest request);

    Page<Transaction> findByTypeCodes(final AppPageRequest request);

    Page<Transaction> findAll(final AppPageRequest request);

}