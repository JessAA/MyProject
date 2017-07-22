pipeline {

    agent { docker 'maven:3.3.3' }

    stages {
        stage('build') {
            steps {
                sh 'echo "Building"'
            }
        }

        stage ('Deploy'){
            steps {
                sh 'echo "DEPLOYING"'
            }
        }

        stage('Test'){
            steps {
                sh 'echo "TESTING"'
            }
        }
    }
}