/**
 * Created by ZSW on 2019/2/28.
 */
var msg = {};
msg.error = function (msg) {
 return vm.$message.error(msg);
};

msg.warning = function (msg) {
    return vm.$message({
        message: msg,
        type: 'warning'
    });
};

msg.success = function (msg) {
    return vm.$message({
        message: msg,
        type: 'success'
    });
};
