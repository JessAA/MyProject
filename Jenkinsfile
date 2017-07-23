
    stage('build') {
        steps{
            sh './gradlew clean'
            sh './gradlew build'
        }


    }

    stage('test') {
        steps{
            sh './gradlew check'
        }

    }

    stage('deploy') {
        steps{
            emailext body: 'test', subject: 'test', to: 'jessica.alaraye@viseo.com'
        }
    }


