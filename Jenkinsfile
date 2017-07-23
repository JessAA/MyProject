
stage('build') { 

     echo 'BUILDING' 
 }  

 stage('test') { 
    echo 'TESTING' 
 }  
 parallel(
    "deploy1":  {echo 'Deploy in staging' },
        "deploy2":  {echo 'Deploy in production' }
)

