pipeline{
    agent any
    tools{
        maven 'maven_3_9_9'
    }
    stages{
        stage('checkout'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/tejaswini-arc/springboot-first-app']])
                 }
        }
        stage('Build'){
            steps{
                 bat 'mvn clean install'
            }
        }
        stage('verify docker'){
            steps{
                bat 'docker --version'
            }
        }
        stage('Build Docker image'){
            steps{
                bat 'docker build -t springboot-first-app .'
                }
            }
            stage('Push image Hub'){
                steps{
                    withCredentials([string(credentialsId: 'docker-hubs-ID', variable: 'docker-jenkins-pwd')]) {
    // some block
                bat 'docker login -u tejaswiini -p %docker-jenkins-pwd%'
                bat 'docker build -t tejaswiini/springboot-first-app .'
                bat 'docker push tejaswiini/springboot-first-app'

                        }
                }
            }
        }
    }