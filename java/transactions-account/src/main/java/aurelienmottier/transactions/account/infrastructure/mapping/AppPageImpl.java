package aurelienmottier.transactions.account.infrastructure.mapping;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AppPageImpl<T> extends PageImpl<T> {

    public AppPageImpl(final List<T> entities, final Pageable pageable, final long totalNumberOfEntities) {
        super(entities, pageable, totalNumberOfEntities);
    }

    @JsonView(AppResponseView.Public.class)
    public boolean hasNext() {
        return super.hasNext();
    }

    @JsonView(AppResponseView.Public.class)
    public List<T> getContent() {
        return super.getContent();
    }

}