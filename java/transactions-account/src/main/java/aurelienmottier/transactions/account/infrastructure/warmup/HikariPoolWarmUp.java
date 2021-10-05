package aurelienmottier.transactions.account.infrastructure.warmup;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class HikariPoolWarmUp implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(HikariPoolWarmUp.class);

    private final HikariDataSource datasource;

    public HikariPoolWarmUp(final HikariDataSource datasource) {
        this.datasource = datasource;
    }

    @Autowired
    public void run(final ApplicationArguments args) throws SQLException {
        datasource.getConnection();
        LOGGER.info("Hikari connection pool has been initialized.");
    }

}