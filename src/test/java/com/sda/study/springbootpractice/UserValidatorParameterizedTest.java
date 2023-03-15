package com.sda.study.springbootpractice;

import com.sda.study.springbootpractice.utils.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Parameterized test for User Validator
 */
public class UserValidatorParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings = {"Carolin12345", "67830833", "diuglisdh"})
    public void givenUserPassword_whenEncodePasswordCalled_shouldReturnEncodedPassword(String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @MethodSource("getPasswords")
    public void givenUserPasswords_whenEncodedPasswordCalled_shouldReturnEncodedPasswords(String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @ArgumentsSource(Parameters.class)
    public void givenUserPasswordsFromProvider_whenEncodedPasswordCalled_shouldReturnEncodedPassword(String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @CsvSource({"Carolin12345, Caroli#sda_java#n12345", "John1234, John#sda_java#1234"})
    public void givenPasswordsFromCsv_whenEncodedPasswordCalled_shouldReturnEncodedPassword(String password, String encodedPassword) {
        Assertions.assertEquals(encodedPassword, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataSource.csv")
    public void givenPasswordsFromCsvFile_whenEncodedPasswordCalled_shouldReturnEncodedPassword(String password, String encodedPassword) {
        Assertions.assertEquals(encodedPassword, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Carolin12345", "67830833", "diuglisdh"})
    public void givenUserPasswordConverter_whenEncodedPasswordCalled_shouldReturnEncodedPassword(@ConvertWith(PasswordConverter.class) String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }


    // For method source
    static Stream<Arguments> getPasswords() {
        return Stream.of(Arguments.of("sjhlajgslsd"), Arguments.of("askjbasklb"), Arguments.of("djsabdljabsd"));
    }

    // For argument source
    static class Parameters implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(Arguments.of("sjhlajgslsd"), Arguments.of("askjbasklb"), Arguments.of("djsabdljabsd"));
        }
    }

    // For argument converter
    static class PasswordConverter implements ArgumentConverter {

        @Override
        public Object convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {
            if (o instanceof String) {
                return ((String) o).replaceAll("12345", "67890");
            }
            fail("Cannot replace string!");
            return null;
        }
    }
}
