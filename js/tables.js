layui.define(function (exports) {

    var BTable = function () {
        this.config = {
            columns: function (parm) {
                getTpl(parm);
            }
        }
    };

    /**
     * 获取模板
     * @param {Object} options
     */
    function getTpl(parms) {
        var tpl = '<ul class="page_left_list">';
        tpl += '{{#  layui.each(parms, function(index, item){ }}';
        tpl += '<li>';
        tpl += '<h2 class="title"><a href="./doc_view.html?id="+{{ item.id }}>{{ item.title }}</a></h2>';
        tpl += '<div class="date_hits">';
        tpl += ' <span>{{ item.author }}</span>';
        tpl += '<span>{{ item.createTime}}</span>';
        tpl += '<span class="hits"><i class="layui-icon" title="点击量"></i> {{ item.clickate }} ℃</span>';
        tpl += '</div>';
        tpl += '<div class="desc">{{ item.desc}}</div>';
        tpl += '</li>';
        tpl += '{{#  }); }}';
        tpl += '</ul>';
        tpl += '<div id="page">';
        tpl += '<ul class="pagination">';
        tpl += '<li class="disabled"><span>&laquo;</span></li>';
        tpl += '{{#  layui.each(parms, function(index, item){ }}';
        tpl += '<li class="{{#  if(index == 1){ }} active {{#  } }} "><span>{{ index }}</span></li>';
        tpl += '{{#  }); }}';
        tpl += '<li><a href="">&raquo;</a></li>';
        tpl += '</ul>';
        tpl += '</div>';
        return tpl;
    }

    exports('tables', BTable);
});
