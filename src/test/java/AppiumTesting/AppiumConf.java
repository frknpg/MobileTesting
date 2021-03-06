package AppiumTesting;

public class AppiumConf {

    public String appiumUrl;
    public String uuid;
    public String apkPath;

    public String getAppiumUrl() {
        return appiumUrl;
    }

    public void setAppiumUrl(String appiumUrl) {
        this.appiumUrl = appiumUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    public AppiumConf() {
        super();
    }

    @Override
    public String toString() {
        return "AppiumConf{" +
                "appiumUrl='" + appiumUrl + '\'' +
                ", uuid='" + uuid + '\'' +
                ", apkPath='" + apkPath + '\'' +
                '}';
    }
}