package aurelienmottier.transactions.account.domain.entity;

import aurelienmottier.transactions.account.infrastructure.mapping.AppResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "transaction_types")
public class TransactionType implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NaturalId
    @JsonView(AppResponseView.Public.class)
    @Column(length = 20, nullable = false, updatable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "type", fetch = LAZY)
    private List<Transaction> transactions;

}