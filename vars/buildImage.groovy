#!/user/bin/env groovy

def call() {

    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh """
            docker build -t ztech101/nana-docker-image:jma-${env.BUILD_NUMBER} .
            echo \$PASS | docker login -u \$USER --password-stdin
            docker push ztech101/nana-docker-image:jma-${env.BUILD_NUMBER}
        """
    }
}