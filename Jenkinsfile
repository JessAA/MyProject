pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'echo "Hello World"'
                sh '''
                    echo "Multiline shell steps works too"
                    ls -lah
                '''
            }
        }

        stage ('Deploy'){
            steps {
                      retry(3){
                      sh 'echo "DEPLOYING"'
                      }
                         timeout (time:3, unit: 'MINUTES'){
                            sh 'echo "timeout"'
                         }

                        }
        }

        stage('Test'){
          steps {
         sh 'echo "TESTING"'
        }
    }}
}