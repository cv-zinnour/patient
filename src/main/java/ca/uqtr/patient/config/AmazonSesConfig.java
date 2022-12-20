package ca.uqtr.patient.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonSesConfig {
    @Value("${aws.ses.accessKey}")
    private String accessKey;
    @Value("${aws.ses.secretKey}")
    private String secretKey;

    @Bean
    public AmazonSimpleEmailService getAmazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(getAwsCredentialProvider())
                .withRegion("ca-central-1")
                .build();
    }

    private AWSCredentialsProvider getAwsCredentialProvider() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(accessKey, secretKey);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }
}
