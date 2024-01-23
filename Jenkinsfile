pipeline{
    agent {
        label 'jenkins_slave'
    }
    
    tools{
        maven 'Maven 3.9.6'
    }
    environment {
            imageName = "venkat7010/springboot-devsecops"
            dockerImage = ''
            dockerImageTag = "${BUILD_NUMBER}"
        }
    stages{
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Checkout App'){
            steps{
                echo "before checkin out"
                git branch: 'main', url: 'https://github.com/venkat5290/spring_devsecops_demo'
                echo "After checking out"
            }
        }
        stage('Maven Build'){
            steps{
                sh '''
                    mvn --version
                    echo "Maven present"
                    mvn clean package
                '''
            }
        }
        stage('Sonarqube Analysis'){
             steps {
                withSonarQubeEnv('Sonarqube-10.3') { 
                    sh "mvn sonar:sonar -Dsonar.projectKey='SpringBoot_Devsecops' -Dsonar.projectName='SpringBoot_Devsecops'"
                }
            }
        }
        stage("Quality Gate Check") {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                    }
                }
        }
        
        stage('Owasp_Dependency_Check') {
            steps {
                    dependencyCheck additionalArguments: ''' 
                                    -o './'
                                    -s './'
                                    -f 'ALL' 
                                    --prettyPrint''', odcInstallation: 'Owasp_Dependency_Check'
                                    
                    dependencyCheckPublisher pattern: 'dependency-check-report.xml'
                }
        }
        
        stage("build and push"){
            steps {
                script {
                    dockerImage = docker.build(imageName)
                    println "Image Created is:${dockerImage}"
                    echo "Docker Image built succesfully"
                    }
                }
        }

        stage("push to registry"){
            steps {
                script {
                    withDockerRegistry(credentialsId: 'docker') {
                        println "pushing docker image to dockerhub"
                        dockerImage.push("${dockerImageTag}")
                        dockerImage.push('latest')
                        println "Docker Image pushed Succesfully"
                        }
                    }
                }
        }
        stage('Trivy Scan') {
            steps {
                script {
                    sh """trivy image venkat7010/springboot-devsecops:latest -o report.html """
                    
                }
                
            }
        }
        stage('Delete Docker Images'){
            steps {
                sh "docker rmi ${imageName}:${dockerImageTag}"
                sh "docker rmi ${imageName}:latest"
            }
        }
        stage('Trigger CD Pipeline') {
            steps {
                sh "curl -v -k --user admin:11675ede32147a4f922845301b5fce4dd6 -X POST -H 'cache-control: no-cache' -H  'content-type: application/x-www-form-urlencoded' --data 'IMAGE_TAG=${dockerImageTag}' 'http://35.224.92.110:8080/job/SpringBoot_DevSecops_CDPipeline/buildWithParameters?token=gitops-config-update'"
            }
        }
    }
}