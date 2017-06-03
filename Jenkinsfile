pipeline {
  agent any
  stages {
    stage('test') {
      steps {
        echo 'Hello World mundo'
      }
    }
  }
  post {
    always {
      echo 'I will always say Hello again!'
      
    }
    
  }
}