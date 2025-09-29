export const employerRegisterData = {
    validUsers: [
        {
            description: "a valid user",
            name: "Test Employer",
            email: "newemployer@gmail.com",
            password: "password",
            confirmPassword: "password"
        }
    ],
    duplicateUser: {
        name: "Test Employer",
        email: "testemployer@gmail.com",
        password: "password",
        confirmPassword: "password"
    },
    invalidCases: [
        {
            description: "a blank name",
            name: "",
            email: "test@gmail.com",
            password: "password",
            confirmPassword: "password",
            expectedError: "nameRequiredMessage"
        },
        {
            description: "a blank email",
            name: "Test Employer",
            email: "",
            password: "password",
            confirmPassword: "password",
            expectedError: "emailRequiredMessage"
        },
        {
            description: "a blank password",
            name: "Test Employer",
            email: "test@gmail.com",
            password: "",
            confirmPassword: "password",
            expectedError: "passwordRequiredMessage"
        },
        {
            description: "a blank confirm password",
            name: "Test Employer",
            email: "test@gmail.com",
            password: "password",
            confirmPassword: "",
            expectedError: "confirmPasswordRequiredMessage"
        },
        {
            description: "mismatched passwords",
            name: "Test Employer",
            email: "test@gmail.com",
            password: "password",
            confirmPassword: "wrongpassword",
            expectedError: "passwordMismatchMessage"
        }
    ]
};