package aurelienmottier.transactions.account.domain.entity;

import aurelienmottier.transactions.account.infrastructure.mapping.AppResponseView;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonView(AppResponseView.Public.class)
    private Long id;

    @JsonView(AppResponseView.Public.class)
    @JsonUnwrapped(prefix = "account_")
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "account_code", referencedColumnName = "code", updatable = false, nullable = false)
    private Account account;

    @JsonView(AppResponseView.Public.class)
    @JsonUnwrapped(prefix = "transaction_status_")
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "transaction_status_code", referencedColumnName = "code", updatable = false, nullable = false)
    private TransactionStatus status;

    @JsonView(AppResponseView.Public.class)
    @JsonUnwrapped(prefix = "transaction_type_")
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "transaction_type_code", referencedColumnName = "code", updatable = false, nullable = false)
    private TransactionType type;

    @JsonView(AppResponseView.Public.class)
    @Column(precision = 12, scale = 2, nullable = false, updatable = false)
    private BigDecimal amount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updated;

}