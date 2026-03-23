package com.codurance.dip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.MonthDay;
import java.util.Collections;

import static com.codurance.dip.EmployeeBuilder.anEmployee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayGreeterShould {
    private static final int CURRENT_MONTH = 7;
    private static final int CURRENT_DAY_OF_MONTH = 9;
    private static final MonthDay TODAY = MonthDay.of(CURRENT_MONTH, CURRENT_DAY_OF_MONTH);

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private Clock clock;
    @Mock
    private IEmailSender emailSender;

    @InjectMocks
    private BirthdayGreeter birthdayGreeter;

    @Test
    public void should_send_greeting_email_to_employee() {
        given(clock.monthDay()).willReturn(TODAY);
        Employee employee = anEmployee().build();
        given(employeeRepository.findEmployeesBornOn(MonthDay.of(CURRENT_MONTH, CURRENT_DAY_OF_MONTH))).willReturn(Collections.singletonList(employee));

        birthdayGreeter.sendGreetings();

        verify(emailSender).send(refEq(new Email(employee.getEmail(), "Happy birthday!", "Happy birthday, dear " + employee.getFirstName() + "!")));
    }



}