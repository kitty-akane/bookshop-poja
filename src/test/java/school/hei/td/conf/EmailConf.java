package school.hei.td.conf;

import org.springframework.test.context.DynamicPropertyRegistry;
import school.hei.td.PojaGenerated;

@PojaGenerated
public class EmailConf {

    void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("aws.ses.source", () -> "dummy-ses-source");
    }
}
