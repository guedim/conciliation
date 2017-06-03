pipeline {
  agent any
  stages {
    stage('test') {
      steps {
        echo 'Hello World mundo'
      }
    }
    stage('paso') {
      steps {
        echo 'saludo hola mundo'
      }
    }
  }
  environment {
    defenition = '123'
    istru = 'false'
  }
  post {
    always {
      echo 'I will always say Hello again!'
      
    }
    
  }
}