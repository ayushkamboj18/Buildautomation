package org.example;

import org.example.payment.CardPayment;
import org.example.payment.PaymentService;
import org.example.payment.UpiPayment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("org.example")
public class AppConfig {

    @Bean
    public User createUser() {
        return new User("Ayush", 22);
    }

    @Bean("cardPaymentService")
    public PaymentService createCardPaymentService() {
        return new CardPayment();
    }

    @Primary
    @Bean("upiPaymentService")
    public PaymentService createUpiPaymentService() {
        return new UpiPayment();
    }

    @Bean
    public OrderService createOrderService(@Qualifier("upiPaymentService") PaymentService paymentService) {
        return new OrderService(paymentService);
    }
}
