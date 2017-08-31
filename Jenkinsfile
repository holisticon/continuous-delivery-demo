properties properties: [
  [$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '30', numToKeepStr: '10']],
  disableConcurrentBuilds()
]

timeout(60) {
  node {
    def buildNumber = env.BUILD_NUMBER
    def branchName = env.BRANCH_NAME
    def workspace = env.WORKSPACE
    def buildUrl = env.BUILD_URL
    def dockerServerUrl = 'http://localhost:41180'

    try {
      withEnv(["JAVA_HOME=${tool 'jdk-8-oracle'}", "PATH+MAVEN=${tool 'mvn latest'}/bin:${env.JAVA_HOME}/bin"]) {

        // PRINT ENVIRONMENT TO JOB
        echo "workspace directory is $workspace"
        echo "build URL is $buildUrl"
        echo "build Number is $buildNumber"
        echo "branch name is $branchName"
        echo "PATH is $env.PATH"

        stage('Checkout') {
          checkout scm
        }

        stage('Build') {
          sh "mvn package -DskipUnitTests=true"
          archiveArtifacts artifacts: '**/target/*.jar'
        }

        stage('Unit-Tests') {
          sh "mvn test -Dmaven.test.failure.ignore"
          junit healthScaleFactor: 1.0, testResults: 'angular-spring-boot-webapp/target/surefire-reports/TEST*.xml'
        }

        node('selenium') {
          withEnv(["JAVA_HOME=${tool 'jdk-8-oracle'}", "PATH+MAVEN=/usr/local/bin:${env.JAVA_HOME}/bin"]) {

            checkout scm

            stage('Prepare Docker Images') {
              // build docker images
              dir('angular-spring-boot-webapp') {
                sh "mvn package docker:build -Dmaven.test.skip"
              }
              // run images
              sh "./docker-run.sh"
              sh "echo Waiting for containers to come up"
              sh "echo -n 'wait for app to be ready '; until \$(curl --output /dev/null --silent --head --fail ${dockerServerUrl}/login); do printf '.'; sleep 5; done;"
            }


            stage('Integration-Tests') {
              dir('angular-spring-boot-webapp') {
                sh "mvn -Pdocker verify -Dmaven.test.failure.ignore -DskipUnitTests=true -DskipNode=true -Dwebdriver.base.url=${dockerServerUrl}"
                junit healthScaleFactor: 1.0, testResults: 'target/failsafe-reports/TEST*.xml'
                publishHTML(target: [
                  reportDir            : 'target/site/serenity/',
                  reportFiles          : 'index.html',
                  reportName           : 'Serenity Test Report',
                  keepAll              : true,
                  alwaysLinkToLastBuild: true,
                  allowMissing         : false
                ])
              }
            }
          }
        }
      }
    } catch (e) {
      rocketSend channel: 'holi-demos', emoji: ':rotating_light:', message: 'Fehler'
      throw e
    }
  }
}
