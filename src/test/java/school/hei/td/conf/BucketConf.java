package school.hei.td.conf;

import org.springframework.test.context.DynamicPropertyRegistry;
import school.hei.td.PojaGenerated;

@PojaGenerated
public class BucketConf {

  void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("aws.s3.bucket", () -> "dummy-bucket");
  }
}
