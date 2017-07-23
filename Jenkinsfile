node { 

    stage('build') {             echo 'BUILDING'     }      stage('test') {             echo 'TESTING'     }      parallel(     	stage('deploy1') {                 echo 'Deploy in staging'         },          stage('deploy2') {                 echo 'DEPLOY in production'         }     )  }