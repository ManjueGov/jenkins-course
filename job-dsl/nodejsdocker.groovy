job('NodeJS Docker example') {
    scm {
        git('https://github.com/ManjueGov/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('ManjueGov')
            node / gitConfigEmail('manjunatha.s@egovernments.org')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('manju4d90/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
