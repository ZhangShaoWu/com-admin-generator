/**
 * Created by ZSW on 2019/11/16.
 */
const confirm = {};
confirm.remove = function (confirmFun,cancelFun) {
    confirm.warning("确定删除吗？",confirmFun,cancelFun);
};

confirm.warning = function (content,confirmFun,cancelFun) {
    vm.$confirm(content, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then((resp) => {
        if(confirmFun){
            confirmFun(resp);
        }
    }).catch((resp) => {
        if(cancelFun){
            cancelFun(resp);
        }
    });
};

confirm.prompt = function (title,reg,errMsg,confirmFun,cancelFun) {
    vm.$prompt(title, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: reg,
        inputErrorMessage: errMsg
    }).then(({ value }) => {
        if(confirmFun){
            confirmFun(value);
        }
    }).catch(() => {
        if(cancelFun){
            cancelFun();
        }
    });
};
