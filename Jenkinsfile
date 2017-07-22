pipeline {

    agent any

    stages {
        stage('build') {
            steps {
                sh 'echo "Building"'
                return
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