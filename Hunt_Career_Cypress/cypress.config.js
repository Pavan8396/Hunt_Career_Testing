const { defineConfig } = require("cypress");

module.exports = defineConfig({
  reporter: 'cypress-mochawesome-reporter',
  reporterOptions: {
    reportDir: 'mochawesome-report',
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
      require('cypress-mochawesome-reporter/plugin')(on);
      return config;
    }
  },
  retries: {
      runMode: 2,
      openMode: 0,
    },
});