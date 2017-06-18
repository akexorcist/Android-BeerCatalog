package com.akexorcist.beercatalog.api;

import com.akexorcist.beercatalog.api.base.BaseService;

/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public class NetworkService extends BaseService<NetworkApi> {
    public static NetworkService newInstance(String baseUrl) {
        NetworkService service = new NetworkService();
        service.setBaseUrl(baseUrl);
        return service;
    }

    private NetworkService() {
    }

    @Override
    protected Class<NetworkApi> getApiClassType() {
        return NetworkApi.class;
    }
}
