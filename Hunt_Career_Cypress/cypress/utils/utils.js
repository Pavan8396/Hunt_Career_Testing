import { faker } from "@faker-js/faker";

function getCurrentDateTimeString() {
    const now = new Date();
    const day = String(now.getDate()).padStart(2, '0');
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const year = now.getFullYear();
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');
    return `${day}-${month}-${year}-${hours}-${minutes}-${seconds}`;
}

export function generateRandomPassword(length = 8) {
    const upperCase = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const lowerCase = 'abcdefghijklmnopqrstuvwxyz';
    const numbers = '0123456789';
    const specialChars = '!@#$%^&*';
    const allChars = upperCase + lowerCase + numbers + specialChars;

    let password = '';
    
    // Ensure at least one from each category
    password += upperCase[Math.floor(Math.random() * upperCase.length)];
    password += lowerCase[Math.floor(Math.random() * lowerCase.length)];
    password += numbers[Math.floor(Math.random() * numbers.length)];
    password += specialChars[Math.floor(Math.random() * specialChars.length)];

    // Fill the rest randomly
    for (let i = 4; i < length; i++) {
        password += allChars[Math.floor(Math.random() * allChars.length)];
    }

    // Shuffle the password to avoid predictable pattern
    password = password.split('').sort(() => 0.5 - Math.random()).join('');

    return password;
}

export function getRandomFirstName(){
    const firstName = faker.person.firstName();
    return firstName;
}

export function getRandomLastName(){
    const lastName= faker.person.lastName();
    return lastName;
}

export function getRandomCompanyName(){
    const companyName = faker.company.name();
    return companyName;
}

function getRandomSuffix(length = 4) {
  return Math.random().toString(36).substring(2, 2 + length);
}

export function generateEmail(type) {
  const dateTime = getCurrentDateTimeString();
  const random = getRandomSuffix(); 
  const baseName = `testuser_${dateTime}_${random}`;

  switch (type) {
    case "normal":
      return `${baseName}@mailinator.com`;

    // invalid formats
    case "plainEmail":
      return baseName; // no @domain
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
      return `${baseName}.user@mailinator.com`
    case "spaceBeforeName":
      return ` ${baseName}@mailinator.com`
    case "spaceAfterName":
      return `${baseName}@mailinator.com `
    
      default:
      return `${baseName}@mailinator.com`;
  }
}

export function generateRandomPhoneNumber() {
    let phone = '';
    for (let i = 0; i < 10; i++) {
        phone += Math.floor(Math.random() * 10);
    }
    return phone;
}