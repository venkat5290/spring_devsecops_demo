# spring_devsecops_demo

* Basic spring app with endpoints /hello and /health

* Test cases are written in Junit5.

* Application was dockerized and available at port 8090

* Jenkinsfile contains stages like git scm,Maven Build, Sonarqube SCA,OWASP Dependency Check,Docker Build, Trivy Scan,Docker push to Dockerhub,Deleting local docker images and finally triggering cd pipeline.

* Image ID is passed as a parameter to Gitops Pipeline
