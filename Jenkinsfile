
    stage('build') { 

 echo 'BUILDING'     }  

 stage('test') { 
 echo 'TESTING'     }  
 parallel( stage('deploy') { 

 echo 'Deploy in staging' 
 },  
 stage('deploy') { 
    echo 'DEPLOY in production' 
 } 
)

