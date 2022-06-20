pipeline {
   agent any
   environment {
       registry = "registry.homelab.com/market-price"
   }
   stages {
       stage('Build Dockerfile and Publish') {
           steps{
               script {
                   docker.withRegistry( 'https://registry.homelab.com', 'docker-creds' ) {
                       appimage.push()
                       appimage.push('latest')
                   }
               }
           }
       }
      stage ('Deploy') {
           steps {
               script{
                   def image_id = "registry.homelab.com/market-price" + ":$BUILD_NUMBER"
                   sh "ansible-playbook  playbook.yml --extra-vars \"image=${image_id}\""
               }
           }
       }
   }
}
