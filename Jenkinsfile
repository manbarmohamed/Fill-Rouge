pipeline {
    agent any

    tools {
        maven 'Maven 3.6.3'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerHubCredentials')
        SONARQUBE_TOKEN = 'squ_5ced8b7ed33f5f17689d407b64bd58c72c2800b1'
        SONARQUBE_SERVER = 'http://localhost:9000'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/manbarmohamed/Fill-Rouge.git'
            }
        }

        stage('Build & Test filRouge') {
            steps {

                    withMaven(maven: 'Maven 3.6.3') {
                        bat 'mvn clean install'

                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarQubeScanner'

                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=. -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"

                }
            }
        }

//         stage('Build Docker Image & Push') {
//             steps {
//                 dir('BackEnd') {
//                     script {
//                         try {
//                             bat 'docker context use default'
//
//                             def imageTag = env.TAG_VERSION ?: 'latest'
//                             def dockerImage = docker.build("zinebaz/filrouge:${imageTag}")
//
//                             docker.withRegistry('https://index.docker.io/v1/', 'dockerHubCredentials') {
//                                 dockerImage.push()
//                             }
//                         } catch (Exception e) {
//                             error "Docker build or push failed: ${e.message}"
//                         }
//                     }
//                 }
//             }
//         }
//
//         stage('Deploy with Docker Compose') {
//             steps {
//                 dir('BackEnd') {
//                     bat 'docker-compose up -d'
//                 }
//             }
//         }
    }
}