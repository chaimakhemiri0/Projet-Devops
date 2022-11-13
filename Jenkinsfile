pipeline {
    agent {label 'maven'} 
  
    tools {
        maven 'M2_HOME' 
    }
    environment {
            DOCKERHUB_CREDENTIALS=credentials('dockerhub')
            // This can work with nexus3 or nexus2
            NEXUS_VERSION = "nexus3"
            // This can be http or https
            NEXUS_PROTOCOL = "http"
            // Where your Nexus is running
            NEXUS_URL = "192.168.1.117:8081"
            // Nexus repository where we will upload the artifact
            NEXUS_REPOSITORY = "maven-releases"
            // Jenkins credential id to authenticate to Nexus
            NEXUS_CREDENTIAL_ID = "dd1c4f8a-2579-4e75-84a6-486e5e347d18"
    }
     
    stages {
          		
        stage('Compilation du Projet'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Eyakaabachi/DevOps-Project.git']]])
             sh 'mvn clean install'
            }  
        }
        
        stage('Package') {
            steps {
                sh 'mvn package'
                // bat '.\\mvnw package'
            } 
        }
       
      	
        stage('MOCKITO') {
            steps {
           sh 'mvn clean test -Dtest=com.esprit.examen.services.ProduitServiceMockTest' 
            }
        }
         stage('JUNIT') {
            steps {
            sh 'mvn clean test -Dtest=com.esprit.examen.services.ProduiServiceImplTest -Dmaven.test.failure.ignore=true'  
            }
        }
        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar  -Dsonar.login=admin -Dsonar.password=eya'
                
            }
        } 
     
       
       stage('NEXUS'){
            steps{
                sh "mvn deploy -DskipTests=true"
            }
        } 

        stage('Build') {
			steps {
				sh 'docker build -t $DOCKERHUB_CREDENTIALS_USR/achat .'
			}
		}
        stage('Login') {
			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW'
			}
        }
		stage('Push') {
			steps {
				sh 'docker push $DOCKERHUB_CREDENTIALS_USR/achat'
			}
		} 
		 stage("Docker-compose") {
            steps{
                    sh 'docker-compose up -d'
                }
        }
    }
        post {
                success {
                     mail to: "eya.kaabachi@esprit.tn",
                     subject: "success",
                     body: "success on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL}"
                }
        failure {
                    mail to: "eya.kaabachi@esprit.tn",
                     subject: "Failure",
                     body: "Failure on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL} "     
                }
            }
}