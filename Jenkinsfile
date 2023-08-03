pipeline {
    agent any
   
    stages {
       
        stage('Build') {
            steps {
          
             def mvnHome = tool name: 'Maven', type: 'maven'
                sh "${mvnHome}/bin/mvn package"
                }
            }
        }
     }
