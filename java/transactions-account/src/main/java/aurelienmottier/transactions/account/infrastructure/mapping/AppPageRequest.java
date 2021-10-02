package aurelienmottier.transactions.account.infrastructure.mapping;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.springframework.data.domain.Sort.unsorted;

@Getter
public class AppPageRequest extends PageRequest {

    private List<String> accountCodes;

    private List<String> statusCodes;

    private List<String> typeCodes;

    protected AppPageRequest(final int page, final int size, final Sort sort) {
        super(page == 0 ? 1 : page, size == 0 ? 10 : size, sort == null ? unsorted() : sort);
    }

}