pipeline {
    agent { docker 'maven:3.3.3-jdk-8' }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}