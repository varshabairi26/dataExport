pipeline {
    agent any

    stages {
        stage ('Compile Stage'){
            withMaven(maven : 'maven_4_0_0') {
                sh 'mvn clean compile'
            }
        }
    }
}