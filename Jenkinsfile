pipeline {
    agent {
    }
    stages {
        stage('Build') {
            steps {
                git branch: 'master', url: 'https://github.com/lszypski/WebApp'
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'java -jar WebApp.jar'
            }
        }
    }
}
