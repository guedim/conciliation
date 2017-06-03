pipeline {
    agent any
    stages {
        stage('Example') {
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
