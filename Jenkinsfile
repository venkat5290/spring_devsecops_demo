pipeline{
    agent any
    tools{
        maven 'Maven 3.9.6'
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
    }
}