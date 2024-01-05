pipeline{
    agent any
    tools{
        maven 'Maven 3.9.6'
    }
    environment {
            imageName = "venkat7010/springboot-devsecops"
            dockerImage = ''
            dockerImageTag = "${imageName}:${BUILD_NUMBER}"
        }
    stages{
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
                    sh "mvn sonar:sonar -Dsonar.projectKey=SpringBoot_App -Dsonar.projectName='SpringBoot_App'"
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
                    echo "Building docker image..."
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
                        dockerImage.push("$BUILD_NUMBER")
                        dockerImage.push('latest')
                        println "Docker Image pushed Succesfully"
                        }
                    }
                }
        }
    }
}