import {
  generateEmail,
  generateRandomPassword,
  getRandomCompanyName
} from "../utils/utils";

export const registerData = {
  validUser:[ 
  (() => {
    const password = generateRandomPassword();
    return {
      description: "Valid User",
      name: getRandomCompanyName(),
      email: generateEmail("normal"),
      password: password,
      confirmPassword: password,
      expectedSuccess: "checkSuccessCreationMessage"
    };
  })(),

  (() => {
    const password = generateRandomPassword();
    return {
      description: "Email With Dot InBetween Name",
      name: getRandomCompanyName(),
      email: generateEmail("dotBetweenName"),
      password: password,
      confirmPassword: password,
      expectedSuccess: "checkSuccessCreationMessage"
  }
})(),

(() => {
    const password = generateRandomPassword();
    return {
    description: "Sapce Before Name",
    name: getRandomCompanyName(),
      email: generateEmail("spaceBeforeName"),
      password: password,
      confirmPassword: password,
      expectedSuccess: "checkSuccessCreationMessage"
  }
})(),

(() => {
    const password = generateRandomPassword();
    return {
    description: "Space After Name",
    name: getRandomCompanyName(),
      email: generateEmail("spaceAfterName"),
      password: password,
      confirmPassword: password,
      expectedSuccess: "checkSuccessCreationMessage"
  }
})(),
],

  duplicateUser: {
    description: "Duplicate employer",
    name: "Exigent Services Pvt Ltd",
    email: "exigent@mailinator.com",
    password: "Test@1234",
    confirmPassword: "Test@1234",
    expectedError: "checkDuplicateErrorMessage"
  },

  invalidCases: [
    {
      description: "Missing Name",
      name: "",
      email: generateEmail("normal"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkEmployerNameBlankMessage",
    },
    {
      description: "Missing Email",
      name: getRandomCompanyName(),
      email: "",
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkEmailBlankMessage",
    },
    {
      description: "Invalid Email Format (Plain)",
      name: getRandomCompanyName(),
      email: generateEmail("plainEmail"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (No Local Part)",
      name: getRandomCompanyName(),
      email: generateEmail("noLocalPart"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Local Part Only)",
      name: getRandomCompanyName(),
      email: generateEmail("localPartOnly"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Two @)",
      name: getRandomCompanyName(),
      email: generateEmail("twoConsecutiveAt"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (No Top Level Domain)",
      name: getRandomCompanyName(),
      email: generateEmail("withoutTopLevelDomain"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Trailing Dot)",
      name: getRandomCompanyName(),
      email: generateEmail("tailingWithDot"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Short TLD)",
      name: getRandomCompanyName(),
      email: generateEmail("topLevelDomainTooShort"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Domain Starts With Hyphen)",
      name: getRandomCompanyName(),
      email: generateEmail("domainStartsWithHyphen"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage"
    },
    {
      description: "Invalid Email Format (Double Dot in Domain)",
      name: getRandomCompanyName(),
      email: generateEmail("doubleDotInDomain"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage"
    },
    {
      description: "Invalid Email Format (No @)",
      name: getRandomCompanyName(),
      email: generateEmail("noAtTheRate"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Illegal Characters)",
      name: getRandomCompanyName(),
      email: generateEmail("illegalCharacters"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Dot After Name)",
      name: getRandomCompanyName(),
      email: generateEmail("dotAfterName"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Two Consecutive Dots)",
      name: getRandomCompanyName(),
      email: generateEmail("twoConsecutiveDots"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Dot At Beginning)",
      name: getRandomCompanyName(),
      email: generateEmail("dotAtBeginning"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (With Quotation)",
      name: getRandomCompanyName(),
      email: generateEmail("withQuotation"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Unclosed Quotation)",
      name: getRandomCompanyName(),
      email: generateEmail("unclosedQuotation"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Very Lengthy Local Part)",
      name: getRandomCompanyName(),
      email: generateEmail("veryLengthyLocal"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Very Lengthy Domain)",
      name: getRandomCompanyName(),
      email: generateEmail("veryLengthyDomain"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Very Lengthy TLD)",
      name: getRandomCompanyName(),
      email: generateEmail("veryLengthyTopLevelDomain"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Missing Second Level Domain)",
      name: getRandomCompanyName(),
      email: generateEmail("missingSecondLevelDomain"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (With Comma)",
      name: getRandomCompanyName(),
      email: generateEmail("withComma"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Underscore in TLD)",
      name: getRandomCompanyName(),
      email: generateEmail("underscoreInTLD"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Invalid Email Format (Without Domain)",
      name: getRandomCompanyName(),
      email: generateEmail("withoutDomain"),
      password: "Test@1234",
      confirmPassword: "Test@1234",
      expectedError: "checkPleaseEnterValidEmailAddressMessage",
    },
    {
      description: "Short Password",
      name: getRandomCompanyName(),
      email: generateEmail("normal"),
      password: "Test",
      confirmPassword: "Test",
      expectedError: "checkPasswordMustHaveRequiredNumberCharactersMessage"
    },
    {
      description: "Password Mismatch",
      name: getRandomCompanyName(),
      email: generateEmail("normal"),
      password: "Test@1234",
      confirmPassword: "Mismatch@1234",
      expectedError: "checkPasswordNotMatchMessage",
    },
    {
      description: "No Password",
      name: getRandomCompanyName(),
      email: generateEmail("normal"),
      password: " ",
      confirmPassword: "Test@1234",
      expectedError: "checkPasswordMustHaveRequiredNumberCharactersMessage",
    },
    {
      description: "No Confirm Password",
      name: getRandomCompanyName(),
      email: generateEmail("normal"),
      password: "Test@1234",
      confirmPassword: " ",
      expectedError: "checkPasswordNotMatchMessage",
    }
  ]
};