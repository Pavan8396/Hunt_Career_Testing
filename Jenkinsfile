pipeline {
    agent any

    stages {
        stage('Run Cypress Tests') {
            steps {
                echo '----------------------------------------'
                echo '        TRIGGERING CYPRESS PIPELINE'
                echo '----------------------------------------'
                build job: 'huntcareer-ui-tests-cypress', wait: true
            }
        }

        stage('Run Playwright Tests') {
            steps {
                echo '----------------------------------------'
                echo '      TRIGGERING PLAYWRIGHT PIPELINE'
                echo '----------------------------------------'
                build job: 'huntcareer-ui-tests-playwright', wait: true
            }
        }

        stage('Run TestNG Tests') {
            steps {
                echo '----------------------------------------'
                echo '         TRIGGERING TESTNG PIPELINE'
                echo '----------------------------------------'
                build job: 'huntcareer-ui-tests-selenium', wait: true
            }
        }
    }

    post {
        always {
            echo '----------------------------------------'
            echo '          MASTER PIPELINE FINISHED'
            echo '----------------------------------------'
        }
        success {
            echo 'All downstream pipelines executed successfully.'
        }
        failure {
            echo 'One of the downstream pipelines failed.'
        }
        unstable {
            echo 'One of the downstream pipelines is unstable.'
        }
    }
}
