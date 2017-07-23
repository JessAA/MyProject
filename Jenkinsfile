pipeline {

    agent any

    stages {
        stage('build') {
            steps {

                sh 'pwd'
                ArtifactoryGradleBuild buildFile: 'build.gradle', rootDir: '', switches: '', tasks: 'artifactoryPublish', tool: '', useWrapper: false, usesPlugin: false

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