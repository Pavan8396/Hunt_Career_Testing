const { defineConfig } = require("cypress");

module.exports = defineConfig({
  baseUrl: 'http://localhost:3000',

  reporter: 'cypress-mochawesome-reporter',
  reporterOptions: {
    reportDir: 'mochawesome-report',
    charts: true,
    reportPageTitle: 'Test Report',
    embeddedScreenshots: true,
    inlineAssets: true,
    saveAllAttempts: true,
    overwrite: false,
    html: false,
    json: true
  },

  e2e: {
    setupNodeEvents(on, config) {
      require('cypress-mochawesome-reporter/plugin')(on);
      return config;
    },
  },

  // Optional: Enable retries
  // retries: {
  //   runMode: 2,
  //   openMode: 0,
  // },
});