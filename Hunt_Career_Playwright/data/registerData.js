export const registerData = {
    validUsers: [
        {
            description: "a valid user",
            firstName: "Test",
            lastName: "User",
            email: "testuser@example.com",
            password: "password123",
            confirmPassword: "password123",
            phoneNumber: "1234567890"
        }
    ],
    duplicateUser: {
        firstName: "Pavan",
        lastName: "U",
        email: "upavan@mailinator.com",
        password: "Test@1234",
        confirmPassword: "Test@1234",
        phoneNumber: "1234567890"
    },
    invalidCases: [
        {
            description: "blank first name",
            firstName: "",
            lastName: "User",
            email: "test@example.com",
            password: "password123",
            confirmPassword: "password123",
            phoneNumber: "1234567890",
            testRunner: "register",
            expectedError: "firstNameRequiredMessage"
        },
        {
            description: "blank last name",
            firstName: "Test",
            lastName: "",
            email: "test@example.com",
            password: "password123",
            confirmPassword: "password123",
            phoneNumber: "1234567890",
            testRunner: "register",
            expectedError: "lastNameRequiredMessage"
        },
        {
            description: "blank email",
            firstName: "Test",
            lastName: "User",
            email: "",
            password: "password123",
            confirmPassword: "password123",
            phoneNumber: "1234567890",
            testRunner: "register",
            expectedError: "emailRequiredMessage"
        },
        {
            description: "invalid email",
            firstName: "Test",
            lastName: "User",
            email: "test",
            password: "password123",
            confirmPassword: "password123",
            phoneNumber: "1234567890",
            testRunner: "register",
            expectedError: "invalidEmailMessage"
        },
        {
            description: "blank password",
            firstName: "Test",
            lastName: "User",
            email: "test@example.com",
            password: "",
            confirmPassword: "password123",
            phoneNumber: "1234567890",
            testRunner: "register",
            expectedError: "passwordRequiredMessage"
        },
        {
            description: "password too short",
            firstName: "Test",
            lastName: "User",
            email: "test@example.com",
            password: "123",
            confirmPassword: "123",
            phoneNumber: "1234567890",
            testRunner: "register",
            expectedError: "passwordLengthMessage"
        },
        {
            description: "mismatched passwords",
            firstName: "Test",
            lastName: "User",
            email: "test@example.com",
            password: "password123",
            confirmPassword: "password456",
            phoneNumber: "1234567890",
            testRunner: "register",
            expectedError: "passwordMismatchMessage"
        },
        {
            description: "blank phone number",
            firstName: "Test",
            lastName: "User",
            email: "test@example.com",
            password: "password123",
            confirmPassword: "password123",
            phoneNumber: "",
            testRunner: "register",
            expectedError: "phoneNumberRequiredMessage"
        }
    ]
};