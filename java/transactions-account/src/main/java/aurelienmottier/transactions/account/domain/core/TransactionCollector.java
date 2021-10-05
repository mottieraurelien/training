package aurelienmottier.transactions.account.domain.core;

import aurelienmottier.transactions.account.domain.entity.Transaction;
import aurelienmottier.transactions.account.infrastructure.mapping.AppPageImpl;
import aurelienmottier.transactions.account.infrastructure.mapping.AppPageRequest;
import aurelienmottier.transactions.account.infrastructure.repository.TransactionRepository;
import aurelienmottier.transactions.account.infrastructure.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TransactionCollector implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public Page<Transaction> findByAccountCodes(final AppPageRequest request) {
        final List<String> accountCodes = request.getAccountCodes();
        final Page<Transaction> transactions = this.repository.findAllByAccountCodeIn(accountCodes, request);
        return new AppPageImpl<>(transactions.getContent(), request, transactions.getTotalElements());
    }

    @Override
    public Page<Transaction> findByStatusCodes(final AppPageRequest request) {
        final List<String> statusCodes = request.getStatusCodes();
        final Page<Transaction> transactions = this.repository.findAllByStatusCodeIn(statusCodes, request);
        return new AppPageImpl<>(transactions.getContent(), request, transactions.getTotalElements());
    }

    @Override
    public Page<Transaction> findByTypeCodes(final AppPageRequest request) {
        final List<String> typeCodes = request.getTypeCodes();
        final Page<Transaction> transactions = this.repository.findAllByTypeCodeIn(typeCodes, request);
        return new AppPageImpl<>(transactions.getContent(), request, transactions.getTotalElements());
    }

    @Override
    public Page<Transaction> findAll(final AppPageRequest request) {
        final List<String> accountCodes = request.getAccountCodes();
        final List<String> statusCodes = request.getStatusCodes();
        final List<String> typeCodes = request.getTypeCodes();
        final Page<Transaction> transactions = this.repository.findAllByAccountCodeInAndStatusCodeInAndTypeCodeIn(accountCodes, statusCodes, typeCodes, request);
        return new AppPageImpl<>(transactions.getContent(), request, transactions.getTotalElements());
    }

}