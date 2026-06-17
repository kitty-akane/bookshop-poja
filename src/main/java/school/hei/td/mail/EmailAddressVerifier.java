package school.hei.td.mail;

import jakarta.mail.internet.InternetAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.td.PojaGenerated;
import software.amazon.awssdk.services.ses.model.VerifyEmailIdentityRequest;

import java.util.function.Consumer;

@Component
@AllArgsConstructor
@PojaGenerated
public class EmailAddressVerifier implements Consumer<InternetAddress> {

    private final EmailConf emailConf;

    @Override
    public void accept(InternetAddress emailAddress) {
        emailConf
                .getSesClient()
                .verifyEmailIdentity(
                        VerifyEmailIdentityRequest.builder().emailAddress(emailAddress.getAddress()).build());
    }
}
