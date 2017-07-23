pipeline {

    agent any

    stages {
        stage('build') {
            steps {

                sh 'pwd'
                sh 'echo $PATH'
                sh 'ls -l | grep gradle'
                sh 'gradle clean --info'
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