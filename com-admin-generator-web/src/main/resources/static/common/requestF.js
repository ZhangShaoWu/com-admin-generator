const requestF = {};


/**
 * Created by ZSW on 2019/2/28.
 */
/**
 * 通过json传参的post请求
 * @param url
 * @param data
 * @param cb
 */
requestF.postJson = function (url, data, cb) {
    request.postJson(url,data,requestF.getHeaders(),cb);
};

/**
 * 通过formdata传参的post请求
 * @param url
 * @param data
 * @param cb
 */
requestF.postForm = function (url, data, cb) {
    request.postForm(url,data,requestF.getHeaders(),cb);
};


/**
 * 通过formdata传参的post请求
 * @param url
 * @param data
 * @param cb
 */
requestF.postFormAsync = function (url, data, cb) {
    request.postFormSync(url,data,requestF.getHeaders(),cb);
};

/**
 * 通过json传参的get请求
 * @param url
 * @param data
 * @param cb
 */

requestF.getJson = function (url, data, cb) {
    let headers = requestF.getHeaders();
    request.get(url,data,headers,cb);
};
/**
 * 通过json传参的get请求(同步方式)
 * @param url
 * @param data
 * @param cb
 */
requestF.getJsonAsync = function(url, data, cb){
    request.getSync(url, data,requestF.getHeaders(), cb);
}

requestF.download = function (url, data) {
    request.download(url,data,requestF.getHeaders());
}

requestF.getHeaders = function() {
    return {
        "AdminAuthentication":""
    };
}






