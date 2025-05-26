const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    baseUrl: 'http://localhost:3000',
    setupNodeEvents(on, config) {
      require('cypress-mochawesome-reporter/plugin')(on);
    },
    reporter: 'cypress-mochawesome-reporter',
    reporterOptions: {
      charts: true,
      reportPageTitle: 'Test Report',
      embeddedScreenshots: true,
      inlineAssets: true,
      saveAllAttempts: true,
    },
    // retries: {
    //   runMode: 2,
    //   openMode: 0,
    // },
  },
});