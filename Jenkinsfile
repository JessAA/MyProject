pipeline {

    agent any

    stages {
        stage('build') {
            steps {
                sh 'echo "Building"'
                 sh '''
                                    echo "Multiline shell steps works too"
                                    lh
                                    blabla
                                '''
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