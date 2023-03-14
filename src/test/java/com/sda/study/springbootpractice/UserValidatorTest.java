package com.sda.study.springbootpractice;

import com.sda.study.springbootpractice.exceptions.UserValidationException;
import com.sda.study.springbootpractice.models.User;
import com.sda.study.springbootpractice.models.UserType;
import com.sda.study.springbootpractice.utils.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testing exceptions
 */
public class UserValidatorTest {

    @Test
    public void givenUser_whenIsAdminCalled_shouldExpectException() {

        //JUnit 5 examples

        User user = new User();
        user.setFirstName("Carolin");
        user.setLastName("Randala");
        user.setPassword("123456");
        user.setUserType(UserType.TEACHER);

        UserValidator userValidator = new UserValidator();
        try {
            userValidator.isAdminUser(user);
        } catch (UserValidationException e) {
            String exceptedMessage = "User validation failed for user: Carolin Randala, Error: User admin check failed!";
            Assertions.assertEquals(exceptedMessage, e.getLocalizedMessage());
        }
    }
    @Test
    public void givenUser_whenIsAdminCalledFunctionally_shouldExpectException() {
    User user = new User();
        user.setFirstName("Carolin");
        user.setLastName("Randala");
        user.setPassword("123456");
        user.setUserType(UserType.STUDENT);

    UserValidator userValidator = new UserValidator();

    UserValidationException e = Assertions.assertThrows(UserValidationException.class,
            () -> userValidator.isAdminUser(user));

        String exceptedMessage = "User validation failed for user: Carolin Randala, Error: User admin check failed!";
        Assertions.assertEquals(exceptedMessage, e.getLocalizedMessage());
    }

    // AssertJ examples
    @Test
    public void givenUser_whenIsAdminUserCalled_shouldExpectNPE() {
        User user = new User();
        user.setFirstName("Carolin");
        user.setLastName("Randala");
        user.setPassword("123456");

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new UserValidator().isAdminUser(user))
                .isExactlyInstanceOf(NullPointerException.class)
                .hasNoCause()
                .hasMessageContaining("null");
    }

    @Test
    public void givenUser_whenIsAdminUserCalled_shouldExpectExceptionAssert() {
        User user = new User();
        user.setFirstName("Carolin");
        user.setLastName("Randala");
        user.setPassword("123456");
        user.setUserType(UserType.STUDENT);

        org.assertj.core.api.Assertions.assertThatExceptionOfType(UserValidationException.class)
                .isThrownBy(() -> new UserValidator().isAdminUser(user))
                .withNoCause()
                .withMessageContaining("User validation failed for user: Carolin Randala, Error: User admin check failed!");
    }

    @Test
    public void givenUserWithoutUserType_whenIsAdminUserCalled_shouldExpectException() {
        User user = new User();
        user.setFirstName("Carolin");
        user.setLastName("Randala");
        user.setPassword("123456");

        Throwable exception = org.assertj.core.api.Assertions.catchThrowable(() -> new UserValidator().isAdminUser(user));
        Assertions.assertEquals("The user type is null for user: Carolin Randala", exception.getLocalizedMessage());
    }

    @Test
    public void givenUser_whenIsAdminUserCalled_shouldExpectUserValidationException() {
        User user = new User();
        user.setFirstName("Carolin");
        user.setLastName("Randala");
        user.setPassword("123456");
        user.setUserType(UserType.TEACHER);

        UserValidationException userValidationException = org.assertj.core.api.Assertions.catchThrowableOfType(() -> new UserValidator().isAdminUser(user), UserValidationException.class);
        String exceptedMessage = "User validation failed for user: Carolin Randala, Error: User admin check failed!";
        Assertions.assertEquals(exceptedMessage, userValidationException.getLocalizedMessage());
    }

    // JUnit4 Example
/*
    @Test(expect = UserValidationException.class)
    public void givenUser_whenIsAdminCalled_shouldExpectException_JUnit4() {

    User user = new User();
        user.setFirstName("Carolin");
        user.setLastName("Randala");
        user.setPassword("123456");
        user.setUserType(UserType.TEACHER);

    UserValidator userValidator = new UserValidator();
    userValidator.isAdminUser(user);
    }*/
}


