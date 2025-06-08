// const { defineConfig } = require("cypress");

// module.exports = defineConfig({
//   e2e: {
//     baseUrl: 'http://localhost:3000',
//     setupNodeEvents(on, config) {
//       require('cypress-mochawesome-reporter/plugin')(on);
//     },
//     reporter: 'cypress-mochawesome-reporter',
//     reporterOptions: {
//       charts: true,
//       reportPageTitle: 'Test Report',
//       embeddedScreenshots: true,
//       inlineAssets: true,
//       saveAllAttempts: true,
//       overwrite: false,
//       html: false,
//       json: true
//     },
//     // retries: {
//     //   runMode: 2,
//     //   openMode: 0,
//     // },
//   },
// });

const { defineConfig } = require("cypress");

module.exports = defineConfig({
  reporter: 'cypress-mochawesome-reporter',
  reporterOptions: {
    reportDir: 'mochawesome-report',
    overwrite: false,
    html: false,
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
  }
});