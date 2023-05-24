pipeline {
    stages {
        stage('Build') {
            git branch: 'master', url: 'https://github.com/lszypski/WebApp'
            sh 'mvn clean package -DskipTests'
        }
        stage('Test') {
            sh 'mvn test'
        }
        stage('Deploy') {
            sh 'java -jar WebApp.jar'
        }
    }
}
