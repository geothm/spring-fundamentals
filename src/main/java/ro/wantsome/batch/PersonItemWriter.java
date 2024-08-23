package ro.wantsome.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonItemWriter implements ItemWriter<Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemWriter.class);

    @Override
    public void write(Chunk<? extends Person> chunk) {
        for (Person person : chunk) {
            log.info("Writing person: {}", person);
        }
    }
}
