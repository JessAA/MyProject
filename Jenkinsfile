
    stage('build') {
        sh './gradlew clean'
        sh './gradlew build'

    }

    stage('test') {
        sh './gradlew check'
    }

    post {
            always {
                junit 'build/reports/**/*.xml'
            }
        }

    stage('deploy') {
        emailext body: 'test', subject: 'test', to: 'jessica.alaraye@viseo.com'
    }


