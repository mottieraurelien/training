package aurelienmottier.transactions.account.infrastructure.controller;

import aurelienmottier.transactions.account.domain.entity.Transaction;
import aurelienmottier.transactions.account.infrastructure.mapping.AppPageRequest;
import aurelienmottier.transactions.account.infrastructure.mapping.AppResponseView;
import aurelienmottier.transactions.account.infrastructure.service.TransactionService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    @GetMapping("/accounts")
    @JsonView(AppResponseView.Public.class)
    public ResponseEntity<Page<Transaction>> findByAccountCodes(@RequestBody final AppPageRequest request) {
        final Page<Transaction> transactions = this.service.findByAccountCodes(request);
        return new ResponseEntity<>(transactions, OK);
    }

    @GetMapping("/statuses")
    @JsonView(AppResponseView.Public.class)
    public ResponseEntity<Page<Transaction>> findByStatusCodes(@RequestBody final AppPageRequest request) {
        final Page<Transaction> transactions = this.service.findByStatusCodes(request);
        return new ResponseEntity<>(transactions, OK);
    }

    @GetMapping("/types")
    @JsonView(AppResponseView.Public.class)
    public ResponseEntity<Page<Transaction>> findByTypeCodes(@RequestBody final AppPageRequest request) {
        final Page<Transaction> transactions = this.service.findByTypeCodes(request);
        return new ResponseEntity<>(transactions, OK);
    }

    @GetMapping
    @JsonView(AppResponseView.Public.class)
    public ResponseEntity<Page<Transaction>> findAll(@RequestBody final AppPageRequest request) {
        final Page<Transaction> transactions = this.service.findAll(request);
        return new ResponseEntity<>(transactions, OK);
    }

}