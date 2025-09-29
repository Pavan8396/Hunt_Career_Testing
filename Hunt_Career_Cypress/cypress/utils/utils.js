import { faker } from "@faker-js/faker";

// This custom password generator is retained because it guarantees
// the presence of different character types, which is often
// necessary for testing password validation rules.
export function generateRandomPassword(length = 8) {
    const upperCase = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const lowerCase = 'abcdefghijklmnopqrstuvwxyz';
    const numbers = '0123456789';
    const specialChars = '!@#$%^&*';
    const allChars = upperCase + lowerCase + numbers + specialChars;

    let password = '';
    
    password += upperCase[Math.floor(Math.random() * upperCase.length)];
    password += lowerCase[Math.floor(Math.random() * lowerCase.length)];
    password += numbers[Math.floor(Math.random() * numbers.length)];
    password += specialChars[Math.floor(Math.random() * specialChars.length)];

    for (let i = 4; i < length; i++) {
        password += allChars[Math.floor(Math.random() * allChars.length)];
    }

    return password.split('').sort(() => 0.5 - Math.random()).join('');
}

export function getRandomFirstName(){
    return faker.person.firstName();
}

export function getRandomLastName(){
    return faker.person.lastName();
}

export function getRandomCompanyName(){
    return faker.company.name();
}

export function generateEmail(type) {
  const baseName = `test_${faker.lorem.word()}`;

  switch (type) {
    case "normal":
      return faker.internet.email({ firstName: 'testuser', allowSpecialCharacters: false });
    case "plainEmail":
      return baseName;
    case "noLocalPart":
      return "@mailinator.com";
    case "localPartOnly":
      return `${baseName}@`;
    case "emailHaving2At":
      return `u@${baseName}@mailinator.com`;
    case "withoutTopLevelDomain":
      return `${baseName}@mailinator`;
    case "tailingWithDot":
      return `${baseName}@mailinator.`;
    case "topLevelDomainTooShort":
      return `${baseName}@mailinator.c`;
    case "domainStartsWithHyphen":
      return `${baseName}@-mailinator.com`;
    case "doubleDotInDomain":
      return `${baseName}@mailinator..com`;
    case "noAtTheRate":
      return `${baseName}#mailinator.com`;
    case "illegalCharacters":
      return `${baseName}<>@mailinator.com`;
    case "dotAfterName":
      return `${baseName}.@mailinator.com`;
    case "twoConsecutiveDots":
      return `u..${baseName}@mailinator.com`;
    case "dotAtBeginning":
      return `.${baseName}@mailinator.com`;
    case "withQuotation":
      return `'${baseName}@mailinator.com'`;
    case "unclosedQuotation":
      return `'${baseName}@mailinator.com`;
    case "veryLengthyLocal":
      return `verylonglocalpartthatexceedstheusuallimitstoverifythelimitdoesitexceedornotbyaddingverylongcharacters@example.com`;
    case "veryLengthyDomain":
      return `${baseName}@verylonglocalpartthatexceedstheusuallimitstoverifythelimitdoesitexceedornotbyaddingverylongcharacters.com`;
    case "veryLengthyTopLevelDomain":
      return `${baseName}@example.verylonglocalpartthatexceedstheusuallimitstoverifythelimitdoesitexceedornotbyaddingverylongcharacterscom`;
    case "missingSecondLevelDomain":
      return `${baseName}@com`;
    case "withComma":
      return `${baseName}@mailinator,com`;
    case "twoConsecutiveAt":
      return `${baseName}@@mailinator.com`;
    case "underscoreInTLD":
      return `${baseName}@mailinator.c_m`;
    case "withoutDomain":
      return `${baseName}@.com`;
    case "dotBetweenName":
      return `${faker.person.firstName()}.${faker.person.lastName()}@mailinator.com`;
    case "spaceBeforeName":
      return ` ${faker.internet.email({ firstName: 'testuser', allowSpecialCharacters: false })}`;
    case "spaceAfterName":
      return `${faker.internet.email({ firstName: 'testuser', allowSpecialCharacters: false })} `;
    default:
      return faker.internet.email({ firstName: 'testuser', allowSpecialCharacters: false });
  }
}

export function generateRandomPhoneNumber() {
    return faker.string.numeric(10);
}