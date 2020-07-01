node {
    stage 'Clone the project'
    git 'https://github.com/frknpg/MobileTesting.git'
    
    stage("Set Configs") {
        jsonfile = readJSON file: 'src/test/resources/config/appium.json'
        jsonfile['appiumUrl'] = 'http://fc7b43c6874f.ngrok.io/wd/hub'
        writeJSON file: 'src/test/resources/config/appium.json', json: jsonfile
    }

    stage("Runing tests") {
        sh "chmod +x gradlew"
        try {
            sh "./gradlew test -Dcucumber.options='--tags \"@start\"'"
        } catch(err) {
            throw err
        } 
    }
    
    stage("Collect Result") {
        try {
            script {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'build/allure-results']]
                ])
            }
        } catch(err) {
            throw err
        }
    }
}