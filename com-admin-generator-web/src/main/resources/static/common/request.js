/**
 * Created by ZSW on 2019/2/28.
 */
const request = {
    xhr :function(){
        if(window.ActiveXObject){
            return new ActiveXObject("Microsoft.XMLHTTP");
        }else if(window.XMLHttpRequest){
            return new XMLHttpRequest();
        }
    },
    complete:function(xhr,fn){
        if(xhr.readyState==4){
            if(xhr.status==200 || xhr.status==304){
                let resp = JSON.parse(xhr.responseText);
                if(!resp.success){
                    error((resp));
                    return
                }
                if(fn){
                    fn(resp);
                }
            }
        }
    },
    setHeaders:function(xhr,headers){
        if(headers){
            for(let k in headers){
                xhr.setRequestHeader(k,headers[k]);
            }
        }
    },
    ajax:function(url,method,data,headers,cb,sync){
        let xhr = request.xhr();
        xhr.open(method,url,sync);
        request.setHeaders(xhr,headers);
        xhr.onreadystatechange=function(){
            request.complete(xhr,cb)
        };
        xhr.send(data);
    },
    download:function (url, data) {
        let a = document.createElement("a");
        a.setAttribute("href",url+"?"+utils.json.format.kv(data));
        a.click();
    },
    get: function(url,data,headers,fn){
        let realUrl = url + "?" + utils.json.format.kv(data);
        request.ajax(realUrl,"GET",data,headers,fn,true);
    },
    getSync: function(url,data,headers,fn){
        let realUrl = url + "?" + utils.json.format.kv(data);
        request.ajax(realUrl,"GET",data,headers,fn,false);
    },
    postJson: function(url,data,headers,fn){
        headers = utils.json.isEmpty(headers)?{}:headers;
        headers["Content-type"] = "application/json;charset=UTF-8";
        data = JSON.stringify(data);
        request.ajax(url,"POST",data,headers,fn,true);
    },
    postJsonSync: function(url,data,headers,fn){
        headers = utils.json.isEmpty(headers)?{}:headers;
        headers["Content-type"] = "application/json;charset=UTF-8";
        data = JSON.stringify(data);
        request.ajax(url,"POST",data,headers,fn,false);
    },
    postForm: function(url,data,headers,fn){
        let param = null;
        if(data instanceof FormData){
            param = data;
        }else{
            param = utils.json.format.formData(data);
        }
        request.ajax(url,"POST",param,headers,fn,true);
    },
    postFormSync: function(url,data,headers,fn){
        let param = null;
        if(data instanceof FormData){
            param = data;
        }else{
            param = utils.json.format.formData(data);
        }
        request.ajax(url,"POST",param,headers,fn,false);
    },
    putJson: function(url,data,headers,fn){
        headers = utils.json.isEmpty(headers)?{}:headers;
        headers["Content-type"] = "application/json;charset=UTF-8";
        data = JSON.stringify(data);
        request.ajax(url,"PUT",data,headers,fn,true);
    },
    putJsonSync: function(url,data,headers,fn){
        headers = utils.json.isEmpty(headers)?{}:headers;
        headers["Content-type"] = "application/json;charset=UTF-8";
        data = JSON.stringify(data);
        request.ajax(url,"PUT",data,headers,fn,false);
    },
    putForm: function(url,data,headers,fn){
        let param = null;
        if(data instanceof FormData){
            param = data;
        }else{
            param = utils.json.format.formData(data);
        }
        request.ajax(url,"PUT",param,headers,fn,true);
    },
    putFormSync: function(url,data,headers,fn){
        let param = null;
        if(data instanceof FormData){
            param = data;
        }else{
            param = utils.json.format.formData(data);
        }
        request.ajax(url,"PUT",param,headers,fn,false);
    },
    del: function(url,data,headers,fn){
        let realUrl = url + "?" + utils.json.format.kv(data);
        request.ajax(realUrl,"DELETE",data,headers,fn,true);
    },
    delSync: function(url,data,headers,fn){
        let realUrl = url + "?" + utils.json.format.kv(data);
        request.ajax(realUrl,"DELETE",data,headers,fn,false);
    },
};


function error(resp) {
    let m = getMsg(resp);
    if(!utils.str.isEmpty(m)){
        msg.error(m);
    }
    if(resp.code == 401){
        if (window != top){
            top.location.href = resp.redirectUrl;
            return;
        }else{
            window.location.href = resp.redirectUrl;
            return;
        }
    }
}


function getMsg(resp) {
    let m = resp.msg;
    if(!m){
        m = resp.message;
    }
    return m;
}

function getResp(resp) {
    if(utils.str.isJson(resp)){
        return  JSON.parse(resp);
    }else{
        return resp;
    }
}


