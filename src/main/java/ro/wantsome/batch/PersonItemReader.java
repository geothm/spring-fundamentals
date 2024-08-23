package ro.wantsome.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PersonItemReader implements ItemReader<Person> {
    private final List<Person> data = List.of(
            new Person("bla", "bla"),
            new Person("balblasdas", "asfasdas1231"),
            new Person("Adrian", "Mirica"),
            new Person("Wantsome", "Academy")
    );

    private int index = 0;

    @Override
    public Person read() {
        if (index < data.size()) {
            return data.get(index++);
        }
        return null;
    }
}
