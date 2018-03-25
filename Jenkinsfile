#!/usr/bin/env groovy

@Library('jenkins-global-libraries') _

pipeline {
    agent any

    stages {
        stage('Init') {
            steps {
                echo 'Initing...'
                sayHello 'Phuc Tran The'
            }
        }
        stage('Build') {
            steps {
                echo 'Building...'
            }
        }
        stage('Package') {
            steps {
                echo 'Packaging...'
            }
        }
        stage('PreDeploy') {
            steps {
                echo 'PreDeploying....'
            }
        }
        stage('Deploy (Dev)') {
            steps {
                echo 'Deploying...'
                sayGoodbye 'Phuc Tran The'
            }
        }
    }
}
