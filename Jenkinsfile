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
   }



}
