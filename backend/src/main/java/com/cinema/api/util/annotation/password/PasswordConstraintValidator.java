package com.cinema.api.util.annotation.password;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<Password, String> {

     @Override
     public void initialize(Password arg0) {
     }

     @Override
     public boolean isValid(String password, ConstraintValidatorContext context) {
          PasswordValidator validator = new PasswordValidator(Arrays.asList(
               new LengthRule(8, 128),
               new CharacterRule(EnglishCharacterData.UpperCase, 1),
               new CharacterRule(EnglishCharacterData.LowerCase, 1),
               new CharacterRule(EnglishCharacterData.Digit, 1),
               new CharacterRule(EnglishCharacterData.Special, 1),
               new WhitespaceRule()));

          RuleResult result = validator.validate(new PasswordData(password));
          if (result.isValid()) {
               return true;
          }
          context.disableDefaultConstraintViolation();
          context.buildConstraintViolationWithTemplate(
               (String.join(",", validator.getMessages(result))))
               .addConstraintViolation();
          return false;
     }
}
