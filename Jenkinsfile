node {
    stage('build') {

            //sh './gradlew clean'
            //sh './gradlew build'
            echo 'BUILDING'

    }

    stage('test') {

            //sh './gradlew check'
            echo 'TESTING'

    }

    parallel(
    stage('deploy') {
                //
                emailext body: 'test', subject: 'test', to: 'jessica.alaraye@viseo.com'

        },

        stage('deploy') {
                        //
                        echo 'DEPLOYING'

                }
    )

}
