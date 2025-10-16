// Function to print a standardized banner
def printBanner(message) {
    echo '----------------------------------------'
    echo "    ${message}"
    echo '----------------------------------------'
}

pipeline {
    agent any

    parameters {
        string(name: 'CYPRESS_JOB_NAME', defaultValue: 'huntcareer-ui-tests-cypress', description: 'Name of the Cypress downstream job')
        string(name: 'PLAYWRIGHT_JOB_NAME', defaultValue: 'huntcareer-ui-tests-playwright', description: 'Name of the Playwright downstream job')
        string(name: 'TESTNG_JOB_NAME', defaultValue: 'huntcareer-ui-tests-selenium', description: 'Name of the TestNG downstream job')
    }

    stages {
        stage('Clean Workspace') {
            steps {
                printBanner('CLEANING WORKSPACE')
                cleanWs()
            }
        }

        stage('Trigger Cypress Pipeline') {
            steps {
                script {
                    printBanner("TRIGGERING CYPRESS PIPELINE")
                    try {
                        def result = build job: params.CYPRESS_JOB_NAME, wait: true
                        echo "✅ Cypress pipeline finished with result: ${result.result}"
                    } catch (err) {
                        echo "⚠️ Cypress pipeline failed: ${err.message}"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Trigger Playwright Pipeline') {
            steps {
                script {
                    printBanner("TRIGGERING PLAYWRIGHT PIPELINE")
                    try {
                        def result = build job: params.PLAYWRIGHT_JOB_NAME, wait: true
                        echo "✅ Playwright pipeline finished with result: ${result.result}"
                    } catch (err) {
                        echo "⚠️ Playwright pipeline failed: ${err.message}"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Trigger TestNG Pipeline') {
            steps {
                script {
                    printBanner("TRIGGERING TESTNG PIPELINE")
                    try {
                        def result = build job: params.TESTNG_JOB_NAME, wait: true
                        echo "✅ TestNG pipeline finished with result: ${result.result}"
                    } catch (err) {
                        echo "⚠️ TestNG pipeline failed: ${err.message}"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
    }

    post {
        always {
            printBanner('MASTER PIPELINE FINISHED')
        }
        success {
            echo '✅ All downstream pipelines executed successfully.'
        }
        unstable {
            echo '⚠️ One or more downstream pipelines are unstable.'
        }
        failure {
            echo '❌ One or more downstream pipelines failed.'
        }
    }
}
