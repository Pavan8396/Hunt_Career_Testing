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

  // duplicateUser: {
  //   description: "Duplicate User",
  //   firstName: "Pavan",
  //   lastName: "U",
  //   email: "upavan@mailinator.com",
  //   password: "Test@1234",
  //   confirmPassword: "Test@1234",
  //   phoneNumber: "1234567890",
  //   expectedError: "checkDuplicateErrorMessage"
  // },

  // invalidCases: [
  //   {
  //     description: "Missing First Name",
  //     firstName: "",
  //     lastName: getRandomLastName(),
  //     email: generateEmail("normal"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkFirstNameBlankMessage",
  //     testRunner: "registerFirstPage"
  //   },
  //   {
  //     description: "Missing Last Name",
  //     firstName: getRandomFirstName(),
  //     lastName: "",
  //     email: generateEmail("normal"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkLastNameBlankMessage",
  //     testRunner: "registerFirstPage"
  //   },
  //   {
  //     description: "Missing Email",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: "",
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkEmailBlankMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Plain)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("plainEmail"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (No Local Part)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("noLocalPart"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Local Part Only)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("localPartOnly"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Two @)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("twoConsecutiveAt"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (No Top Level Domain)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("withoutTopLevelDomain"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Trailing Dot)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("tailingWithDot"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Short TLD)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("topLevelDomainTooShort"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Domain Starts With Hyphen)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("domainStartsWithHyphen"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Double Dot in Domain)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("doubleDotInDomain"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (No @)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("noAtTheRate"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Illegal Characters)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("illegalCharacters"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Dot After Name)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("dotAfterName"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Two Consecutive Dots)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("twoConsecutiveDots"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Dot At Beginning)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("dotAtBeginning"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (With Quotation)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("withQuotation"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Unclosed Quotation)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("unclosedQuotation"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Very Lengthy Local Part)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("veryLengthyLocal"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Very Lengthy Domain)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("veryLengthyDomain"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Very Lengthy TLD)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("veryLengthyTopLevelDomain"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Missing Second Level Domain)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("missingSecondLevelDomain"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (With Comma)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("withComma"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Underscore in TLD)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("underscoreInTLD"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Invalid Email Format (Without Domain)",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("withoutDomain"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPleaseEnterValidEmailAddressMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Short Password",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("normal"),
  //     password: "Test",
  //     confirmPassword: "Test",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPasswordMustHaveRequiredNumberCharactersMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Password Mismatch",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("normal"),
  //     password: "Test@1234",
  //     confirmPassword: "Mismatch@1234",
  //     phoneNumber: generateRandomPhoneNumber(),
  //     expectedError: "checkPasswordNotMatchMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "Missing Phone Number",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("normal"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: "",
  //     expectedError: "checkPhoneNumberRequiredMessage",
  //     testRunner: "register"
  //   },
  //   {
  //     description: "Invalid Phone Number",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("normal"),
  //     password: "Test@1234",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: "abcd1234567",
  //     expectedError: "checkPhoneNumberInvalidMessage",
  //     testRunner: "register"
  //   },
  //   {
  //     description: "No Password",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("normal"),
  //     password: " ",
  //     confirmPassword: "Test@1234",
  //     phoneNumber: "abcd1234567",
  //     expectedError: "checkPasswordMustHaveRequiredNumberCharactersMessage",
  //     testRunner: "registerSecondPage"
  //   },
  //   {
  //     description: "No Confirm Password",
  //     firstName: getRandomFirstName(),
  //     lastName: getRandomLastName(),
  //     email: generateEmail("normal"),
  //     password: "Test@1234",
  //     confirmPassword: " ",
  //     phoneNumber: "abcd1234567",
  //     expectedError: "checkPasswordNotMatchMessage",
  //     testRunner: "registerSecondPage"
  //   }
  // ]
};