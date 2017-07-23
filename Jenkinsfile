
    stage('build') {
        sh './gradlew clean'
        sh './gradlew build'

    }

    stage('test') {
        sh './gradlew check'
    }

    stage('deploy') {
        emailext body: 'test', subject: 'test', to: 'jessica.alaraye@viseo.com'
    }


