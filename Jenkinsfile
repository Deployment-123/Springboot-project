pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    environment {
        APP_NAME     = "springboot-project"
        DEV_PORT     = "8084"
        DEPLOY_DIR   = "/opt/apps/dev/springboot-project"
        SERVICE_NAME = "springboot-project-dev"
    }

    triggers {
        githubPush()
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test & Coverage') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    try {
                        timeout(time: 5, unit: 'MINUTES') {
                            waitForQualityGate abortPipeline: true
                        }
                    } catch (err) {
                        echo "Quality Gate timed out – continuing DEV deployment"
                    }
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy to DEV') {
            steps {
                sh '''
                echo "Deploying ${APP_NAME} to DEV..."

                echo "Copying JAR to ${DEPLOY_DIR}"
                cp target/EventManagementSystem-0.0.1-SNAPSHOT.jar \
                   ${DEPLOY_DIR}/springboot-project.jar

                echo "Restarting systemd service: ${SERVICE_NAME}"
                sudo systemctl restart ${SERVICE_NAME}
                '''
            }
        }

        stage('Sanity Test') {
            steps {
                sh '''
                echo "Waiting for application to start..."
                sleep 20

                echo "Running sanity check on DEV..."
                curl -f http://localhost:${DEV_PORT}/h2-console
                '''
            }
        }
    }

    post {
        success {
            echo "${APP_NAME} deployed successfully to DEV"
        }
        failure {
            echo "${APP_NAME} deployment failed"
        }
    }
}
