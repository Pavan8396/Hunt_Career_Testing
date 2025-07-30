pipeline {
    agent any

    tools {
        nodejs 'NodeJS'
        maven 'MAVEN'
        jdk 'JAVA'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }

        stage('Run Cypress Tests') {
            steps {
                dir('Hunt_Career_Cypress') {
                    script {
                        try {
                            echo 'Installing npm dependencies for Cypress...'
                            bat 'npm install'
                            echo 'Running Cypress tests and generating report...'
                            bat 'npm run test:run:report'
                        } catch (any) {
                            currentBuild.result = 'UNSTABLE'
                            echo "Cypress tests execution failed or reported errors. Build set to UNSTABLE."
                        }
                    }
                }
            }
        }

        stage('Run Playwright Tests') {
            steps {
                dir('Hunt_Career_Playwright') {
                    script {
                        try {
                            echo 'Installing npm dependencies for Playwright...'
                            bat 'npm install'
                            echo 'Installing Playwright browsers...'
                            bat 'npx playwright install --with-deps'
                            echo 'Running Playwright tests...'
                            bat 'npx playwright test'
                        } catch (any) {
                            currentBuild.result = 'UNSTABLE'
                            echo "Playwright tests execution failed or reported errors. Build set to UNSTABLE."
                        }
                    }
                }
            }
        }

        stage('Run TestNG Tests') {
            steps {
                dir('Hunt_Career_TestNG_Hybrid_Framework') {
                    script {
                        try {
                            echo 'Building the TestNG project...'
                            bat 'mvn -B clean install -DskipTests'
                            echo 'Running TestNG tests...'
                            bat 'mvn -B test'
                        } catch (any) {
                            currentBuild.result = 'UNSTABLE'
                            echo "TestNG tests execution failed or reported errors. Build set to UNSTABLE."
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished. Archiving artifacts and publishing reports...'

            // Cypress reports
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'Hunt_Career_Cypress/mochawesome-report',
                reportFiles: 'mochawesome.html',
                reportName: 'Cypress Test Report'
            ])
            archiveArtifacts artifacts: 'Hunt_Career_Cypress/mochawesome-report/mochawesome*.html', allowEmptyArchive: true
            archiveArtifacts artifacts: 'Hunt_Career_Cypress/cypress/screenshots/**/*.png', allowEmptyArchive: true
            archiveArtifacts artifacts: 'Hunt_Career_Cypress/cypress/videos/**/*.mp4', allowEmptyArchive: true

            // Playwright reports
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'Hunt_Career_Playwright/playwright-report',
                reportFiles: 'index.html',
                reportName: 'Playwright Test Report'
            ])
            archiveArtifacts artifacts: 'Hunt_Career_Playwright/playwright-report/**/*', allowEmptyArchive: true

            // TestNG reports
            junit testResults: 'Hunt_Career_TestNG_Hybrid_Framework/test-output/testng-results.xml', allowEmptyResults: true
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'Hunt_Career_TestNG_Hybrid_Framework/test-output/ExtentReports',
                reportFiles: 'extentReport.html',
                reportName: 'Extent Test Report'
            ])
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'Hunt_Career_TestNG_Hybrid_Framework/test-output',
                reportFiles: 'emailable-report.html',
                reportName: 'TestNG Emailable Report'
            ])
            archiveArtifacts artifacts: 'Hunt_Career_TestNG_Hybrid_Framework/test-output/**/*', allowEmptyArchive: true
        }
        success {
            echo 'All frameworks executed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
        unstable {
            echo 'One or more frameworks reported test failures.'
        }
    }
}
