// ***********************************************************
// This example support/e2e.js is processed and
// loaded automatically before your test files.
//
// This is a great place to put global configuration and
// behavior that modifies Cypress.
//
// You can change the location of this file or turn off
// automatically serving support files with the
// 'supportFile' configuration option.
//
// You can read more here:
// https://on.cypress.io/configuration
// ***********************************************************

// Import commands.js using ES2015 syntax:
import './commands'
import '@shelex/cypress-allure-plugin';
import 'cypress-mochawesome-reporter/register';

Cypress.on('uncaught:exception', (err, runnable) => {
    // Log the error to the Cypress command log for visibility
    Cypress.log({
        name: 'Uncaught Exception',
        message: err.message,
        consoleProps: () => {
            return {
                Error: err,
                Runnable: runnable
            };
        }
    });
    
    // Example: Only ignore a very specific, known benign error.
    // Replace this condition with actual known benign errors if any.
    // If there are none, the best practice is often to REMOVE this handler entirely
    // or `return true;` to let tests fail on any uncaught app exception.
    if (err.message.includes('ignore_this_specific_error_if_it_exists')) {
        return false; // Don't fail the test for this specific error
    }
    // For most other uncaught exceptions, it's better to let Cypress fail the test.
    // However, to maintain previous behavior of ignoring all for now (while recommending removal),
    // this example will still return false, but with a strong recommendation to make it more strict.
    // For a stricter approach, this should be `return true;`
    console.warn('Warning: An uncaught application exception was ignored. Review if this is expected. Error: ' + err.message);
    return false; // TEMPORARY: Keeps old behavior of ignoring all, but logs it.
                  // STRONGLY RECOMMENDED: Change to 'return true;' or remove handler 
                  // unless specific benign errors are being filtered.
  });
  