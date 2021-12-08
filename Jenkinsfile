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

    stage ('alert') {
        emailext attachLog: true, attachmentsPattern: 'build/libs/Invaders.jar', body: 'successfully build', to: 'changsun19991212@gmail.com', subject: 'Jenkins Alert'
    }
}