/**
 * Created by ZSW on 2019/2/27.
 */
const vm = new Vue({
    el:"#app",
    data:{
        tableLoading:false,
        tables:[],
        page:utils.page,
        search:"",
        selectTables:[],
        genForm: {
            host: null,
            port: null,
            user: null,
            password: null,
            dbName: null,
            packageName:null,
            author:'admin',
            lombok:'1'
        },
        genFormDialogShow:false
    },
    mounted:function(){
        let form = utils.localstore.get(config.GEN_FORM_KEY)
        if(!utils.str.isEmpty(form)){
            this.genForm = JSON.parse(form);
            this.getTables();
        }else{
            this.genFormDialogShow = true;
        }
    },
    methods:{
        getTables:function (reset) {
            let t = this;
            let page = t.page;
            if(reset){
                page['currentPage'] = 1;
            }
            let genForm = t.genForm;
            page['tableName'] = t.search;
            let params = utils.json.merge(page,genForm);
            t.tableLoading = true;
            requestF.getJson(api.generator.tables(),params,function (resp) {
                if(resp.success){
                    let page = resp.data;
                    t.tables = page.data;
                    t.page.currentPage = page.currentPage;
                    t.page.totalPage = page.totalPage;
                    t.page.total = page.total;
                    t.tableLoading = false;
                }
            })
        },
        gen:function () {
            let t = this;
            let param = t.genForm;
            param['tableNames'] = t.selectTables;
            requestF.download(api.generator.gen(),param)
        },
        handleSizeChange:function (val) {
            this.page.pageSize = val;
            this.getTables();
        },
        handleCurrentChange(val) {
            this.page.currentPage = val;
            this.getTables();
        },
        setting:function () {
            let t = this;
            this.$refs['genForm'].validate((valid) => {
                if (valid) {
                  utils.localstore.put(config.GEN_FORM_KEY,t.genForm);
                  t.genFormDialogShow = false;
                  t.getTables();
                }
            });
        },
        selectF:function (row) {
            let tableNames = [];
            for(let x in row){
                let r = row[x];
                tableNames.push(r.tableName);
            }
            this.selectTables = tableNames;
        }
    }

});
