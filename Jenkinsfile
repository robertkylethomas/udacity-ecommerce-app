pipeline {

  environment {
     app=null
  }

  agent any

  tools {
    maven 'Maven 3'
  }

  stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean install -U'
            }
        }
        stage('Build docker image') {
            steps {
               script {
                  app= docker.build('logging-service')
               }
            }
        }
        stage('Push docker image') {
            steps {
               script {
                  docker.withRegistry('http://196.35.119.253:5000','') {
                       app.push("$BUILD_NUMBER")
                   }
               }
            }
        }
   }



}
