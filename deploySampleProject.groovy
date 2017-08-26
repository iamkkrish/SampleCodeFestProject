node {
  try {
    def config = getConfig();
	
	//Setting Environmental Variables required to build OSB Projects
    ciEnvironment.setup(config);
	
	// create test integration folder
    //def integrationTestFolder = "${env.WORKSPACE}/metadatafiles"
	
	
	//cleaning jenkins workspace
	cleanWs()
	
	//Check out latest code from SVN Repository
	stage 'Checkout'		
		checkOut(config);
	
	// Package, Version and build Project
	stage 'Build'
		//preIntegrationTest(config)
	
	//Security Scan using McAfee
	stage 'Malware Scan'
		//mcafee(config: config,resultsFile: "${env.WORKSPACE}/metadatafiles/scan_results.txt",scanDirectory: "${env.WORKSPACE}/target/*.sbar"
		)
	
	//Deploy module into DEV Environment
	stage 'Deploy to DEV'
		//deployToDEV(config: config, moduleName: moduleName);
		
	stage 'Run Integration Tests'
		//integrationTests(testFolder: integrationTestFolder, config: config)
	
	stage 'Push to Repo'
		//pushToQARepo(config: config, moduleName: moduleName)
		
	//Finish Up the build
    stage('Finish Up')
		//sendEmailNotification(config)
  } catch (Exception e) {
	  //sendEmailNotification(config)
	  throw e;
	}
}

def getConfig() {	
	return [		          
		devAppServer : [
            url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123'
		],
		qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
		qa_repo_id: 'den01cee',
		jenkinsCredentialsId : '3d7b9f9b-6467-4186-bfea-ca87d7a79107',
		svnURL: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/',
		artifact_base_version: '1.1.0.',
		OSB_emailList: "krishnaiah.kunta@oracle.com",				
		MCAFEE_HOME: '/u01/app/mcafee/',
		ORACLE_HOME : '/u01/app/oracle/middleware/product/oracle_home/',
		JDK_TOOL : 'default',
		MVN_TOOL : 'M3',
		FORTIFY_HOME: '/u01/app/HPE_Security/Fortify_SCA_and_Apps_17.10/',
		NODE_HOME: '/scratch/tools/node-v4.4.5-linux-x64/',
		emailList: "sriharish.koduri@oracle.com,krishnaiah.kunta@oracle.com, anirban.ab.banerjee@oracle.com"
	]
}


def checkOut(svnConfig) {
    echo "checking out with ${svnConfig}"
    checkout([$class: 'SubversionSCM', 
             additionalCredentials: [], 
             excludedCommitMessages: '', 
             excludedRegions: '', 
             excludedRevprop: '', 
             excludedUsers: '', 
             filterChangelog: false, 
             ignoreDirPropChanges: false, 
             includedRegions: '', 
             locations: [
                   [credentialsId: svnConfig.jenkinsCredentialsId,
                    depthOption: 'infinity', 
                    ignoreExternalsOption: true, 
                    local: svnConfig.localFolder, 
                    remote: svnConfig.svnURL]], 
                workspaceUpdater: [$class: 'UpdateUpdater']])
}

