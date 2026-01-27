pipeline {
    agent any

    environment {
        // Optional: set JAVA_HOME if needed
        JAVA_HOME = "${tool 'JDK17'}"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull code from GitHub
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Use Gradle wrapper to clean, build, and run tests
                sh './gradlew clean build'
            }
        }

        stage('Archive Artifact') {
            steps {
                // Archive generated JAR(s) so they can be downloaded from Jenkins
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

