package com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters;


import com.advertising_id_service.appclick.googleadvertisingidservice.InstallationInfo;
// @param installInfo     - объект содержащий информацию по инсталяции
public class InstallParameters extends  BaseInputParameters<InstallParameters> {
    private InstallationInfo installInfo;

    public InstallationInfo getInstallInfo() {
        return installInfo;
    }

    public InstallParameters setInstallInfo(InstallationInfo installInfo) {
        this.installInfo = installInfo;
        return this;
    }
}
