pipeline {
    agent any

    stages {
        stage('Run Cypress Tests') {
            steps {
                echo '----------------------------------------'
                echo '        TRIGGERING CYPRESS PIPELINE'
                echo '----------------------------------------'
                script {
                    catchError(buildResult: 'UNSTABLE', stageResult: 'UNSTABLE') {
                        def result = build job: 'huntcareer-ui-tests-cypress', wait: true
                        currentBuild.description = (currentBuild.description ?: '') + "\nCypress: ${result.result}"
                        currentBuild.result = result.result
                        env.CYPRESS_RESULT = result.result
                    }
                }
            }
        }

        stage('Run Playwright Tests') {
            steps {
                echo '----------------------------------------'
                echo '      TRIGGERING PLAYWRIGHT PIPELINE'
                echo '----------------------------------------'
                script {
                    catchError(buildResult: 'UNSTABLE', stageResult: 'UNSTABLE') {
                        def result = build job: 'huntcareer-ui-tests-playwright', wait: true
                        currentBuild.description = (currentBuild.description ?: '') + "\nPlaywright: ${result.result}"
                        currentBuild.result = result.result
                        env.PLAYWRIGHT_RESULT = result.result
                    }
                }
            }
        }

        stage('Run TestNG Tests') {
            steps {
                echo '----------------------------------------'
                echo '         TRIGGERING TESTNG PIPELINE'
                echo '----------------------------------------'
                script {
                    catchError(buildResult: 'UNSTABLE', stageResult: 'UNSTABLE') {
                        def result = build job: 'huntcareer-ui-tests-selenium', wait: true
                        currentBuild.description = (currentBuild.description ?: '') + "\nTestNG: ${result.result}"
                        currentBuild.result = result.result
                        env.TESTNG_RESULT = result.result
                    }
                }
            }
        }
    }

    post {
        always {
            echo '----------------------------------------'
            echo '          MASTER PIPELINE SUMMARY'
            echo '----------------------------------------'
            echo " Cypress:   ${env.CYPRESS_RESULT ?: 'NOT RUN'}"
            echo " Playwright: ${env.PLAYWRIGHT_RESULT ?: 'NOT RUN'}"
            echo " TestNG:     ${env.TESTNG_RESULT ?: 'NOT RUN'}"
        }
        success {
            echo '✅ All downstream pipelines executed successfully.'
        }
        failure {
            echo '❌ One of the downstream pipelines failed.'
        }
        unstable {
            echo '⚠️ One of the downstream pipelines is unstable.'
        }
    }
}
