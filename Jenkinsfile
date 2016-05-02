node {
   env.JAVA_HOME = tool 'jdk-8-oracle'
   def mvnHome = tool 'Maven 3.3.1'
   env.PATH="${env.JAVA_HOME}/bin:${mvnHome}/bin:${env.PATH}"
   
   stage 'Checkout'
   checkout scm

   stage 'Build'
   sh "${mvnHome}/bin/mvn clean package"
   
   stage 'Unit-Tests'
   sh "${mvnHome}/bin/mvn test -Dmaven.test.failure.ignore"
   
   step([
       $class: 'JUnitResultArchiver', 
       testResults: 'angular-spring-boot-webapp/target/surefire-reports/TEST*.xml'
   ])
   
   stage 'Integration-Tests'
   wrap([$class: 'Xvfb']) {
        sh "export DOCKER_HOST=tcp://localhost:4243 && ${mvnHome}/bin/mvn -Pdocker clean verify -Dmaven.test.failure.ignore"
   }
   
   step([
       $class: 'ArtifactArchiver', 
       artifacts: '**/target/*.jar', 
       fingerprint: true
    ])
   step([
       $class: 'JUnitResultArchiver', 
       testResults: 'angular-spring-boot-webapp/target/failsafe-reports/TEST*.xml'
   ])
   publishHTML(target: [
       reportDir:'angular-spring-boot-webapp/target/site/serenity/',
       reportFiles:'index.html',
       reportName:'Serenity Test Report', 
       keepAll:true, 
       alwaysLinkToLastBuild:true,
       allowMissing: false
    ])
 
}
