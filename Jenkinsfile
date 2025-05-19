pipeline {
    agent any

    environment {
        REPORT_PATH = "target/surefire-reports"
    }

    stages {
//         stage('Checkout') {
//             steps {
//                 git 'https://github.com/NgocAnh97/NakivoTest.git'
//             }
//         }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Publish Report') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*/*.html', allowEmptyArchive: true
        }
        failure {
            echo "❌ Tests failed. Please check logs."
        }
        success {
            echo "✅ All tests passed!"
        }
    }
}
