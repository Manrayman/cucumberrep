pipeline {
    agent any
    tools{
        maven "Maven"
        jdk "JAVA"
    }
    stages {
       
        stage('Build') {
            steps {
          
            
                sh 'mvn clean install'
                }
            }
        }
     }
