const { defineConfig } = require("cypress");
const allureWriter = require('@shelex/cypress-allure-plugin/writer');

module.exports = defineConfig({
  reporter: 'cypress-mochawesome-reporter',
  reporterOptions: {
    reportDir: 'D:/Project HuntCareer/Hunt_Career_Testing/Hunt_Career_Cypress/cypress/reports/mochawesome-report',
    overwrite: true,
    html: true,
    json: true,
    saveJson: true,
    embeddedScreenshots: true,
    inlineAssets: true,
    charts: true,
    reportPageTitle: 'Test Report',
    saveAllAttempts: true
  },
  e2e: {
    baseUrl: 'http://localhost:3000',
    setupNodeEvents(on, config) {
      allureWriter(on, config);
      require('cypress-mochawesome-reporter/plugin')(on);
      return config;
    },
      env: {
      allureResultsPath: 'D:/Project HuntCareer/Hunt_Career_Testing/Hunt_Career_Cypress/cypress/reports/allure-results',
    }
  },
  retries: {
    runMode: 2,
    openMode: 0,
  },
});