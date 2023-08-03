pipeline {
    agent any
    
    stages {
       
        stage('Build') {
            steps {
          def mvnhm=tool name: 'Maven', type: 'maven'
             sh '$(mvnhm)/bin/mvn install'
                }
            }
        }
     }
