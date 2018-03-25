#!/usr/bin/env groovy

@Library('jenkins-global-libraries') _

def deploy(String projectDeployDirectory, String destination) {
  def deployUtilities = new com.boeing.da.helix.pipeline.DeployUtilities()
  
  String[] tokens = destination.tokenize(":")
  String platform = tokens[0].toLowerCase()
  String environment = tokens[1].toLowerCase()
 
  String deployPlaybookPath = "deployment/deploy-playbook.yml"

// existing ansible scripts need absolute paths
//  String swaggerJsonPath = "${WORKSPACE}/${projectDeployDirectory}/deliverables/swagger.json"
//  String apiPolicyPath = "${WORKSPACE}/${projectDeployDirectory}/deliverables/api-policy.xml"

  echo "workspace = ${WORKSPACE}"
  echo "project deploy directory = ${projectDeployDirectory}"
  echo "destination = ${destination}"
  echo "platform = ${platform}"
  echo "environment = ${environment}"
  echo "deploy playbook file = ${deployPlaybookPath}"

  dir (projectDeployDirectory) {
    echo "pwd = " + pwd()
    withEnv(["WORKSPACE=${WORKSPACE}",
             "PLATFORM=${platform}",
             "ENVIRONMENT=${environment}",
             "PROJECT_DEPLOY_SCRIPT_DIRECTORY=${WORKSPACE}/${PROJECT_DEPLOY_SCRIPT_DIRECTORY}",
             "WAR_FILE_PATH=${WORKSPACE}/${projectDeployDirectory}/deliverables/wsisun.war",
             "WEB_CONFIG_FILE_PATH=${WORKSPACE}/${projectDeployDirectory}/deliverables/web.config",
             "DISTRIBUTION_DIRECTORY=${WORKSPACE}/${projectDeployDirectory}",
             "REVISION_FILE=metadata/build.revision",
             "ANSIBLE_VAULT_TOKEN=${ANSIBLE_VAULT_TOKEN}",
             "DEPLOY_PLAYBOOK_FILE=${deployPlaybookPath}"]) {



      sh '''#!/bin/bash
            
      set -x -e
      
      bash ${PROJECT_DEPLOY_SCRIPT_DIRECTORY}/scripts/${PLATFORM}/deploy/webapp.sh
     
      '''
    }
  }
}

return this
