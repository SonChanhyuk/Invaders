node {
    stage ('checkout') {
        checkout scm
        echo 'pull' + env.BRANCH_NAME
    }

    stage ('build') {
        echo 'gradle build start'
        sh './gradlew clean build'
    }

    stage ('deploy') {
        echo 'list of items to deploy'
        sh 'ls build/libs'
        echo 'process - deploy with docker'
    }

    post {
        always {
            emailext body: 'successfully build', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Jenkins Alert'
        }
    }
}