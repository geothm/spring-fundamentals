package ro.wantsome.batch.visitors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ro.wantsome.databases.domain.batch.Visitors;

import java.text.SimpleDateFormat;

@Component
public class VisitorsItemProcessor implements ItemProcessor<Visitors, Visitors> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

    //Processing method that is changing the format of the data field. It just an operations, because
    //as you know, each step from a batch job needs to a have a reader, a processor and a writer
    @Override
    public Visitors process(Visitors item) throws Exception {
        item.setVisitDate(dateFormat.parse(item.getStrVisitDate()));
        return item;
    }
}
