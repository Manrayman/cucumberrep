pipeline {
    agent any
    
    stages {
       
        stage('Build') {
            steps {
            tool name: 'Maven', type: 'maven'
             sh 'mvn clean install'
                }
            }
        }
     }
