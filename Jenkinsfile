pipeline {
   agent any
   environment {
       registry = "labmain:32000/market-price"
   }
   stages {
       stage('Build Dockerfile and Publish') {
           steps{
               script {
                   def appimage = docker.build registry + ":$BUILD_NUMBER"
                   docker.withRegistry( '', '' ) {
                       appimage.push()
                       appimage.push('latest')
                   }
               }
           }
       }
      stage ('Deploy') {
           steps {
               script{
                   def image_id = "localhost:32000/market-price" + ":$BUILD_NUMBER"
                   sh "ansible-playbook  playbook.yml --extra-vars \"image=${image_id}\""
               }
           }
       }
   }
}
