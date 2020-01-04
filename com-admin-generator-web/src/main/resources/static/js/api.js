/**
 * Created by ZSW on 2019/12/27.
 */

const api = {};


api.generator = {
    root:"/generator/",
    tables:function () {
        return api.generator.root + "tables";
    },
    gen:function () {
        return api.generator.root + "gen";
    },
}