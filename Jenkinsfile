node {
    stage('build') {
            sh 'pwd'
            //sh './gradlew clean'
            //sh './gradlew build'
            echo 'BUILDING'

    }

    stage('test') {

            //sh './gradlew check'
            echo 'TESTING'

    }

    parallel(
    stage('deploy1') {

                //emailext body: 'test', subject: 'test', to: 'jessica.alaraye@viseo.com'
                echo 'Deploy in staging'

        },

        stage('deploy2') {

                        echo 'DEPLOY in production'

                }
    )

}
