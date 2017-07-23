pipeline { 
 agent any
 stage('build') { 
    echo 'BUILDING' 
 }  

 stage('test') { 

    echo 'TESTING' 

 }  

 parallel( 
    stage('deploy-staging') { 
        echo 'Deploy in staging' 
    },  
    stage('deploy-production') { 
        echo 'DEPLOY in production' 
    }
  )

  }