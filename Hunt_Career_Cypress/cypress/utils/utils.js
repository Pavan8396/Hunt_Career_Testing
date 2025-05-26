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

export function generateEmail(type){
    const dateTime = getCurrentDateTimeString();
    const baseName = `testuser_${dateTime}`;
    switch (type) {
        case "normal":
            return `${baseName}@mailinator.com`;
        case "withoutAllDomain":
            return `${baseName}`;
        case "withoutTopLevelDomain":
            return `${baseName}@mailinator`
        case "doubleAt":
            return `${baseName}@@mailinator.com`;
        case "dotBeforeAt":
            return `${baseName}.@mailinator.com`;
        case "withoutDomain":
            return `${baseName}@.com`;
        case "missingUsername":
            return `@mailinator.com`;
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