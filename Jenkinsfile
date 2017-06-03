pipeline {
  agent any
  stages {
    stage('ejemplo') {
      steps {
        echo 'Hello  mundo 1'
        echo 'Hello  mundo 2'
      }
    }
  }
  post {
    always {
      echo 'I will always say Hello again!'    
    }
  }
}
