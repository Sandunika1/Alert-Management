pipeline {
    //this jdk need to be added in jenkins level Jenkins -> Manage Jenkins -> Global Tool Configuration -> jdk section
    // https://stackoverflow.com/questions/56842222/specify-java-home-in-jenkins-for-java-11
    env.JAVA_HOME="${tool 'jdk1.8'}"
    env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
    agent any
    tools {
        maven 'maven'
        jdk 'jdk1.8'
    }
    stages {
       stage('Preparation') {
           steps {
               echo 'Preparing test run'
               git credentialsId: 'Jenkins_bitbucket', url: 'https://jenkinsbst@bitbucket.org/bst_element/ui_test_automation.git'
            }
        }
        stage('Run tests') {
            steps {
               echo 'Executing tests'
               sh 'mvn clean install'
            }
        }
         stage('Publishing results') {
            steps {
                step([$class: 'Publisher', reportFilenamePattern: '**/surefire-reports/testng-results.xml'])
            }
        }
    }
}
