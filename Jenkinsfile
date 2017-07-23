pipeline {

    agent any

    stages {
        stage('build') {
            steps {

                sh 'pwd'
                echo '$PATH'
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