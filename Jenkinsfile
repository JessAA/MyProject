pipeline {
    agent any
    stages {
        stage('first stage'){
            steps{
                sh 'echo "First stage"'
            }

        }

        stage('second stage'){
            steps{
                sh 'echo "Second stage"'
            }

        }
        stage('third stage') {
            steps {
                // Note that parallel can only be used as the only step for a stage.
                // Also, if you want to have your parallel branches run on different
                // nodes, you'll need control that manually with "node('some-label') {"
                // blocks inside the parallel branches, and per-stage post won't be able
                // to see anything from the parallel workspaces.
                // This'll be improved by https://issues.jenkins-ci.org/browse/JENKINS-41334,
                // which adds Declarative-specific syntax for parallel stage execution.
                parallel(
                        one: {
                            echo "I'm on the first branch!"
                            sh 'pwd'
                        },
                        two: {
                            echo "I'm on the second branch!"
                        },
                        three: {
                            echo "I'm on the third branch!"
                            echo "But you probably guessed that already."
                        })
            }
        }

        stage('end'){
            steps{
                sh 'echo "This is the end"'
            }
        }

    }

}