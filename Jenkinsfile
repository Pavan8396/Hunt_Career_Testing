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

        stage('Triggering Downstream Pipelines') {
            steps {
                script {
                    // Define the downstream jobs
                    def jobs = [
                        'Cypress': "${params.CYPRESS_JOB_NAME}",
                        'Playwright': "${params.PLAYWRIGHT_JOB_NAME}",
                        'TestNG': "${params.TESTNG_JOB_NAME}"
                    ]

                    // Trigger jobs in parallel, failing fast
                    parallel(jobs.collectEntries { jobName, jobPath ->
                        ["${jobName} Tests": {
                            stage("Run ${jobName} Tests") {
                                printBanner("TRIGGERING ${jobName.toUpperCase()} PIPELINE")
                                build job: jobPath, wait: true
                            }
                        }]
                    } + [failFast: true])
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
        failure {
            echo '❌ One or more downstream pipelines failed.'
        }
        unstable {
            echo '⚠️ One or more downstream pipelines are unstable.'
        }
    }
}
