pipeline {
    agent any

    stages {
        stage('Run Cypress Tests') {
            steps {
                echo 'Triggering the Cypress pipeline...'
                build job: 'huntcareer-ui-tests-cypress', wait: true
            }
        }

        stage('Run Playwright Tests') {
            steps {
                echo 'Triggering the Playwright pipeline...'
                build job: 'huntcareer-ui-tests-playwright', wait: true
            }
        }

        stage('Run TestNG Tests') {
            steps {
                echo 'Triggering the TestNG pipeline...'
                build job: 'huntcareer-ui-tests-selenium', wait: true
            }
        }
    }

    post {
        always {
            echo 'Master pipeline finished.'
        }
        success {
            echo 'All pipelines executed successfully.'
        }
        failure {
            echo 'One of the downstream pipelines failed.'
        }
        unstable {
            echo 'One of the downstream pipelines is unstable.'
        }
    }
}
