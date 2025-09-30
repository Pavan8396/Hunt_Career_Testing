// Function to print a standardized banner
def printBanner(message) {
    echo '----------------------------------------'
    echo "    ${message}"
    echo '----------------------------------------'
}

pipeline {
    agent any

    stages {
        stage('Triggering Downstream Pipelines') {
            steps {
                script {
                    // Define the downstream jobs
                    def jobs = [
                        'Cypress': 'huntcareer-ui-tests-cypress',
                        'Playwright': 'huntcareer-ui-tests-playwright',
                        'TestNG': 'huntcareer-ui-tests-selenium'
                    ]

                    // Trigger jobs in parallel
                    parallel jobs.collectEntries { jobName, jobPath ->
                        ["${jobName} Tests": {
                            stage("Run ${jobName} Tests") {
                                printBanner("TRIGGERING ${jobName.toUpperCase()} PIPELINE")
                                build job: jobPath, wait: true
                            }
                        }]
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
        failure {
            echo '❌ One or more downstream pipelines failed.'
        }
        unstable {
            echo '⚠️ One or more downstream pipelines are unstable.'
        }
    }
}
