import { generateEmail, generateRandomPassword, generateRandomPhoneNumber, getRandomFirstName, getRandomLastName } from "../utils/utils";

export const registerData = {
    validUser: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('normal'),
            password: password,
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    duplicateUser: {
        firstName: "Pavan", 
        lastName: "U",
        email: "upavan@mailinator.com",
        password: "Test@1234",
        confirmPassword: "Test@1234",
        phoneNumber: "1234567890"
    },

    noFirstName: (() => {
        const password = generateRandomPassword();
        return {
            firstName: "",
            lastName: getRandomLastName(),
            email: generateEmail('normal'),
            password: password,
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    noLastName: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: "",
            email: generateEmail('normal'),
            password: password,
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    noEmailName: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: "",
            password: password,
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    noPasswordName: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('normal'),
            password: "",
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    passwordWithLessCharacters: {
        firstName: getRandomFirstName(),
        lastName: getRandomLastName(),
        email: generateEmail('normal'),
        password: "Test",
        confirmPassword: "Test",
        phoneNumber: generateRandomPhoneNumber()
    },

    noConfirmPasswordName: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('normal'),
            password: password,
            confirmPassword: "",  // confirm password intentionally empty
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    noPhoneNumberName: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('normal'),
            password: password,
            confirmPassword: password,
            phoneNumber: ""
        };
    })(),

    invalidPhoneNumber: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('normal'),
            password: password,
            confirmPassword: password,
            phoneNumber: "abcd1234567"  // intentionally invalid phone number
        };
    })(),

    emailWithoutAllDomain: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('withoutAllDomain'),
            password: password,
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    emailWithoutTopLevelDomain: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('withoutTopLevelDomain'),
            password: password,
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    emailDoubleAt: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('doubleAt'),
            password: password,
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    emailDotBeforeAt: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('dotBeforeAt'),
            password: password,
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    emailWithoutDomain: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('withoutDomain'),
            password: password,
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })(),

    emailMissingUsername: (() => {
        const password = generateRandomPassword();
        return {
            firstName: getRandomFirstName(),
            lastName: getRandomLastName(),
            email: generateEmail('missingUsername'),
            password: password,
            confirmPassword: password,
            phoneNumber: generateRandomPhoneNumber()
        };
    })()
};