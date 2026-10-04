pipeline {
    agent any

    environment {
        ALPHA_VANTAGE_API_KEY = credentials('alpha-vantage-api-key')
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Environment') {
            steps {
                sh 'java -version'
                sh '/opt/homebrew/bin/mvn -version'
            }
        }

        stage('Test') {
            steps {
                sh '/opt/homebrew/bin/mvn clean test'
            }
        }

        stage('Package') {
            steps {
                sh '/opt/homebrew/bin/mvn package -DskipTests'
            }
        }

        stage('Run Application') {
            steps {
                sh '''
                    java -jar target/stock-indicator-client-1.0.0.jar IBM
                '''
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true

                archiveArtifacts artifacts: 'output/*.json,output/*.csv',
                                 allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}