package com.viasoft.email_service.factory;

import com.viasoft.email_service.config.AppProperties;
import com.viasoft.email_service.service.AwsEmailServiceImpl;
import com.viasoft.email_service.service.EmailService;
import com.viasoft.email_service.service.OciEmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class EmailServiceFactoryTest {

    @Test
    void testGetInstance_ReturnsAwsService() {
        AwsEmailServiceImpl awsService = new AwsEmailServiceImpl();

        AppProperties props = mock(AppProperties.class);
        when(props.getIntegracao()).thenReturn("AWS");

        ApplicationContext context = mock(ApplicationContext.class);
        when(context.getBean("AWS")).thenReturn(awsService);

        EmailServiceFactory factory = new EmailServiceFactory(context, props);

        EmailService service = factory.getInstance();

        assertTrue(service instanceof AwsEmailServiceImpl);
    }

    @Test
    void testGetInstance_ReturnsOciService() {
        OciEmailServiceImpl ociService = new OciEmailServiceImpl();

        AppProperties props = mock(AppProperties.class);
        when(props.getIntegracao()).thenReturn("OCI");

        ApplicationContext context = mock(ApplicationContext.class);
        when(context.getBean("OCI")).thenReturn(ociService);

        EmailServiceFactory factory = new EmailServiceFactory(context, props);

        EmailService service = factory.getInstance();

        assertTrue(service instanceof OciEmailServiceImpl);
    }

    @Test
    void testGetInstance_ThrowsExceptionForUnknown() {
        AppProperties props = mock(AppProperties.class);
        when(props.getIntegracao()).thenReturn("UNKNOWN");

        ApplicationContext context = mock(ApplicationContext.class);

        EmailServiceFactory factory = new EmailServiceFactory(context, props);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, factory::getInstance);
        assertTrue(thrown.getMessage().contains("Valor inválido para mail.integracao"));
    }
}
